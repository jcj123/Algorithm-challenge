package sf.sgs.Algorithm;

public class ControlBean {
    public ControlBean(String direction) {
        this.direction = direction;
    }

    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "ControlBean{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
