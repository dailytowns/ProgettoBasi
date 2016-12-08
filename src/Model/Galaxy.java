package Model;

/**
 * Created by feder on 07/12/2016.
 */
public class Galaxy {

    private String name;
    private String altName;
    private double redshift;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public double getRedshift() {
        return redshift;
    }

    public void setRedshift(double redshift) {
        this.redshift = redshift;
    }

    public Galaxy(String name, String altName, double redshift) {
        this.name = name;
        this.altName = altName;
        this.redshift = redshift;
    }
}
