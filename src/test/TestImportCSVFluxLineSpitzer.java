package test;

import Helper.ImportCSVFluxLineSpitzer;
import Helper.PsqlDBHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by feder on 29/12/2016.
 */
public class TestImportCSVFluxLineSpitzer {
    @Test
    public void testImportFile() throws Exception {
        ImportCSVFluxLineSpitzer importCSVFluxLineSpitzer = new ImportCSVFluxLineSpitzer();
        int i = importCSVFluxLineSpitzer.importFile("C:\\Users\\feder\\Desktop\\MRTable8_irs.csv");
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        int j = psqlDBHelper.countRowsTable("flussorighesp");
        Assert.assertEquals(i, j);

        System.err.println("Info TestImportCSVFluxLineSpitzer: Il test non ha individuato errori");
    }

    @Test
    public void main() throws Exception {
        TestImportCSVFluxLineSpitzer testImportCSVFluxLineSpitzer = new TestImportCSVFluxLineSpitzer();
        testImportCSVFluxLineSpitzer.testImportFile();
    }

}