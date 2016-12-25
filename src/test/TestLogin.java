package test;

import Helper.PsqlDBHelper;
import org.junit.*;

/**
 * Created by feder on 07/12/2016.
 */
public class TestLogin {

    @Test
    public void testLogin(){
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        /*true*/
        boolean result = psqlDBHelper.checkUser("usermario", "passmario");
        Assert.assertEquals(result, true);

        psqlDBHelper = new PsqlDBHelper();
        /*false*/
        result = psqlDBHelper.checkUser("oiopo", "passmape");
        Assert.assertEquals(result, false);
    }

    /*Eccezione se il dbms è chiuso*/
    public static void main(String[] args) {
        TestLogin testLogin = new TestLogin();
        testLogin.testLogin();
        System.err.println("INFO: Il test non ha individuato errori");
    }

}
