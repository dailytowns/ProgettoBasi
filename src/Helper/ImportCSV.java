package Helper;

/**
 * @author Federico Amici
 * Classe astratta adibita all'import dei file .csv contenenti dati utili
 */
public abstract class ImportCSV {

    /**
     * Importa il file .csv
     * @param path Percorso del file da importare
     */
    public abstract int importFile(String path);

}
