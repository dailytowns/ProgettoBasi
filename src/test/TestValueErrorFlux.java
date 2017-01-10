package test;

import Helper.FluxDAO;
import Model.FluxCellData;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 07/01/2017.
 */
@RunWith(Parameterized.class)
public class TestValueErrorFlux {

    private String galaxyName;
    private String[] atoms;
    private String table;
    private FluxDAO fluxDAO;
    private Double error;

    @Before
    public void setup() {
        fluxDAO = new FluxDAO();
    }

    public TestValueErrorFlux(String galaxyName, String[] atoms, String table, Double error) {
        this.galaxyName = galaxyName;
        this.atoms = atoms;
        this.table = table;
        this.error = error;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"M82", new String[]{"OIII88", "SIV"}, "flussorighehp", 60.1},
                                            {"M82", new String[]{"OIII88", "SIV"}, "flussorighesp", 0.74},
                                            {"M82", new String[]{"OIII88", "SIV"}, "flussocontinuo", 7.3}};
        return Arrays.asList(data);
    }

    @Test
    public void testValueErrorFlux() {

        ObservableList<FluxCellData> obs = fluxDAO.retrieveValErrFluxDB(galaxyName, atoms, table);
        Assert.assertEquals(obs.get(0).getError(), error);
        fluxDAO.closeConnection();

    }

}
