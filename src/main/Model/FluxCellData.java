package main.Model;

/**
 * Classe contenenti i dati utili per la visualizzazione in lista
 * dei flussi
 * @author Federico Amici
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
