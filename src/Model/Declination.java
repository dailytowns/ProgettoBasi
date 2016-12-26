package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class Declination {

    private String sign;
    private Integer degrees;
    private Integer minute;
    private Double seconds;

    public Declination(String sign, int hour, int minute, double seconds) {
        this.sign = sign;
        this.degrees = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
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

    public static Double convertToDegrees(Declination d) {
        double res = d.getDegrees() + d.getMinute()/60 + d.getSeconds()/3600;
        if(d.getSign() == "-")
            return res*(-1);
        return res;
    }
}
