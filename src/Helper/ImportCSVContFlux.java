package Helper;

import Model.ContFlux;
import Model.Flux;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by feder on 27/12/2016.
 */
public class ImportCSVContFlux extends ImportCSV {

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
                if(nextLine.length != 22)
                    continue;

                ContFlux fluxOIII52 = new ContFlux(nextLine[0], "cHP", "OIII52",
                        nextLine[1], "NO", nextLine[21], nextLine[20], nextLine[2]);
                psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                        "aperture, ref160, errore) VALUES ('" + fluxOIII52.getNomeGalassia() + "', 'cHP', '" + fluxOIII52.getUpperLimit() + "', 'OIII52'," +
                        fluxOIII52.getValore() + ", '" + fluxOIII52.getAperture() + "', '" + fluxOIII52.getRef160um() + "'," + fluxOIII52.getError() + ");");


                try {
                    ContFlux fluxNIII = new ContFlux(nextLine[0], "cHP", "NIII57",
                            nextLine[3], "NO", nextLine[21], nextLine[20], nextLine[4]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxNIII.getNomeGalassia() + "', 'cHP', '" + fluxNIII.getUpperLimit() + "'" +
                            ", 'NIII57', " + fluxNIII.getValore() + ", '" + fluxNIII.getAperture() + "', '" + fluxNIII.getRef160um() + "', " +
                            fluxNIII.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ContFlux fluxOI = new ContFlux(nextLine[0], "cHP", "OI63",
                            nextLine[6], nextLine[5], nextLine[21], nextLine[20], nextLine[7]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOI.getNomeGalassia() + "', 'cHP', '" + fluxOI.getUpperLimit() + "'" +
                            ", 'OI63', " + fluxOI.getValore() + ", '" + fluxOI.getAperture() + "', '" + fluxOI.getRef160um() + "'," +
                            fluxOI.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ContFlux fluxOIII88 = new ContFlux(nextLine[0], "cHP", "OIII88",
                            nextLine[9], nextLine[8], nextLine[21], nextLine[20], nextLine[10]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOIII88.getNomeGalassia() + "', 'cHP', '" + fluxOIII88.getUpperLimit() + "'" +
                            ", 'OIII88', " + fluxOIII88.getValore() + ", '" + fluxOIII88.getAperture() + "', '" + fluxOIII88.getRef160um() + "'," +
                            fluxOIII88.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ContFlux fluxNII122 = new ContFlux(nextLine[0], "cHP", "NIII122",
                            nextLine[12], nextLine[11], nextLine[21], nextLine[20], nextLine[13]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxNII122.getNomeGalassia() + "', 'cHP', '" + fluxNII122.getUpperLimit() + "'" +
                            ", 'NIII122', " + fluxNII122.getValore() + ", '" + fluxNII122.getAperture() + "','" + fluxNII122.getRef160um() + "'," +
                            fluxNII122.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ContFlux fluxOI145 = new ContFlux(nextLine[0], "cHP", "OI145",
                            nextLine[15], nextLine[14], nextLine[21], nextLine[20], nextLine[16]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxOI145.getNomeGalassia() + "', 'cHP', '" + fluxOI145.getUpperLimit() + "'" +
                            ", 'OI145', " + fluxOI145.getValore() + ", '" + fluxOI145.getAperture() +"', '" + fluxOI145.getRef160um() + "'," +
                            fluxOI145.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ContFlux fluxCII158 = new ContFlux(nextLine[0], "cHP", "CII158",
                            nextLine[18], nextLine[17], nextLine[21], nextLine[20], nextLine[19]);
                    psqlDBHelper.insertRecord("INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, aperture, ref160, errore) " +
                            "VALUES ('" + fluxCII158.getNomeGalassia() + "', 'cHP', '" + fluxCII158.getUpperLimit() + "'" +
                            ", 'CII158', " + fluxCII158.getValore() + ", '" + fluxCII158.getAperture() + "', '" + fluxCII158.getRef160um() + "'," +
                            fluxCII158.getError() + ");");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        psqlDBHelper.closeConnection();

        System.out.println("In importFile flux");

    }

    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flussocontinuo"))
            psqlDBHelper.deleteTable("flussocontinuo");
        psqlDBHelper.createTableFlussoContinuo();
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVContFlux importCSVFlux = new ImportCSVContFlux();
        importCSVFlux.importFile("C:\\Users\\feder\\Desktop\\MRTable6_cont.csv");
    }
}
