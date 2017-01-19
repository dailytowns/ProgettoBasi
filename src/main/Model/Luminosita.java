package main.Model;

/**
 * Classe model addetta alla modellizzazione della caratteristica
 * fisica luminosità
 * @author Federico Amici
 */
public class Luminosita {

    private Double valore;
    private Integer riferimento;

    public Luminosita(String valore, String riferimento) {
        controllaValori(valore, riferimento);
    }

    /**
     * Controlla che i valori recuperati non siano in realtà nulli
     * @param valore Valore della luminosità
     * @param riferimento Riferimento bibliografico sulla luminosità
     */
    private void controllaValori(String valore, String riferimento) {

        if(checkValue(valore))
            this.valore = Double.parseDouble(valore);
        else
            this.valore = null;
        if(checkValue(riferimento))
            this.riferimento = Integer.parseInt(riferimento);
        else
            this.riferimento = null;

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

    public Integer getRiferimento() {
        return riferimento;
    }
}
