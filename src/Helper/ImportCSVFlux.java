package Helper;

import Model.Distanza;
import Model.Flux;
import Model.Metallicita;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by feder on 23/12/2016.
 */
public class ImportCSVFlux extends ImportCSV {
    @Override
    public void importFile(String path) {

        resetDB();

        CSVReader reader = null;
        String[] nextLine;
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
                if(nextLine.length != 23)
                    continue;

                Flux fluxOIII52 = new Flux(nextLine[0], "rHP", "OIII52",
                        nextLine[2], nextLine[1], nextLine[22], nextLine[3]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxOIII52.getNomeGalassia() + "', " + "'rHP', '" +
                        fluxOIII52.getUpperLimit() + "', 'OIII52'," + fluxOIII52.getValore() + ", '" +
                        fluxOIII52.getAperture() + "', " + fluxOIII52.getError() + ");");

                Flux fluxNIII = new Flux(nextLine[0], "rHP", "NIII57",
                        nextLine[5], nextLine[4], nextLine[22], nextLine[6]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) " + "VALUES ('" + fluxNIII.getNomeGalassia() + "', 'rHP', '" + fluxNIII.getUpperLimit() +
                        "'" + ", 'NIII57', " + fluxNIII.getValore() + ", '" + fluxNIII.getAperture() + "'," +
                        fluxNIII.getError() + ");");

                Flux fluxOI = new Flux(nextLine[0], "rHP", "OI63",
                        nextLine[8], nextLine[7], nextLine[22], nextLine[9]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) " + "VALUES ('" + fluxOI.getNomeGalassia() + "', 'rHP', '" + fluxOI.getUpperLimit() +
                        "'" + ", 'OI63', " + fluxOI.getValore() + ", '" + fluxOI.getAperture() + "'," +
                        fluxOI.getError() + ");");

                Flux fluxOIII88 = new Flux(nextLine[0], "rHP", "OIII88",
                        nextLine[11], nextLine[10], nextLine[22], nextLine[12]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxOIII88.getNomeGalassia() + "', 'rHP', '" + fluxOIII88.getUpperLimit() +
                        "', 'OIII88', " + fluxOIII88.getValore() + ", '" + fluxOIII88.getAperture() + "'," +
                        fluxOIII88.getError() + ");");

                Flux fluxNII122 = new Flux(nextLine[0], "rHP", "NIII122",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[15]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxNII122.getNomeGalassia() + "', 'rHP', '" + fluxNII122.getUpperLimit() +
                        "', 'NIII122', " + fluxNII122.getValore() + ", '" + fluxNII122.getAperture() + "'," +
                        fluxNII122.getError() + ");");

                Flux fluxOI145 = new Flux(nextLine[0], "rHP", "OI145",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[18]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore," +
                        "aperture, errore) VALUES ('" + fluxOI145.getNomeGalassia() + "', 'rHP', '" +
                        fluxOI145.getUpperLimit() + "', 'OI145', " + fluxOI145.getValore() + ", '" + fluxOI145.getAperture() +
                        "', " + fluxOI145.getError() + ");");

                Flux fluxCII158 = new Flux(nextLine[0], "rHP", "CII158",
                        nextLine[14], nextLine[13], nextLine[22], nextLine[21]);
                psqlDBHelper.insertRecord("INSERT INTO flusso(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, errore) VALUES ('" + fluxCII158.getNomeGalassia() + "', 'rHP', '" + fluxCII158.getUpperLimit() +
                        "', 'CII158', " + fluxCII158.getValore() + ", '" + fluxCII158.getAperture() + "'," + fluxCII158.getError() + ");");
                System.out.println("DOPO GALASSIA");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        psqlDBHelper.closeConnection();

        System.out.println("In importFile flux");
    }

    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flusso"))
            psqlDBHelper.deleteTable("flusso");
        psqlDBHelper.createTableFlusso();
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVFlux importCSVFlux = new ImportCSVFlux();
        importCSVFlux.importFile("C:\\Users\\feder\\Desktop\\MRTable4_flux.csv");
    }
}
