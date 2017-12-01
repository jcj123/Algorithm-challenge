package sf.sgs.Algorithm;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstCode {
    public static void main(String[] args) {
        String post = HttpHelper.post("http://10.2.5.64/test", "{\"name\":\"某不知名炮灰队\"}");
        System.out.println(post);
        Gson gson = new Gson();
        TestBean testBean = gson.fromJson(post,TestBean.class);
        System.out.println(testBean.toString());
        List<TestBean.StateBean.JobsBean> jobs = testBean.getState().getJobs();
        List<TestBean.StateBean.WallsBean> walls = testBean.getState().getWalls();
        int score;

        Point[][] allPoint = new Point[12][12];
        int[][] value = new int[12][12];
        boolean[][] hasGone = new boolean[12][12];


        Point pointNow = new Point(0, 0);
        pointNow.distance = 0;

        //初始化网格
        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                Point point = new Point(i,j);
                allPoint[i][j] = point;
            }
        }

        for(TestBean.StateBean.JobsBean job : jobs){
            value[job.getX()][job.getY()] = job.getValue();
        }

        for(TestBean.StateBean.WallsBean wall: walls){
            allPoint[wall.getX()][wall.getY()].fobidden = true;
        }



        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                //左
                if(i>=1 && i<=12 && ! allPoint[i-1][j].fobidden){
                    allPoint[i][j].nextPointList.add(allPoint[i-1][j]);
                }
                //右
                if(i>=0 && i<=11 && ! allPoint[i-1][j].fobidden){
                    allPoint[i][j].nextPointList.add(allPoint[i+1][j]);
                }
                //上
                if(j>=1 && i<=12 && ! allPoint[i][j-1].fobidden){
                    allPoint[i][j].nextPointList.add(allPoint[i][j-1]);
                }
                //下
                if(j>=0 && i<=11 && ! allPoint[i][j+1].fobidden){
                    allPoint[i][j].nextPointList.add(allPoint[i][j+1]);
                }
            }
        }

        //模型初始化完成

        Set<Point> viewedPointSet = new HashSet<Point>();
        Set<Point> unViewedPointSet = new HashSet<Point>();
        for(int i=0; i<12; i++){
            for(int j = 0; j<12; j++){
                unViewedPointSet.add(allPoint[i][j]);
            }
        }
        viewedPointSet.add(pointNow);

        //核心算法
        while( !unViewedPointSet.isEmpty()){
            Point addPoint = new Point(Integer.MAX_VALUE);
            for(Point point : viewedPointSet){
                for(Point nextPoint : point.nextPointList){
                    nextPoint.distance = point.distance + 1;
                    if(nextPoint.distance < addPoint.distance){   //遍历已赋值的节点，找到数值最小的那个
                        addPoint = nextPoint;
                        point.nextPoint = addPoint;
                    }
                }
            }
            viewedPointSet.add(addPoint);
            unViewedPointSet.remove(addPoint);
        }


    }

    static class Point {
        int x;
        int y;
        int distance;
        boolean fobidden;
        HashSet<Point> nextPointList;
        Point nextPoint;

        public Point(int x, int y){
            this.x  = x;
            this.y = y;
            distance = Integer.MAX_VALUE;
        }

        public Point(int value){
            this.distance = value;
        }
    }
}
