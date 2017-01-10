package Model;

import com.sun.mail.imap.Rights;

/**
 * Created by feder on 08/12/2016.
 */
public class CoordinateAngolari {

    private Declination declination;
    private RightAscension rightAscension;

    public CoordinateAngolari(Declination declination, RightAscension rightAscension) {
        this.declination = declination;
        this.rightAscension = rightAscension;
    }

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
