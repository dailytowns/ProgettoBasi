package main.Model;

/**
 * Coordinate angolari della galassia, comprendenti
 * declinazione e ascensione retta
 * @author Federico Amici
 */
public class CoordinateAngolari {

    private Declination declination;
    private RightAscension rightAscension;
    private Double gradiDec;
    private Double gradiAr;

    public CoordinateAngolari(Declination declination, RightAscension rightAscension) {
        this.declination = declination;
        this.rightAscension = rightAscension;
    }

    public CoordinateAngolari(Double gradiDec, Double gradiAr) {
        this.gradiAr = gradiAr;
        this.gradiDec = gradiDec;
    }

    public Double getGradiDec() {
        return gradiDec;
    }

    public Double getGradiAr() {
        return gradiAr;
    }

    /**
     * Calcola la distanza tra due punti individuati dalle coordinate angolari
     * passate come parametri
     * @param a1 Coordinate angolari della prima galassia
     * @param a2 Coordinate angolari della seconda galassia
     * @return La distanza tra i due punti
     */
    public static double computeDistanceBetweenCoordinates(CoordinateAngolari a1, CoordinateAngolari a2) {
        /*d=arccos(sin(ra1) * sin(ra2) + cos(ra1) * cos(ra2) * cos(dec1 - dec2))*/
        /*Risparmiate 6 conversioni*/
        double sinRA1 = Math.sin(a1.getGradiAr());
        double sinRA2 = Math.sin(a2.rightAscension.getDegAR());
        double cosRA1 = Math.cos(a1.getGradiAr());
        double cosRA2 = Math.cos(a2.rightAscension.getDegAR());
        double cosD1D2 = Math.cos(a1.getGradiAr() - a2.declination.getDegDec());

        System.out.println(Math.acos((sinRA1*sinRA2 + cosRA1*cosRA2*cosD1D2)));

        return Math.abs(Math.acos((sinRA1*sinRA2 + cosRA1*cosRA2*cosD1D2)));
    }

    public Declination getDeclination() {
        return declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

}
