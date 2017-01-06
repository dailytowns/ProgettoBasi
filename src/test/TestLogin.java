package test;

import Helper.PsqlDBHelper;
import Helper.UserDAO;
import org.junit.*;

/**
 * Created by feder on 07/12/2016.
 */
public class TestLogin {

    @Test
    public void testLogin(){
        UserDAO userDAO = new UserDAO();
        //*true*//
        boolean result = userDAO.checkUser("usermario", "passmario80");
        Assert.assertEquals(result, true);

        //*true*//
        userDAO = new UserDAO();
        result = userDAO.checkUser("pippobaudo", "pippobaudo73");
        Assert.assertEquals(result, false);

        //*false*//
        userDAO = new UserDAO();
        result = userDAO.checkUser("usermarione", "passmario80");
        Assert.assertEquals(result, false);

        userDAO.closeConnection();

        System.err.println("INFO TestLogin: Il test non ha individuato errori");
    }

    /*Eccezione se il dbms è chiuso*/
    @Test
    public void main() throws Exception {
        TestLogin testLogin = new TestLogin();
        testLogin.testLogin();
    }

}
