package test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Helper.ImportCSVGalaxy;
import main.Helper.PsqlDBHelper;

@RunWith(Parameterized.class)
public class TestImportCSVGalaxy {

	private PsqlDBHelper psqlDBHelper;
	private String path;
	
	@Before
	public void setup() {
		psqlDBHelper = new PsqlDBHelper();
	}

	public TestImportCSVGalaxy (String path) {
		this.path = path;
	}

	@Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"C:\\Users\\feder\\Desktop\\MRTable3_sample.csv"},
                {"C:\\temp\\MRTable3_sample.csv"}};
        return Arrays.asList(data);
    }
	
    @Test
    public void testImportFile() throws Exception {
        ImportCSVGalaxy importCSVGalaxy = new ImportCSVGalaxy();
        int i = importCSVGalaxy.importFile(path);
        int j = psqlDBHelper.countRowsTable("galassia");
        j += psqlDBHelper.countRowsTable("coordinateangolari");
        j += psqlDBHelper.countRowsTable("caratteristichefisiche");
        System.out.println(i);
        System.out.println(j);
        Assert.assertEquals(i, j);
        psqlDBHelper.closeConnection();
        System.err.println("Info TestImportCSVGalaxy: Il test non ha individuato errori");
    }

    
}
