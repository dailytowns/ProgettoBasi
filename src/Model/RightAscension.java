package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class RightAscension {

    private int hour;
    private int minute;
    private double seconds;

    public RightAscension(int hour, int minute, double seconds) {
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public static double convertToDegrees(RightAscension r) {
        return 15*(r.getHour() + r.getMinute()/60 + r.getSeconds()/3600);
    }
}
