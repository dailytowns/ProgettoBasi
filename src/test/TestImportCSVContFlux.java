package test;

import Helper.ImportCSVContFlux;
import Helper.PsqlDBHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.postgresql.util.PSQLException;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 06/01/2017.
 */
@RunWith(Parameterized.class)
public class TestImportCSVContFlux {

    private PsqlDBHelper psqlDBHelper;
    private String path;

    @Before
    public void setup() {
        psqlDBHelper = new PsqlDBHelper();
    }

    public TestImportCSVContFlux(String path) {
        this.path = path;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"C:\\Users\\feder\\Desktop\\MRTable6_cont.csv"},
                {"C:\\temp\\MRTable6_cont.csv"}};
        return Arrays.asList(data);
    }

    @Test
    public void testImportFile() throws Exception {
        ImportCSVContFlux importCSVContFlux = new ImportCSVContFlux();
        int i = importCSVContFlux.importFile(path);
        int j = psqlDBHelper.countRowsTable("flussocontinuo");
        Assert.assertEquals(i, j);
        psqlDBHelper.closeConnection();
        System.err.println("Info TestImportCSVContFlux: Il test non ha individuato errori");
    }

}
