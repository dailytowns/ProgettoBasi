package test;

import main.Helper.ImportCSVFluxLineHP;
import main.Helper.PsqlDBHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by feder on 06/01/2017.
 */
public class TestImportCSVFluxLineHP {

    @Test
    public void testImportFile() throws Exception {
        ImportCSVFluxLineHP importCSVFluxLineHP = new ImportCSVFluxLineHP();
        int i = importCSVFluxLineHP.importFile("C:\\Users\\feder\\Desktop\\MRTable4_flux.csv");
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        int j = psqlDBHelper.countRowsTable("flussorighehp");
        Assert.assertEquals(i, j);

        System.err.println("Info TestImportCSVFluxLineHP: Il test non ha individuato errori");
    }

    @Test
    public void main() throws Exception {
        TestImportCSVFluxLineHP testImportCSVFluxLineHP = new TestImportCSVFluxLineHP();
        testImportCSVFluxLineHP.testImportFile();
    }


}
