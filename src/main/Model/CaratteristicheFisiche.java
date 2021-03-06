package main.Model;

/**
 * Insieme di caratteristiche fisiche della galassia
 * @author Federico Amici
 */
public class CaratteristicheFisiche {

    private String nomeGalassia;
    private Metallicita metallicita;
    private Luminosita luminosita;

    public CaratteristicheFisiche() {

    }

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
