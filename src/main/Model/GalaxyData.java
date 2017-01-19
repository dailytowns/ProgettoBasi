package main.Model;

/**
 * Classe addetta al incapsulamento dei dati da visualizzare
 * relativamente ad una galassia
 */
public class GalaxyData {

    private Galaxy galaxy;
    private int counter;

    public GalaxyData(Galaxy galaxy, int counter) {
        this.galaxy = galaxy;
        this.counter = counter;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public int getCounter() {
        return counter;
    }
}