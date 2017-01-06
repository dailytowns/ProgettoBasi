package test;

import Control.InsertUserControl;
import Helper.PsqlDBHelper;
import Helper.UserDAO;
import Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.postgresql.util.PSQLException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by feder on 06/01/2017.
 */

@RunWith(Parameterized.class)
public class TestInsertUser {

    private String name;
    private String surname;
    private String email;
    private String userId;
    private String password;
    public Class<? extends Exception> expectedException;
    private PsqlDBHelper psqlDBHelper;
    private int rows;

    @Before
    public void setup() {
        psqlDBHelper = new PsqlDBHelper();
        rows = psqlDBHelper.countRowsTable("registereduser");
    }

    public TestInsertUser(String name, String surname, String email, String userid, String password,
                              Class<? extends Exception> expectedException) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userId = userid;
        this.password = password;
        this.expectedException = expectedException;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{"Giuseppe", "Verdi", "mail@verdi", "giuseppeverdi", "passverdolino27", PSQLException.class},
                {"Mario", "Rossi", "mail@mario", "usermario", "passmario020", PSQLException.class}};
        return Arrays.asList(data);
    }

    @Test
    public void testInsert() {

        System.err.println("in test insert");
        UserDAO userDAO = new UserDAO();
        boolean bool = InsertUserControl.checkUserAndPassword(userId, password);
        User user = new User(name, surname, email, userId, password);

        userDAO.insertUser(user, "registereduser");
        int j = psqlDBHelper.countRowsTable("registereduser");

        /*Se è stata sollevata una eccezione o se non sono state rispettate le regole di vincolo
        * il numero delle righe nella tabella deve esserem lo stesso*/
        if(expectedException != null || !bool) {
            Assert.assertEquals(rows, j);
        } else {
            Assert.assertEquals(++rows, j);
        }
    }

}
