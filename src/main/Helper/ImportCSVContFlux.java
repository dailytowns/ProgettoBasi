package main.Helper;

import main.Model.ContFluxHP;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementazione del Factory Method addetto all'import
 * di file sul flusso continuo
 * @author Federico Amici
 */
public class ImportCSVContFlux extends ImportCSV {

    /**
     * Importa il file nel database
     * @param path Percorso del file da importare
     * @return Il numero di righe create
     */
    @Override
    public int importFile(String path) {

        resetDB();

        CSVReader reader = null;
        String[] nextLine;
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        String[] headerLine;

        int i = 0; //Contatore delle righe inserite utilizzato per testing
        try {
            reader = new CSVReader(new FileReader(path), '\t');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((headerLine = reader.readNext()) != null) {
                nextLine = headerLine[0].split(";");
                if(nextLine.length != 22)
                    continue;

                ContFluxHP fluxOIII52 = new ContFluxHP(nextLine[0], "OIII52",
                        nextLine[1], "NO", nextLine[21], nextLine[20], nextLine[2]);
                psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, ref160, errore) VALUES ('" + fluxOIII52.getNomeGalassia() + "', '" + fluxOIII52.getUpperLimit() + "', 'OIII52'," +
                        fluxOIII52.getValore() + ", '" + fluxOIII52.getAperture() + "', '" + fluxOIII52.getRef160um() + "'," + fluxOIII52.getError() + ");");

                i++;

                try {
                    ContFluxHP fluxNIII = new ContFluxHP(nextLine[0], "NIII57",
                            nextLine[3], "NO", nextLine[21], nextLine[20], nextLine[4]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxNIII.getNomeGalassia() + "', '" + fluxNIII.getUpperLimit() + "'" +
                            ", 'NIII57', " + fluxNIII.getValore() + ", '" + fluxNIII.getAperture() + "', '" + fluxNIII.getRef160um() + "', " +
                            fluxNIII.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;

                try {
                    ContFluxHP fluxOI = new ContFluxHP(nextLine[0], "OI63",
                            nextLine[6], nextLine[5], nextLine[21], nextLine[20], nextLine[7]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOI.getNomeGalassia() + "', '" + fluxOI.getUpperLimit() + "'" +
                            ", 'OI63', " + fluxOI.getValore() + ", '" + fluxOI.getAperture() + "', '" + fluxOI.getRef160um() + "'," +
                            fluxOI.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;

                try {
                    ContFluxHP fluxOIII88 = new ContFluxHP(nextLine[0], "OIII88",
                            nextLine[9], nextLine[8], nextLine[21], nextLine[20], nextLine[10]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOIII88.getNomeGalassia() + "', '" + fluxOIII88.getUpperLimit() + "'" +
                            ", 'OIII88', " + fluxOIII88.getValore() + ", '" + fluxOIII88.getAperture() + "', '" + fluxOIII88.getRef160um() + "'," +
                            fluxOIII88.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;

                try {
                    ContFluxHP fluxNII122 = new ContFluxHP(nextLine[0], "NIII122",
                            nextLine[12], nextLine[11], nextLine[21], nextLine[20], nextLine[13]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxNII122.getNomeGalassia() + "', '" + fluxNII122.getUpperLimit() + "'" +
                            ", 'NIII122', " + fluxNII122.getValore() + ", '" + fluxNII122.getAperture() + "','" + fluxNII122.getRef160um() + "'," +
                            fluxNII122.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;

                try {
                    ContFluxHP fluxOI145 = new ContFluxHP(nextLine[0], "OI145",
                            nextLine[15], nextLine[14], nextLine[21], nextLine[20], nextLine[16]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOI145.getNomeGalassia() + "', '" + fluxOI145.getUpperLimit() + "'" +
                            ", 'OI145', " + fluxOI145.getValore() + ", '" + fluxOI145.getAperture() +"', '" + fluxOI145.getRef160um() + "'," +
                            fluxOI145.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;

                try {
                    ContFluxHP fluxCII158 = new ContFluxHP(nextLine[0], "CII158",
                            nextLine[18], nextLine[17], nextLine[21], nextLine[20], nextLine[19]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxCII158.getNomeGalassia() + "', '" + fluxCII158.getUpperLimit() + "'" +
                            ", 'CII158', " + fluxCII158.getValore() + ", '" + fluxCII158.getAperture() + "', '" + fluxCII158.getRef160um() + "'," +
                            fluxCII158.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        psqlDBHelper.closeConnection();

        System.err.println("Importato file flusso continuo");

        return i;

    }

    /**
     * Il metodo cancella la tabella relativa al flusso continuo se
     * presente nel database
     */
    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flussocontinuo")) {
            psqlDBHelper.deleteTable("flussocontinuo");
            psqlDBHelper.createTableFlussoContinuoHP();
        }
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVContFlux importCSVFlux = new ImportCSVContFlux();
        importCSVFlux.importFile("C:\\Users\\feder\\Desktop\\MRTable6_cont.csv");
    }
}
