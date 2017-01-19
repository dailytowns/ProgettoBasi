package main.Model;

/**
 * Classe model dell'ascensione retta
 * @author Federico Amici
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

    public RightAscension () {

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

    /**
     * Converte in gradi l'ascensione retta in formato (HH, MM, SS)
     * @param hours Gradi dell'ascensione retta
     * @param minutes Minuti dell'ascensione retta
     * @param seconds Secondi dell'ascensione retta
     * @return Il valore della conversione
     */
    public static Double convertToDegrees(Integer hours, Integer minutes, Double seconds) {
        return 15*(hours + minutes/60 + seconds/3600);
    }
}
