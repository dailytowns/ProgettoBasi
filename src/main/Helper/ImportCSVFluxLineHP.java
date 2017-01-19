package main.Helper;

import main.Model.FluxHP;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Si occupa dell'import dei file .csv contenenti
 * dati sul flusso continuo registrati dal satellite
 * Herschel/PACS
 * @author Federico Amici
 */
public class ImportCSVFluxLineHP extends ImportCSV {

    /**
     * Importa il file contenenti dati sul flusso continuo
     * registrati dal satellite Herschel/PACS
     * @param path Percorso del file da importare
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
                if(nextLine.length != 23)
                    continue;

                FluxHP fluxHPOIII52 = new FluxHP(nextLine[0], "OIII52",
                        nextLine[2], nextLine[1], nextLine[22], nextLine[3]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxHPOIII52.getNomeGalassia() + "', '" +
                        fluxHPOIII52.getUpperLimit() + "', 'OIII52'," + fluxHPOIII52.getValore() + ", '" +
                        fluxHPOIII52.getAperture() + "', " + fluxHPOIII52.getError() + ");");

                i++;

                FluxHP fluxHPNIII = new FluxHP(nextLine[0], "NIII57",
                        nextLine[5], nextLine[4], nextLine[22], nextLine[6]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) " + "VALUES ('" + fluxHPNIII.getNomeGalassia() + "', '" + fluxHPNIII.getUpperLimit() +
                        "'" + ", 'NIII57', " + fluxHPNIII.getValore() + ", '" + fluxHPNIII.getAperture() + "'," +
                        fluxHPNIII.getError() + ");");

                i++;

                FluxHP fluxHPOI = new FluxHP(nextLine[0], "OI63",
                        nextLine[8], nextLine[7], nextLine[22], nextLine[9]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) " + "VALUES ('" + fluxHPOI.getNomeGalassia() + "', '" + fluxHPOI.getUpperLimit() +
                        "'" + ", 'OI63', " + fluxHPOI.getValore() + ", '" + fluxHPOI.getAperture() + "'," +
                        fluxHPOI.getError() + ");");

                i++;

                FluxHP fluxHPOIII88 = new FluxHP(nextLine[0], "OIII88",
                        nextLine[11], nextLine[10], nextLine[22], nextLine[12]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxHPOIII88.getNomeGalassia() + "', '" + fluxHPOIII88.getUpperLimit() +
                        "', 'OIII88', " + fluxHPOIII88.getValore() + ", '" + fluxHPOIII88.getAperture() + "'," +
                        fluxHPOIII88.getError() + ");");

                i++;

                FluxHP fluxHPNII122 = new FluxHP(nextLine[0], "NIII122",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[15]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxHPNII122.getNomeGalassia() + "', '" + fluxHPNII122.getUpperLimit() +
                        "', 'NIII122', " + fluxHPNII122.getValore() + ", '" + fluxHPNII122.getAperture() + "'," +
                        fluxHPNII122.getError() + ");");

                i++;

                FluxHP fluxHPOI145 = new FluxHP(nextLine[0], "OI145",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[18]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore," +
                        "aperture, errore) VALUES ('" + fluxHPOI145.getNomeGalassia() + "', '" +
                        fluxHPOI145.getUpperLimit() + "', 'OI145', " + fluxHPOI145.getValore() + ", '" + fluxHPOI145.getAperture() +
                        "', " + fluxHPOI145.getError() + ");");

                i++;

                FluxHP fluxHPCII158 = new FluxHP(nextLine[0], "CII158",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[21]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighehp(nomegalassia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxHPCII158.getNomeGalassia() + "', '" + fluxHPCII158.getUpperLimit() +
                        "', 'CII158', " + fluxHPCII158.getValore() + ", '" + fluxHPCII158.getAperture() + "'," + fluxHPCII158.getError() + ");");

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Importato file flusso HP");
        psqlDBHelper.closeConnection();

        return i;
    }

    /**
     * Il metodo cancella la tabella relativa al flusso continuo se
     * presente nel database
     */
    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flussorighehp")) {
            psqlDBHelper.deleteTable("flussorighehp");
            psqlDBHelper.createTableFlussoRigheHP();
        }
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVFluxLineHP importCSVFluxLineHP = new ImportCSVFluxLineHP();
        importCSVFluxLineHP.importFile("C:\\Users\\feder\\Desktop\\MRTable4_flux.csv");
    }
}
