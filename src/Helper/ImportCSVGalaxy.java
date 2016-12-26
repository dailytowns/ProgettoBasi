package Helper;

import Model.Distanza;
import Model.Metallicita;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by feder on 23/12/2016.
 */
public class ImportCSVGalaxy extends ImportCSV {

    /**
     *
     * @param path Percorso del file da importare
     */
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
                if(nextLine.length != 26)
                    continue;
                System.out.println("headerLine " + nextLine[0]);
                System.out.println(nextLine.length);
                System.out.println("Prima di sql");

                psqlDBHelper.insertRecord("INSERT INTO galassia(nome, nomealt, redshift, classespettrale) VALUES ('"
                        + nextLine[0] + "', '" + nextLine[25] + "', " + nextLine[8] + ", '" + nextLine[11] + "');");
                System.out.println("DOPO GALASSIA");


                 /*   psqlDBHelper.insertRecord("INSERT INTO declinazione(nomegalassia, decsign, decdeg, decmin, decsec) VALUES ('"
                            + nextLine[0] + "','" + nextLine[4] + "', " + nextLine[5] + ", " + nextLine[6] + ", " + nextLine[7] + ");");
                System.out.println("DOPO DECLINAZIONE");
                    psqlDBHelper.insertRecord("INSERT INTO ascensioneretta(nomegalassia, ARh, ARm, ARs) VALUES ('"
                        + nextLine[0] + "', " + nextLine[1] + ", " + nextLine[2] + ", " + nextLine[3] + ");");
                System.out.println("DOPO ASCENSIONE");*/

                 try {
                     psqlDBHelper.insertRecord("INSERT INTO coordinateangolari(nomegalassia, ARh, ARm, ARs, decsign, decdeg, decmin, decsec) VALUES ('"
                             + nextLine[0] + "'," + nextLine[1] + ", " + nextLine[2] + ", " + nextLine[3] + ", '" +
                             nextLine[4] + "', " + nextLine[5] + ", " + nextLine[6] + ", " + nextLine[7] + ");");
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

                Metallicita metallicita = new Metallicita(nextLine[0], nextLine[22], nextLine[23], nextLine[24]);
                psqlDBHelper.insertRecord("INSERT INTO caratteristichefisiche(nomegalassia, tipologia, valore, errore, "
                        + "riferimento) VALUES('" + nextLine[0] + "', " + "'metallicita', " + metallicita.getValore() + ", "
                        + metallicita.getErrore() + ", " + metallicita.getRiferimento() + ");");
                System.out.println("DOPO METALLO");

                /*psqlDBHelper.insertRecord("INSERT INTO caratteristichefisiche(nomegalassia, tipologia, valore, errore, "
                        + "riferimento) VALUES('" + nextLine[0] + "', " + "'metallicita', " + nextLine[22] + ", "
                        + nextLine[23] + ", " + nextLine[24]+ ");");
                System.out.println("DOPO METALLO");*/

                Distanza distanza = new Distanza(nextLine[9], nextLine[10]);
                psqlDBHelper.insertRecord("INSERT INTO distanza(nomegalassia, valore, riferimento) VALUES('" +
                        nextLine[0] + "', " + distanza.getValore() + ", " + distanza.getRiferimento() + ");");
                System.out.println("DOPO DISTANZA");
                System.out.println("Dopo sql");
/*                psqlDBHelper.insertRecord("INSERT INTO distanza(nomegalassia, valore, riferimento) VALUES('" +
                        nextLine[0] + "', " + nextLine[9] + ", " + nextLine[10] + ");");
                System.out.println("DOPO DISTANZA");
                System.out.println("Dopo sql");*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        psqlDBHelper.closeConnection();
    }

    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("coordinateangolari"))
            psqlDBHelper.deleteTable("coordinateangolari");
        if(psqlDBHelper.checkTable("caratteristichefisiche"))
            psqlDBHelper.deleteTable("caratteristichefisiche");
        if(psqlDBHelper.checkTable("distanza"))
            psqlDBHelper.deleteTable("distanza");
        if(psqlDBHelper.checkTable("galassia"))
            psqlDBHelper.deleteTable("galassia");
        psqlDBHelper.createTableGalassia();
        psqlDBHelper.createTableCoordinateAngolari();
        psqlDBHelper.createTableCaratteristiche();
        psqlDBHelper.createTableDistanza();
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVGalaxy importCSVGalaxy = new ImportCSVGalaxy();
        importCSVGalaxy.importFile("C:\\Users\\feder\\Desktop\\MRTable3_Sample.csv");
    }
}
