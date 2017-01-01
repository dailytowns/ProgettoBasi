package Model;

/**
 * Created by feder on 27/12/2016.
 */
public class FluxSp extends Flux {

    private String IRSMode;

    public FluxSp(String nomeGalassia, String atomo, String valore, String upperLimit, String error, String IRSMode) {
        super(nomeGalassia, atomo, valore, upperLimit, error);
        if(checkValue(IRSMode))
            this.IRSMode = IRSMode;
        else
            this.IRSMode = null;
    }

    public String getIRSMode() {
        return this.IRSMode;
    }
}
