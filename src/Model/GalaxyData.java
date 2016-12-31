package Model;

/**
 * Created by feder on 31/12/2016.
 */
public class GalaxyData {

    private String nomeGalassia;
    private Double redshift;
    private CaratteristicheFisiche caratteristicheFisiche;
    private CoordinateAngolari coordinateAngolari;

    public GalaxyData(String nomeGalassia, Double redshift, CaratteristicheFisiche caratteristicheFisiche,
                      CoordinateAngolari coordinateAngolari) {
        this.nomeGalassia = nomeGalassia;
        this.redshift = redshift;
        this.caratteristicheFisiche = caratteristicheFisiche;
        this.coordinateAngolari = coordinateAngolari;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
    }

    public Double getRedshift() {
        return redshift;
    }

    public CaratteristicheFisiche getCaratteristicheFisiche() {
        return caratteristicheFisiche;
    }

    public CoordinateAngolari getCoordinateAngolari() {
        return coordinateAngolari;
    }
}
