package Model;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by feder on 24/12/2016.
 */
public class Distanza {

    private Double valore;
    private Integer riferimento;

    public Distanza(String valore, String riferimento) {
        controllaValori(valore, riferimento);
    }

    private void controllaValori(String valore, String riferimento) {
        if(checkValue(valore))
            try {
                this.valore = Double.parseDouble(valore);
            } catch (Exception e) {
                e.printStackTrace();
            }

        else {
            System.out.println("VALORE       " + valore);
            this.valore = null;
        }

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
