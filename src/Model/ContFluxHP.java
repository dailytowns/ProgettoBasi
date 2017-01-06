package Model;

/**
 * Created by feder on 27/12/2016.
 */
public class ContFluxHP {

    private String nomeGalassia;
    private String atomo;
    private Double valore;
    private String upperLimit;
    private String aperture;
    private Double error;
    private String ref160um;

    public ContFluxHP(String nomeGalassia, String atomo, String valore, String upperLimit,
                      String aperture, String ref160um, String error) {
        this.nomeGalassia = nomeGalassia;
        this.atomo = atomo;
        this.aperture = aperture;
        if(checkValue(ref160um))
            this.ref160um = ref160um;
        else
            this.ref160um = null;
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

    public String getRef160um() {
        return this.ref160um;
    }

}
