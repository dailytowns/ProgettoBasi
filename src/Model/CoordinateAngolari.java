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

    public static void main(String[] args) {
        Declination d1 = new Declination("-", 12, 6, 276.624);
        System.out.println(Declination.convertToDegrees(d1));
        RightAscension ra1 = new RightAscension(0, 11, 65.412);
        System.out.println(RightAscension.convertToDegrees(ra1));

        Declination d2 = new Declination("+", 21, 57, 368.064);
        System.out.println(Declination.convertToDegrees(d2));
        RightAscension ra2 = new RightAscension(0, 3, 96.038);
        System.out.println(RightAscension.convertToDegrees(ra2));

        CoordinateAngolari c1 = new CoordinateAngolari(d1, ra1);
        CoordinateAngolari c2 = new CoordinateAngolari(d2, ra2);

        System.out.println(CoordinateAngolari.computeDistanceBetweenCoordinates(c1, c2));
    }

}
