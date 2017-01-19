package test;

import main.Control.SearchRatioFluxViewControl;
import main.Model.Flux;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 07/01/2017.
 */
@RunWith(Parameterized.class)
public class TestValueRatioFlux {

    private String galaxyName;
    private String atom1;
    private String atom2;
    private Double ratio;

    public TestValueRatioFlux(String galaxyName, String atom1, String atom2, Double ratio){
        this.galaxyName = galaxyName;
        this.atom1 = atom1;
        this.atom2 = atom2;
        this.ratio = ratio;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"M82", "OIII88", "OI63", 5.4612053729232946},
                                            {"IZw1", "OI63", "OI63", 3.1}};
        return Arrays.asList(data);
    }

    @Test
    public void testValueRatioFlux() {

        SearchRatioFluxViewControl searchRatioFluxViewControl = new SearchRatioFluxViewControl();
        Flux flux1 = searchRatioFluxViewControl.retrieveValueDB(galaxyName, atom1, "flussorighehp");
        Flux flux2 = searchRatioFluxViewControl.retrieveValueDB(galaxyName, atom2, "flussocontinuo");

        Assert.assertEquals(ratio, Double.valueOf(flux1.getValore()/flux2.getValore()));
    }
}
