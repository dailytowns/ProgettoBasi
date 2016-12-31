package Model;

/**
 * Created by feder on 31/12/2016.
 */
public class Luminosita {

    private Double valore;
    private Integer riferimento;

    public Luminosita(String valore, String riferimento) {
        controllaValori(valore, riferimento);
    }
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
