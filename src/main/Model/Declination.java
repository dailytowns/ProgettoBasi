package main.Model;

/**
 * Classe model della Declinazione
 * @author Federico Amici
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

    /**
     * Converte in gradi la declinazione in formato (+/-, GG, MM, SS)
     * @param degrees Gradi della declinazione
     * @param minutes Minuti della declinazione
     * @param seconds Secondi della declinazione
     * @param sign Segno della declinazione
     * @return Il valore della conversione
     */
    public static Double convertToDegrees(Integer degrees, Integer minutes, Double seconds, String sign) {
        double res = degrees + minutes/60 + seconds/3600;
        if(sign == "-")
            return res*(-1);
        return res;
    }
}
