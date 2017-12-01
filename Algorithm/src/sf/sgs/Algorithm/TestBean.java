package sf.sgs.Algorithm;

import java.util.List;

/**
 * Created by lxb on 2017-12-01.
 */

public class TestBean {

    /**
     * msg : OK
     * state : {"ai":{"y":0,"x":0},"walls":[{"y":2,"x":1},{"y":3,"x":2},{"y":3,"x":4},{"y":4,"x":4},{"y":6,"x":9},{"y":6,"x":8},{"y":5,"x":6},{"y":8,"x":6},{"y":9,"x":6},{"y":9,"x":3},{"y":9,"x":2},{"y":10,"x":2},{"y":2,"x":9},{"y":2,"x":8}],"score":0,"jobs":[{"y":7,"x":0,"value":21},{"y":3,"x":1,"value":25},{"y":8,"x":2,"value":30},{"y":11,"x":2,"value":30},{"y":8,"x":3,"value":24},{"y":9,"x":4,"value":18},{"y":7,"x":5,"value":20},{"y":8,"x":5,"value":23},{"y":9,"x":5,"value":28},{"y":0,"x":7,"value":35},{"y":11,"x":7,"value":36},{"y":0,"x":8,"value":25},{"y":1,"x":8,"value":35},{"y":9,"x":8,"value":32},{"y":8,"x":9,"value":33},{"y":4,"x":10,"value":31},{"y":5,"x":10,"value":25},{"y":2,"x":11,"value":34}]}
     * id : 6cda36d2-956f-4003-b064-c415f707d575
     */

    private String msg;
    private StateBean state;
    private String id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class StateBean {
        /**
         * ai : {"y":0,"x":0}
         * walls : [{"y":2,"x":1},{"y":3,"x":2},{"y":3,"x":4},{"y":4,"x":4},{"y":6,"x":9},{"y":6,"x":8},{"y":5,"x":6},{"y":8,"x":6},{"y":9,"x":6},{"y":9,"x":3},{"y":9,"x":2},{"y":10,"x":2},{"y":2,"x":9},{"y":2,"x":8}]
         * score : 0
         * jobs : [{"y":7,"x":0,"value":21},{"y":3,"x":1,"value":25},{"y":8,"x":2,"value":30},{"y":11,"x":2,"value":30},{"y":8,"x":3,"value":24},{"y":9,"x":4,"value":18},{"y":7,"x":5,"value":20},{"y":8,"x":5,"value":23},{"y":9,"x":5,"value":28},{"y":0,"x":7,"value":35},{"y":11,"x":7,"value":36},{"y":0,"x":8,"value":25},{"y":1,"x":8,"value":35},{"y":9,"x":8,"value":32},{"y":8,"x":9,"value":33},{"y":4,"x":10,"value":31},{"y":5,"x":10,"value":25},{"y":2,"x":11,"value":34}]
         */

        private AiBean ai;
        private int score;
        private List<WallsBean> walls;
        private List<JobsBean> jobs;

        public AiBean getAi() {
            return ai;
        }

        public void setAi(AiBean ai) {
            this.ai = ai;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<WallsBean> getWalls() {
            return walls;
        }

        public void setWalls(List<WallsBean> walls) {
            this.walls = walls;
        }

        public List<JobsBean> getJobs() {
            return jobs;
        }

        public void setJobs(List<JobsBean> jobs) {
            this.jobs = jobs;
        }

        public static class AiBean {
            /**
             * y : 0
             * x : 0
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }
        }

        public static class WallsBean {
            /**
             * y : 2
             * x : 1
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            @Override
            public String toString() {
                return "WallsBean{" +
                        "y=" + y +
                        ", x=" + x +
                        '}';
            }
        }

        public static class JobsBean {
            /**
             * y : 7
             * x : 0
             * value : 21
             */

            private int y;
            private int x;
            private int value;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return "JobsBean{" +
                        "y=" + y +
                        ", x=" + x +
                        ", value=" + value +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "msg='" + msg + '\'' +
                ", state=" + state.toString() +
                ", id='" + id + '\'' +
                '}';
    }
}
