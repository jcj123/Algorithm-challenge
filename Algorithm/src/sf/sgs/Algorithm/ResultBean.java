package sf.sgs.Algorithm;

import java.util.List;

/**
 * Created by lxb on 2017-12-01.
 */

public class ResultBean {

    /**
     * action : U
     * state : {"ai":{"y":0,"x":0},"walls":[{"y":2,"x":1},{"y":3,"x":2},{"y":3,"x":4},{"y":4,"x":4},{"y":6,"x":9},{"y":6,"x":8},{"y":5,"x":6},{"y":8,"x":6},{"y":9,"x":6},{"y":9,"x":3},{"y":9,"x":2},{"y":10,"x":2},{"y":2,"x":9},{"y":2,"x":8}],"score":0,"jobs":[{"y":1,"x":0,"value":33},{"y":2,"x":0,"value":24},{"y":8,"x":0,"value":22},{"y":10,"x":0,"value":34},{"y":1,"x":1,"value":24},{"y":3,"x":1,"value":25},{"y":5,"x":2,"value":18},{"y":7,"x":4,"value":27},{"y":3,"x":7,"value":31},{"y":6,"x":7,"value":28},{"y":9,"x":7,"value":17},{"y":3,"x":9,"value":25},{"y":5,"x":9,"value":24},{"y":9,"x":9,"value":28},{"y":10,"x":9,"value":24},{"y":11,"x":9,"value":30},{"y":0,"x":11,"value":30},{"y":1,"x":11,"value":32}]}
     * done : false
     * msg : OK
     * reward : 0
     * id : 29146778-6904-4f5f-beb8-d44efc566914
     */

    private String action;
    private StateBean state;
    private boolean done;
    private String msg;
    private int reward;
    private String id;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
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
         * jobs : [{"y":1,"x":0,"value":33},{"y":2,"x":0,"value":24},{"y":8,"x":0,"value":22},{"y":10,"x":0,"value":34},{"y":1,"x":1,"value":24},{"y":3,"x":1,"value":25},{"y":5,"x":2,"value":18},{"y":7,"x":4,"value":27},{"y":3,"x":7,"value":31},{"y":6,"x":7,"value":28},{"y":9,"x":7,"value":17},{"y":3,"x":9,"value":25},{"y":5,"x":9,"value":24},{"y":9,"x":9,"value":28},{"y":10,"x":9,"value":24},{"y":11,"x":9,"value":30},{"y":0,"x":11,"value":30},{"y":1,"x":11,"value":32}]
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
             * y : 1
             * x : 0
             * value : 33.0
             */

            private int y;
            private int x;
            private float value;

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

            public float getValue() {
                return value;
            }

            public void setValue(float value) {
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
        return "ResultBean{" +
                "action='" + action + '\'' +
                ", state=" + state +
                ", done=" + done +
                ", msg='" + msg + '\'' +
                ", reward=" + reward +
                ", id='" + id + '\'' +
                '}';
    }
}
