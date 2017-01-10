package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class RightAscension {

    private Integer hours;
    private Integer minutes;
    private Double seconds;
    private Double degAR;

    public RightAscension(Integer hours, Integer minutes, Double seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.degAR = convertToDegrees(hours, minutes, seconds);
    }

    public Integer getHour() {
        return hours;
    }

    public Integer getMinute() {
        return minutes;
    }

    public Double getSeconds() {
        return seconds;
    }

    public Double getDegAR() {
        return degAR;
    }

    public static Double convertToDegrees(Integer hours, Integer minutes, Double seconds) {
        return 15*(hours + minutes/60 + seconds/3600);
    }
}
