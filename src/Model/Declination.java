package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class Declination {

    private String sign;
    private Integer degrees;
    private Integer minutes;
    private Double seconds;
    private Double degDec;

    public Declination () {

    }

    public Declination(String sign, int degrees, int minutes, Double seconds) {
        this.sign = sign;
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.degDec = convertToDegrees(degrees, minutes, seconds, sign);
    }

    public String getSign() {
        return sign;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getMinute() {
        return minutes;
    }

    public Double getSeconds() {
        return seconds;
    }

    public Double getDegDec() {return degDec;}

    public static Double convertToDegrees(Integer degrees, Integer minutes, Double seconds, String sign) {
        double res = degrees + minutes/60 + seconds/3600;
        if(sign == "-")
            return res*(-1);
        return res;
    }
}
