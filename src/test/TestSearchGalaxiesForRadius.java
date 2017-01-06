package test;

import Control.SearchGalaxyForRadiusControl;
import Helper.GalaxyDAO;
import Model.Declination;
import Model.GalaxyDataRadius;
import Model.RightAscension;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;

/**
 * Created by feder on 06/01/2017.
 */

@RunWith(Parameterized.class)
public class TestSearchGalaxiesForRadius {

    private String sign;
    private Integer decDegrees;
    private Integer decMinutes;
    private Double decSeconds;
    private Integer arHours;
    private Integer arMinutes;
    private Double arSeconds;
    private boolean goodInput;
    private int numberRowsExpected;

    public TestSearchGalaxiesForRadius(Integer arHours, Integer arMinutes, Double arSeconds,
                                       String sign, Integer decDegrees, Integer decMinutes, Double decSeconds,
                                       boolean goodInput, int numberRowsExpected) {
        this.sign = sign;
        this.decDegrees = decDegrees;
        this.decMinutes = decMinutes;
        this.decSeconds = decSeconds;
        this.arHours = arHours;
        this.arMinutes = arMinutes;
        this.arSeconds = arSeconds;
        this.goodInput = goodInput;
        this.numberRowsExpected = numberRowsExpected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{4, 5, 6.0, "+", 4, 5, 6.0, true, 8},
                                            {25, 3, 4.0, "-", 93, 65, 3.0, false, 0}};
        return Arrays.asList(data);
    }

    @Test
    public void testSearchForRadius() {
        SearchGalaxyForRadiusControl searchGalaxyForRadiusControl = new SearchGalaxyForRadiusControl();

        Declination d = new Declination(sign, decDegrees, decMinutes, decSeconds);
        RightAscension ra = new RightAscension(arHours, arMinutes, arSeconds);
        boolean b = searchGalaxyForRadiusControl.parseInput(ra, d);

        Assert.assertEquals(goodInput, b);

        if(b) {
            ObservableList<GalaxyDataRadius> list = searchGalaxyForRadiusControl.retrieveGalaxiesForRadius(d, ra, 10, 0.5);
            Assert.assertEquals(numberRowsExpected, list.size());
        }
    }
}
