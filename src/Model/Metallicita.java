package Model;

/**
 * Created by feder on 24/12/2016.
 */

public class Metallicita {

    private Double valore;
    private Double errore;
    private Integer riferimento;

    public Metallicita(String valore, String errore, String riferimento) {
        controllaValori(valore, errore, riferimento);
    }

    private void controllaValori(String valore, String errore, String riferimento) {

        if(checkValue(valore))
            this.valore = Double.parseDouble(valore);
        else
            this.valore = null;
        if(checkValue(errore))
            this.errore = Double.parseDouble(errore);
        else
            this.errore = null;
        if(checkValue(riferimento))
            this.riferimento = Integer.parseInt(riferimento);
        else
            this.riferimento = 0;

    }

    private boolean checkValue(String value) {
        int i = 0;
        if(value == null)
            return false;

        while(i < value.length()) {
            if(value.charAt(i) != ' ') {
                return true;
            }
            i++;
        }
        return false;
    }

    public Double getValore() {
        return valore;
    }

    public Double getErrore() {
        return errore;
    }

    public Integer getRiferimento() {
        return riferimento;
    }
}
