package main.Model;

/**
 * Classe main.Model di una galassia. Implementa l'interfaccia Comparable
 * per realizzare l'ordinamento per relativeDistance
 * @author Federico Amici
 */
public class Galaxy implements Comparable<Galaxy>{

    private String name;
    private String altName;
    private double redshift;
    private Double relativeDistance; //utile nell'ordinamento per raggio
    private CaratteristicheFisiche caratteristicheFisiche;
    private CoordinateAngolari coordinateAngolari;

    public Galaxy () {

    }

    public void setCaratteristicheFisiche(CaratteristicheFisiche caratteristicheFisiche) {this.caratteristicheFisiche = caratteristicheFisiche;}

    public void setCoordinateAngolari(CoordinateAngolari coordinateAngolari) {this.coordinateAngolari = coordinateAngolari;}

    public void setAltName(String altName) {this.altName = altName;}

    public Double getRelativeDistance() {return relativeDistance;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public double getRedshift() {
        return redshift;
    }

    public void setRedshift(double redshift) {
        this.redshift = redshift;
    }

    public CaratteristicheFisiche getCaratteristicheFisiche() {
        return caratteristicheFisiche;
    }

    public CoordinateAngolari getCoordinateAngolari() {
        return coordinateAngolari;
    }

    public Galaxy(String name, String altName, double redshift) {
        this.name = name;
        this.altName = altName;
        this.redshift = redshift;
    }

    public Galaxy(String name, String altName, double redshift, Double relativeDistance){
        this.name = name;
        this.altName = altName;
        this.redshift = redshift;
        this.relativeDistance = relativeDistance;
    }

    public Galaxy(String name, String altName, double redshift, CaratteristicheFisiche caratteristicheFisiche, CoordinateAngolari coordinateAngolari) {
        this.name = name;
        this.altName = altName;
        this.redshift = redshift;
        this.caratteristicheFisiche = caratteristicheFisiche;
        this.coordinateAngolari = coordinateAngolari;
    }

    @Override
    public int compareTo(Galaxy o) {
        return (this.relativeDistance).compareTo(o.relativeDistance);
    }

}
