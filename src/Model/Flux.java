package Model;

/**
 * Created by feder on 27/12/2016.
 */
public class Flux {

    private String nomeGalassia;
    private String tipologia;
    private String atomo;
    private Double valore;
    private String upperLimit;
    private String aperture;
    private Double error;

    public Flux(String atomo, Double valore) {
        this.atomo = atomo;
        this.valore = valore;
    }

    public Flux(String nomeGalassia, String atomo, String valore, String upperLimit, String error) {
        this.nomeGalassia = nomeGalassia;
        this.atomo = atomo;
        this.aperture = aperture;
        controllaValori(valore, upperLimit, error);
    }

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

        if(value == null)
            return false;

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

    public String getAperture() {
        return aperture;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
    }

    public String getTipologia() {
        return tipologia;
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

}
