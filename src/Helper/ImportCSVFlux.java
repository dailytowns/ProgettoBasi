package Helper;

/**
 * Created by feder on 23/12/2016.
 */
public class ImportCSVFlux extends ImportCSV {
    @Override
    public void importFile(String path) {


        System.out.println("In importFile flux");
    }

    private void resetDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(psqlDBHelper.checkTable("flusso"))
            psqlDBHelper.deleteTable("flusso");
        psqlDBHelper.createTableFlusso();
        psqlDBHelper.createTableAtomo();
        psqlDBHelper.closeConnection();
    }
}
