package test;

import main.Control.StatisticsValuesRatioFluxControl;
import main.Helper.FluxDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 07/01/2017.
 */

@RunWith(Parameterized.class)
public class TestStatSpectralGroup {

    private FluxDAO fluxDAO;
    private String fluxType;
    private String lineSelected;
    private Double meanValue;
    private Double devStand;
    private Double median;
    private Double devMedAss;
    private String aperture;

    @Before
    public void setup() {
        fluxDAO = new FluxDAO();
    }

    public TestStatSpectralGroup (String fluxType, String lineSelected, String aperture,
                                  Double meanValue, Double devStand, Double median, Double devMedAss) {
        this.fluxType = fluxType;
        this.lineSelected = lineSelected;
        this.meanValue = meanValue;
        this.devStand = devStand;
        this.median = median;
        this.devMedAss = devMedAss;
        this.aperture = aperture;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"flussorighehp", "OI63", "No", 589.0473301974233, 965.6306103931474, 35.42, 593.9698577329768},
                                         {"flussorighehp", "OI63", "c", 51.61584954087926, 118.0907243910876, 9.57, 58.46603112438758}};
        return Arrays.asList(data);
    }

    @Test
    public void test() {
        StatisticsValuesRatioFluxControl statisticsValuesRatioFluxControl = new StatisticsValuesRatioFluxControl();
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<Double> listTest;
        if(aperture.equals("No"))
            list = fluxDAO.retrieveValLineDB(fluxType, lineSelected);
        else if(aperture.equals("c"))
            list = fluxDAO.retrieveValLineDB(fluxType, lineSelected, aperture);

        listTest = statisticsValuesRatioFluxControl.computeAllValues(list);

        Assert.assertEquals(listTest.get(0), meanValue);
        Assert.assertEquals(listTest.get(1), devStand);
        Assert.assertEquals(listTest.get(2), median);
        Assert.assertEquals(listTest.get(3), devMedAss);

    }

}
