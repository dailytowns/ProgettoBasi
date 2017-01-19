package main.Model;

/**
 * Coordinate angolari della galassia, comprendenti
 * declinazione e ascensione retta
 * @author Federico Amici
 */
public class CoordinateAngolari {

    private Declination declination;
    private RightAscension rightAscension;

    public CoordinateAngolari(Declination declination, RightAscension rightAscension) {
        this.declination = declination;
        this.rightAscension = rightAscension;
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
        double sinRA1 = Math.sin(a1.rightAscension.getDegAR());
        double sinRA2 = Math.sin(a2.rightAscension.getDegAR());
        double cosRA1 = Math.cos(a1.rightAscension.getDegAR());
        double cosRA2 = Math.cos(a2.rightAscension.getDegAR());
        double cosD1D2 = Math.cos(a1.declination.getDegDec() - a2.declination.getDegDec());

        return Math.abs(Math.acos((sinRA1*sinRA2 + cosRA1*cosRA2*cosD1D2)));
    }

    public Declination getDeclination() {
        return declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

}
