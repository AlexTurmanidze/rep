package web.model;

public class Car {
    private int series;
    private int speed;
    private String model;

    public Car() {
    }

    public Car(int series, int speed, String model) {
        this.series = series;
        this.speed = speed;
        this.model = model;
    }

    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
