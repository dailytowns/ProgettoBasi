package main.Model;

/**
 * Flusso delle righe calcolato da Herschel/PACS
 * @author Federico Amici
 */
public class FluxHP {

    private String nomeGalassia;
    private String atomo;
    private Double valore;
    private String upperLimit;
    private String aperture;
    private Double error;

    public FluxHP(String nomeGalassia, String atomo, String valore, String upperLimit,
                  String aperture, String error) {
        this.nomeGalassia = nomeGalassia;
        this.atomo = atomo;
        this.aperture = aperture;
        controllaValori(valore, upperLimit, error);
    }

    /**
     * Controlla che i valori recuperati non siano in realtà nulli
     * @param valore Valore del flusso
     * @param upperLimit Se impostato, indica che la misura costituisce un upperlimit
     * @param error Errore sulla misura del flusso
     */
    protected void controllaValori(String valore, String upperLimit, String error) {
        if(checkValue(valore))
            this.valore = Double.parseDouble(valore);
        else
            this.valore = null;
        if(checkValue(upperLimit))
            this.upperLimit = upperLimit;
        else
            this.upperLimit = null;

        if(checkValue(error))
            this.error = Double.parseDouble(error);
        else
            this.error = null;

    }

    protected boolean checkValue(String value) {
        int i = 0;

        while(i < value.length()) {
            if(value.charAt(i) != ' ') {
                return true;
            }
            i++;
        }
        return false;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
    }

    public void setNomeGalassia(String nomeGalassia) {
        this.nomeGalassia = nomeGalassia;
    }

    public String getAtomo() {
        return atomo;
    }

    public void setAtomo(String atomo) {
        this.atomo = atomo;
    }

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }
}
