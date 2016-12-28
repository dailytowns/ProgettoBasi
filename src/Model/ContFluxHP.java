package Model;

/**
 * Created by feder on 27/12/2016.
 */
public class ContFluxHP extends FluxHP {

    private String ref160um;

    public ContFluxHP(String nomeGalassia, String tipologia, String atomo, String valore, String upperLimit,
                      String aperture, String ref160um, String error) {
        super(nomeGalassia, tipologia, atomo, valore, upperLimit, aperture, error);
        if(super.checkValue(ref160um))
            this.ref160um = ref160um;
        else
            this.ref160um = null;
    }

    public String getRef160um() {
        return this.ref160um;
    }

}
