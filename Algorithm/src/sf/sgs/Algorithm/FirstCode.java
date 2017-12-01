package sf.sgs.Algorithm;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class FirstCode {

    Point[][] allPoint = new Point[12][12];
    int[][] value = new int[12][12];              //地图包裹价值图
    float[][] xingJiaBi = new float[12][12];       //性价比图
    boolean[][] hasGone = new boolean[12][12];
    static Gson gson;


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


    public static ResultBean getControl(String id) {
        String controlUrl = "http://10.2.5.64/test/" + id + "/move";
        ControlBean controlBean = new ControlBean("U");
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

    //算法
    public static void algorithm(TestBean testBean) {
        List<TestBean.StateBean.JobsBean> jobs = testBean.getState().getJobs();
        List<TestBean.StateBean.WallsBean> walls = testBean.getState().getWalls();
        int score;
        Point[][] allPoint = new Point[12][12];
        int[][] value = new int[12][12];
        boolean[][] hasGone = new boolean[12][12];


        Point pointNow = new Point(0, 0);
        pointNow.distance = 0;

        //初始化网格
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Point point = new Point(i, j);
                allPoint[i][j] = point;
            }
        }

        for (TestBean.StateBean.JobsBean job : jobs) {
            value[job.getX()][job.getY()] = job.getValue();
        }

        for (TestBean.StateBean.WallsBean wall : walls) {
            allPoint[wall.getX()][wall.getY()].fobidden = true;
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                //左
                if (i >= 1 && i <= 12 && !allPoint[i - 1][j].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i - 1][j]);
                }
                //右
                if (i >= 0 && i <= 11 && !allPoint[i - 1][j].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i + 1][j]);
                }
                //上
                if (j >= 1 && i <= 12 && !allPoint[i][j - 1].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i][j - 1]);
                }
                //下
                if (j >= 0 && i <= 11 && !allPoint[i][j + 1].fobidden) {
                    allPoint[i][j].nextPointList.add(allPoint[i][j + 1]);
                }
            }
        }

        //模型初始化完成

        Set<Point> viewedPointSet = new HashSet<Point>();
        Set<Point> unViewedPointSet = new HashSet<Point>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                unViewedPointSet.add(allPoint[i][j]);
            }
        }
        viewedPointSet.add(pointNow);

        //核心算法
        while (!unViewedPointSet.isEmpty()) {
            Point addPoint = new Point(Integer.MAX_VALUE);
            for (Point point : viewedPointSet) {
                for (Point nextPoint : point.nextPointList) {
                    nextPoint.distance = point.distance + 1;
                    if (nextPoint.distance < addPoint.distance) {   //遍历已赋值的节点，找到数值最小的那个
                        addPoint = nextPoint;
                        nextPoint.previousPoint = point;
                    }
                }
            }
            viewedPointSet.add(addPoint);
            unViewedPointSet.remove(addPoint);
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                value[i][j] = value[i][j] - allPoint[i][j].distance;
//                xingJiaBi[i][j] = value[i][j]/allPoint[i][j].distance;
            }
        }

        int destPointX = 0;
        int destPointY = 0;
        int step = 0;
        int tempValue = Integer.MIN_VALUE;
        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                if(value[i][j] > tempValue){
                    tempValue = value[i][j];
                    destPointY = i;
                    destPointX = j;
                }
            }
        }
        Point destPoint = allPoint[destPointX][destPointY];

        Stack<Point> stack = new Stack<>();
        stack.add(destPoint);
        while(destPoint.previousPoint.x != pointNow.x && destPoint.previousPoint.y != pointNow.y){
            stack.add(destPoint.previousPoint);
            destPoint = destPoint.previousPoint;
        }
    }

    public static void main(String[] args) {
        TestBean testBean = getEnvironment();
        algorithm(testBean);
        ResultBean controlBean = getControl(testBean.getId());
        getReplay(testBean.getId());
    }

    static class Point {
        int x;
        int y;
        int distance;
        boolean fobidden;
        HashSet<Point> nextPointList;
        Point previousPoint;
        Point nextPoint;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = Integer.MAX_VALUE;
        }

        public Point(int value) {
            this.distance = value;
        }
    }
}

