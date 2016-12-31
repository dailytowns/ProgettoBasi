package Model;

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
        double sinRA1 = Math.sin(RightAscension.convertToDegrees(a1.rightAscension));
        double sinRA2 = Math.sin(RightAscension.convertToDegrees(a2.rightAscension));
        double cosRA1 = Math.cos(RightAscension.convertToDegrees(a1.rightAscension));
        double cosRA2 = Math.cos(RightAscension.convertToDegrees(a2.rightAscension));
        double cosD1D2 = Math.cos(Declination.convertToDegrees(a1.declination) - Declination.convertToDegrees(a2.declination));

        return Math.abs(Math.acos((sinRA1*sinRA2 + cosRA1*cosRA2*cosD1D2)));
    }

    public Declination getDeclination() {
        return declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }
}
