package Model;

/**
 * @author Federico Amici
 * Classe contenente i dati per una cella della listView
 * in SearchGalaxyForRadiusView
 */
public class GalaxyDataRadius extends GalaxyData {

    private Double radius;

    public GalaxyDataRadius(Galaxy galaxy, int counter, Double radius) {
        super(galaxy, counter);
        this.radius = radius;
    }

    public Double getRadius() {return this.radius;}
}
