package Model;

/**
 * Created by feder on 24/12/2016.
 */

public class Metallicita {

    private String nomeGalassia;
    private Double valore;
    private Double errore;
    private Integer riferimento;

    public Metallicita(String nomeGalassia, String valore, String errore, String riferimento) {
        this.nomeGalassia = nomeGalassia;
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
            this.riferimento = null;

    }

    private boolean checkValue(String value) {
        int i = 0;

        while(i < value.length()) {
            if(value.charAt(i) != ' ') {
                return true;
            }
            i++;
        }
        return false;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
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
