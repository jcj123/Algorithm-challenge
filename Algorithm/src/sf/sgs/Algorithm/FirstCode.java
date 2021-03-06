package sf.sgs.Algorithm;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class FirstCode {

    static Point[][] allPoint = new Point[12][12];
    static int[][] value = new int[12][12];              //地图包裹价值图
    static float[][] xingJiaBi = new float[12][12];       //性价比图
    static boolean[][] hasGone = new boolean[12][12];
    static Point mPointNow;
    static Point mPointStart;
    static Point mPointDest;
    static ResultBean mResultBean;
    static String mId;
    static int mScore;
    static int mStep;
    static Gson gson;


    public static void main(String[] args) {
        TestBean testBean = getEnvironment();
        mId = testBean.getId();
        mStep = 0;

        algorithm(testBean);
        getReplay(testBean.getId());
        System.out.println(mScore);
    }

    //算法
    public static void algorithm(TestBean testBean) {
        List<TestBean.StateBean.JobsBean> jobs = testBean.getState().getJobs();
        List<TestBean.StateBean.WallsBean> walls = testBean.getState().getWalls();


        //初始化网格
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Point point = new Point(i, j);
                allPoint[i][j] = point;
            }
        }

        for (TestBean.StateBean.JobsBean job : jobs) {
            allPoint[job.getY()][job.getX()].value = job.getValue();
        }

        for (TestBean.StateBean.WallsBean wall : walls) {
            allPoint[wall.getY()][wall.getX()].fobidden = true;
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                //左
                if (i >= 1  && !allPoint[i - 1][j].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i - 1][j]);
                }
                //右
                if (i <= 10 && !allPoint[i + 1][j].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i + 1][j]);
                }
                //上
                if (j >= 1  && !allPoint[i][j - 1].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i][j - 1]);
                }
                //下
                if ( j <= 10 && !allPoint[i][j + 1].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i][j + 1]);
                }
            }
        }

        //模型初始化完成

        mPointStart = allPoint[0][0];
        while(mStep < 400){
            Point destPoint = findPath(mPointStart);
            goToTheDestPoint(mPointStart,destPoint);
            resetAllPointInfo();
        }
    }


    private static Point findPath(Point pointStart){
        pointStart.distance = 0;
        pointStart.value = Integer.MIN_VALUE;

        Set<Point> viewedPointSet = new HashSet<Point>();
        Set<Point> unViewedPointSet = new HashSet<Point>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if(!allPoint[i][j].fobidden ){
                    unViewedPointSet.add(allPoint[i][j]);
                }
            }
        }
        viewedPointSet.add(pointStart);
        unViewedPointSet.remove(pointStart);


        //核心算法
        while (!unViewedPointSet.isEmpty()) {
            Point addPoint = new Point(Integer.MAX_VALUE);
            boolean hasFound = false;
            for (Point point : viewedPointSet) {
                if(point.nextPointList == null || point.nextPointList.isEmpty()){
                    continue;
                }
                for (Point nextPoint : point.nextPointList) {
                    if(nextPoint.hasAdd){
                        continue;
                    }
                    nextPoint.distance = point.distance + 1;
                    if (nextPoint.distance < addPoint.distance) {   //遍历已赋值的节点，找到数值最小的那个
                        hasFound = true;
                        addPoint = nextPoint;
                        addPoint.previousPoint = point;
                    }
                }
            }
            if(hasFound){
                addPoint.hasAdd = true;
                viewedPointSet.add(addPoint);
                unViewedPointSet.remove(addPoint);
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                allPoint[i][j].value = allPoint[i][j].value - allPoint[i][j].distance;
            }
        }
        allPoint[mPointStart.x][mPointStart.y].value = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                xingJiaBi[i][j] = allPoint[i][j].value/allPoint[i][j].distance;
            }
        }

        int destPointX = 0;
        int destPointY = 0;
        int step = 0;
        float tempValue = Float.MIN_VALUE;
        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                if(xingJiaBi[i][j] > tempValue){
                    tempValue = xingJiaBi[i][j];
                    destPointX = i;
                    destPointY = j;
                }
            }
        }

        //找到目标节点，并返回路径
        return allPoint[destPointX][destPointY];
    }


    private static void goToTheDestPoint(Point pointNow, Point destPoint){
        Stack<Point> stepStack = new Stack<>();
        stepStack.add(destPoint);
        while(destPoint.previousPoint != mPointStart){
            stepStack.add(destPoint.previousPoint);
            destPoint = destPoint.previousPoint;
        }

        int round = 0;
        while(!stepStack.isEmpty() && round < 6){
            Point startPoint = pointNow;
            Point endPoint = stepStack.pop();

            if(startPoint.x == endPoint.x){
                if(startPoint.y < endPoint.y){
                    mResultBean = getControl(mId, "D");
                } else if(startPoint.y > endPoint.y){
                    mResultBean = getControl(mId, "U");
                }else {
                    System.out.println("error");
                }

            }else if(startPoint.x <endPoint.x){
                mResultBean = getControl(mId, "R");
            }else if(startPoint.x > endPoint.x){
                mResultBean = getControl(mId, "L");
            }else{
                System.out.println("error");
            }
            mStep++;
            round++;
            System.out.println(mStep);
            System.out.println("mScore" + mScore);
            pointNow = endPoint;
            mScore += mResultBean.getReward();
            mPointStart = allPoint[mResultBean.getState().getAi().getY()][mResultBean.getState().getAi().getX()];
        }
    }

    //重置节点数据
    private static void resetAllPointInfo(){
        if(mResultBean == null || mResultBean.getState() == null)return;
        List<ResultBean.StateBean.JobsBean> jobs = mResultBean.getState().getJobs();

        mPointStart = allPoint[mResultBean.getState().getAi().getY()][mResultBean.getState().getAi().getX()];

        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                allPoint[i][j].hasAdd = false;
                allPoint[i][j].previousPoint = null;
                allPoint[i][j].value = 0;
                allPoint[i][j].distance = Integer.MAX_VALUE;
            }
        }

        for (ResultBean.StateBean.JobsBean job : jobs) {
            allPoint[job.getY()][job.getX()].value = (int)job.getValue();
        }
    }







    //获取环境
    private static TestBean getEnvironment() {
        String result = HttpHelper.post("http://10.2.5.64/test", "{\"name\":\"某不知名炮灰队\"}");
        System.out.println(result);
        gson = new Gson();
        TestBean testBean = gson.fromJson(result, TestBean.class);
        System.out.println(testBean.toString());
        return testBean;
    }

    //控制小哥
    public static ResultBean getControl(String id, String action) {
        String controlUrl = "http://10.2.5.64/test/" + id + "/move";
        ControlBean controlBean = new ControlBean(action);
        String controlJson = gson.toJson(controlBean);
        System.out.println("controlJson:" + controlJson);
        String result = HttpHelper.post(controlUrl, controlJson);
        System.out.println("controlResult :" + result);
        ResultBean resultBean = gson.fromJson(result, ResultBean.class);
        System.out.println(resultBean.toString());
        return resultBean;
    }

    //获取replay
    public static void getReplay(String id) {
        String replayUrl = "http://10.2.5.64/test/replays/" + id;
        String repalyJson = HttpHelper.get(replayUrl);
        System.out.println("replayJson :" + repalyJson);
    }

    static class Point {
        int x;
        int y;
        int distance;
        int value;
        boolean fobidden;
        boolean hasAdd;
        HashSet<Point> nextPointList;
        Point previousPoint;
        Point nextPoint;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = Integer.MAX_VALUE;
            nextPointList = new HashSet<>();
        }

        public Point(int distance) {
            this.distance = distance;
        }
    }
}

