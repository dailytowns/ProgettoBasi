package Helper;

import Model.FluxSp;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by feder on 27/12/2016.
 */
public class ImportCSVFluxLineSpitzer extends ImportCSV {
    @Override
    public void importFile(String path) {
        resetDB();

        CSVReader reader = null;
        String[] nextLine = new String[0];
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        String[] headerLine;

        int i = 0;
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

                FluxSp fluxSpSIV = new FluxSp(nextLine[0], "rSp", "SIV",
                        nextLine[2], nextLine[1], nextLine[3], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIV.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpSIV.getUpperLimit() + "', 'SIV'," + fluxSpSIV.getValore() + ", "
                        + fluxSpSIV.getError() + ", '" + fluxSpSIV.getIRSMode() + "');");

                FluxSp fluxSpNeII = new FluxSp(nextLine[0], "rSp", "NeII",
                        nextLine[5], nextLine[4], nextLine[6], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeII.getNomeGalassia() + "', 'rSp', '" +
                fluxSpNeII.getUpperLimit() + "', 'NeII', " +fluxSpNeII.getValore() + ", "
                + fluxSpNeII.getError() + ", '" + fluxSpNeII.getIRSMode() + "');");

                FluxSp fluxSpNeV14 = new FluxSp(nextLine[0], "rSp", "NeV14",
                        nextLine[8], nextLine[7], nextLine[9], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeV14.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpNeV14.getUpperLimit() + "', 'NeV14', " +fluxSpNeV14.getValore() + ", "
                        + fluxSpNeV14.getError() + ", '" + fluxSpNeV14.getIRSMode() + "');");

                FluxSp fluxSpNeIII = new FluxSp(nextLine[0], "rSp", "NeII",
                        nextLine[11], nextLine[10], nextLine[12], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeIII.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpNeIII.getUpperLimit() + "', 'NeIII', " +fluxSpNeIII.getValore() + ", "
                        + fluxSpNeIII.getError() + ", '" + fluxSpNeIII.getIRSMode() + "');");

                FluxSp fluxSpSIII = new FluxSp(nextLine[0], "rSp", "SIII",
                        nextLine[14], nextLine[13], nextLine[15], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIII.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpSIII.getUpperLimit() + "', 'SIII', " +fluxSpSIII.getValore() + ", "
                        + fluxSpSIII.getError() + ", '" + fluxSpSIII.getIRSMode() + "');");

                FluxSp fluxSpNeV24 = new FluxSp(nextLine[0], "rSp", "NeV24",
                        nextLine[17], nextLine[16], nextLine[18], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpNeV24.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpNeV24.getUpperLimit() + "', 'NeV24', " +fluxSpNeV24.getValore() + ", "
                        + fluxSpNeV24.getError() + ", '" + fluxSpNeV24.getIRSMode() + "');");

                FluxSp fluxSpOIV = new FluxSp(nextLine[0], "rSp", "OIV",
                        nextLine[20], nextLine[19], nextLine[21], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpOIV.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpOIV.getUpperLimit() + "', 'OIV', " +fluxSpOIV.getValore() + ", "
                        + fluxSpOIV.getError() + ", '" + fluxSpOIV.getIRSMode() + "');");


                FluxSp fluxSpSIII33 = new FluxSp(nextLine[0], "rSp", "SIII33",
                        nextLine[23], nextLine[22], nextLine[24], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSIII33.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpSIII33.getUpperLimit() + "', 'SIII33', " +fluxSpSIII33.getValore() + ", "
                        + fluxSpSIII33.getError() + ", '" + fluxSpSIII33.getIRSMode() + "');");

                FluxSp fluxSpSiII = new FluxSp(nextLine[0], "rSp", "SiII",
                        nextLine[26], nextLine[25], nextLine[27], nextLine[28]);
                psqlDBHelper.insertRecord("INSERT INTO flussoSp(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "errore, irsmode) VALUES ('" + fluxSpSiII.getNomeGalassia() + "', 'rSp', '" +
                        fluxSpSiII.getUpperLimit() + "', 'SiII', " +fluxSpSiII.getValore() + ", "
                        + fluxSpSiII.getError() + ", '" + fluxSpSiII.getIRSMode() + "');");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flussosp"))
            psqlDBHelper.deleteTable("flussosp");
        psqlDBHelper.createTableFlussoSp();
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVFluxLineSpitzer importCSVFluxLineSpitzer = new ImportCSVFluxLineSpitzer();
        importCSVFluxLineSpitzer.importFile("C:\\Users\\feder\\Desktop\\MRTable8_irs.csv");
    }

}
