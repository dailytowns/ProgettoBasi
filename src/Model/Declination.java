package Model;

/**
 * Created by feder on 08/12/2016.
 */
public class Declination {

    private char sign;
    private int hour;
    private int minute;
    private double seconds;

    public Declination(char sign, int hour, int minute, double seconds) {
        this.sign = sign;
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
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

    public static double convertToDegrees(Declination d) {
        double res = d.getHour() + d.getMinute()/60 + d.getSeconds()/3600;
        if(d.getSign() == '-')
            return res*(-1);
        return res;
    }
}
