package main.Helper;

import main.Model.*;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe addetta all'import del file contenente
 * utenti registrati
 */
public class ImportCSVUser extends ImportCSV {


    @Override
    public int importFile(String path) {

        resetDB();

        CSVReader reader = null;
        String[] nextLine;
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        String[] headerLine;

        int i = 0; //Contatore delle righe inserite utilizzato per testing
        try {
            reader = new CSVReader(new FileReader(path), ';');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((nextLine = reader.readNext()) != null) {
                if(nextLine.length != 5)
                    continue;

                UserHib user = new UserHib(nextLine[3], nextLine[4], nextLine[0], nextLine[1], nextLine[2]);
                psqlDBHelper.insertRecord("INSERT INTO registereduser(name,surname,email,userid,password) VALUES ('" +
                        user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getUserid() +
                        "', '" + user.getPassword() + "');");
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Importato il file degli utenti");
        psqlDBHelper.closeConnection();

        return i;
    }

    /**
     * Il metodo cancella la tabella relativa al flusso continuo se
     * presente nel database
     */
    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("registereduser")) {
            psqlDBHelper.deleteTable("registereduser");
            psqlDBHelper.createTableUser();
        } else {
            psqlDBHelper.createTableUser();
        }
        psqlDBHelper.closeConnection();
    }

    public static void main(String[] args) {
        ImportCSVUser importCSVUser = new ImportCSVUser();
        importCSVUser.importFile("C:\\Users\\feder\\Desktop\\UserFile.csv");
    }
}
