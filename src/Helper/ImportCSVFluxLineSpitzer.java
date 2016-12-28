package Helper;

import Model.FluxHP;
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
