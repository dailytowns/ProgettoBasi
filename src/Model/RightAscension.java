package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class RightAscension {

    private Integer hour;
    private Integer minute;
    private Double seconds;

    public RightAscension(Integer hours, Integer minutes, Double seconds) {
        this.hour = hours;
        this.minute = minutes;
        this.seconds = seconds;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Double getSeconds() {
        return seconds;
    }

    public static double convertToDegrees(RightAscension r) {
        return 15*(r.getHour() + r.getMinute()/60 + r.getSeconds()/3600);
    }
}
