package Model;

/**
 * Created by feder on 31/12/2016.
 */
public class CaratteristicheFisiche {

    private String nomeGalassia;
    private Metallicita metallicita;
    private Luminosita luminosita;

    public CaratteristicheFisiche(String nomeGalassia, Metallicita metallicita, Luminosita luminosita) {
        this.nomeGalassia = nomeGalassia;
        this.metallicita = metallicita;
        this.luminosita = luminosita;
    }

    public String getNomeGalassia() {
        return nomeGalassia;
    }

    public Metallicita getMetallicita() {
        return metallicita;
    }

    public Luminosita getLuminosita() {
        return luminosita;
    }
}
