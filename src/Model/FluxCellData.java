package Model;

/**
 * Created by feder on 28/12/2016.
 */
public class FluxCellData {

    private String nomeGalassia;
    private String atomo;
    private Double error;
    private String upperLimit;
    private Double valore;

    public FluxCellData(String nomeGalassia, String atomo, Double error, String upperLimit, Double valore) {
        this.nomeGalassia = nomeGalassia;
        this.atomo = atomo;
        this.error = error;
        this.upperLimit = upperLimit;
        this.valore = valore;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
    }

    public String getAtomo() {
        return atomo;
    }

    public Double getError() {
        return error;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public Double getValore() {
        return valore;
    }
}
