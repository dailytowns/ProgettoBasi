package test;

import Helper.GalaxyDAO;
import Model.Galaxy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by feder on 06/01/2017.
 */
@RunWith(Parameterized.class)
public class TestSearchGalaxyForName {

    private GalaxyDAO galaxyDAO;
    private String name;
    private boolean found;

    @Before
    public void setup() {
        galaxyDAO = new GalaxyDAO();
    }

    public TestSearchGalaxyForName(String name, boolean found) {
        this.name = name;
        this.found = found;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"Mrk334", true}, {"unNomeQualsiasi", false}};
        return Arrays.asList(data);
    }

    @Test
    public void testSearchGalaxyForName() {
        //Galaxy galaxy = galaxyDAO.searchGalaxyForName(name);
        //Assert.assertEquals(found, galaxy != null);
    }

}
