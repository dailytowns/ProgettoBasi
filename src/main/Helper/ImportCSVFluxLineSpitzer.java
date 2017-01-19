package main.Helper;

import main.Model.FluxSp;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementazione del Factory Method addetto all'import
 * di file sul flusso calcolato dal satellite Spitzer
 * @author Federico Amici
 */
public class ImportCSVFluxLineSpitzer extends ImportCSV {

    /**
     * Importa il file delle righe calcolate dal satellite
     * Spitzer
     * @param path Percorso del file da importare
     * @return Il numero di righe create
     */
    @Override
    public int importFile(String path) {
        resetDB();

        CSVReader reader = null;
        String[] nextLine = new String[0];
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
                if (nextLine.length != 30)
                    continue;

                FluxSp fluxSpSIV = new FluxSp(nextLine[0], "SIV",
                        nextLine[2], nextLine[1], nextLine[3], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIV.getNomeGalassia() + "', '" +
                        fluxSpSIV.getUpperLimit() + "', 'SIV'," + fluxSpSIV.getValore() + ", "
                        + fluxSpSIV.getError() + ", '" + fluxSpSIV.getIRSMode() + "');");

                i++;

                FluxSp fluxSpNeII = new FluxSp(nextLine[0], "NeII",
                        nextLine[5], nextLine[4], nextLine[6], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeII.getNomeGalassia() + "', '" +
                fluxSpNeII.getUpperLimit() + "', 'NeII', " +fluxSpNeII.getValore() + ", "
                + fluxSpNeII.getError() + ", '" + fluxSpNeII.getIRSMode() + "');");

                i++;

                FluxSp fluxSpNeV14 = new FluxSp(nextLine[0], "NeV14.3",
                        nextLine[8], nextLine[7], nextLine[9], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeV14.getNomeGalassia() + "', '" +
                        fluxSpNeV14.getUpperLimit() + "', 'NeV14.3', " +fluxSpNeV14.getValore() + ", "
                        + fluxSpNeV14.getError() + ", '" + fluxSpNeV14.getIRSMode() + "');");

                i++;

                FluxSp fluxSpNeIII = new FluxSp(nextLine[0], "NeII",
                        nextLine[11], nextLine[10], nextLine[12], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeIII.getNomeGalassia() + "', '" +
                        fluxSpNeIII.getUpperLimit() + "', 'NeIII', " +fluxSpNeIII.getValore() + ", "
                        + fluxSpNeIII.getError() + ", '" + fluxSpNeIII.getIRSMode() + "');");

                i++;

                FluxSp fluxSpSIII = new FluxSp(nextLine[0],"SIII18.7",
                        nextLine[14], nextLine[13], nextLine[15], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIII.getNomeGalassia() + "', '" +
                        fluxSpSIII.getUpperLimit() + "', 'SIII18.7', " +fluxSpSIII.getValore() + ", "
                        + fluxSpSIII.getError() + ", '" + fluxSpSIII.getIRSMode() + "');");

                i++;

                FluxSp fluxSpNeV24 = new FluxSp(nextLine[0], "NeV24.3",
                        nextLine[17], nextLine[16], nextLine[18], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeV24.getNomeGalassia() + "', '" +
                        fluxSpNeV24.getUpperLimit() + "', 'NeV24.3', " +fluxSpNeV24.getValore() + ", "
                        + fluxSpNeV24.getError() + ", '" + fluxSpNeV24.getIRSMode() + "');");

                i++;

                FluxSp fluxSpOIV = new FluxSp(nextLine[0], "OIV",
                        nextLine[20], nextLine[19], nextLine[21], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpOIV.getNomeGalassia() + "', '" +
                        fluxSpOIV.getUpperLimit() + "', 'OIV', " +fluxSpOIV.getValore() + ", "
                        + fluxSpOIV.getError() + ", '" + fluxSpOIV.getIRSMode() + "');");


                i++;

                FluxSp fluxSpSIII33 = new FluxSp(nextLine[0],"SIII33.5",
                        nextLine[23], nextLine[22], nextLine[24], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIII33.getNomeGalassia() + "', '" +
                        fluxSpSIII33.getUpperLimit() + "', 'SIII33.5', " +fluxSpSIII33.getValore() + ", "
                        + fluxSpSIII33.getError() + ", '" + fluxSpSIII33.getIRSMode() + "');");

                i++;

                FluxSp fluxSpSiII = new FluxSp(nextLine[0], "SiII",
                        nextLine[26], nextLine[25], nextLine[27], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussorighesp(nomegalassia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSiII.getNomeGalassia() + "', '" +
                        fluxSpSiII.getUpperLimit() + "', 'SiII', " +fluxSpSiII.getValore() + ", "
                        + fluxSpSiII.getError() + ", '" + fluxSpSiII.getIRSMode() + "');");

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Importato file flusso spitzer");
        psqlDBHelper.closeConnection();

        return i;
    }

    /**
     * Il metodo cancella la tabella relativa al flusso Spitzer se
     * presente nel database
     */
    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flussorighesp")) {
            psqlDBHelper.deleteTable("flussorighesp");
            psqlDBHelper.createTableFlussoSp();
        }
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVFluxLineSpitzer importCSVFluxLineSpitzer = new ImportCSVFluxLineSpitzer();
        importCSVFluxLineSpitzer.importFile("C:\\Users\\feder\\Desktop\\MRTable8_irs.csv");
    }

}
