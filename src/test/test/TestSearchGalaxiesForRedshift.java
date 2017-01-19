package test;

import main.Control.SearchGalaxyForRedshiftControl;
import main.Model.GalaxyData;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 06/01/2017.
 */
@RunWith(Parameterized.class)
public class TestSearchGalaxiesForRedshift {

    private Double redshift;
    private String lgt;
    private int listSize;

    public TestSearchGalaxiesForRedshift(Double redshift, String lgt, int listSize) {
        this.redshift = redshift;
        this.lgt = lgt;
        this.listSize = listSize;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{0.001, "<=", 13}, {90.0, ">=", 0}};
        return Arrays.asList(data);
    }

    @Test
    public void testSearchGalaxiesForRedshift() {

        SearchGalaxyForRedshiftControl searchGalaxyForRedshiftControl = new SearchGalaxyForRedshiftControl();
        ObservableList<GalaxyData> list = searchGalaxyForRedshiftControl.retrieveGalaxiesForRedshift(redshift, lgt);
        Assert.assertEquals(list.size(), listSize);

    }

}
