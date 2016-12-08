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
        /*false*/
        result = psqlDBHelper.checkUser("oiopo", "passmape");
        Assert.assertEquals(result, false);
    }

    public static void main(String[] args) {
        TestLogin testLogin = new TestLogin();
        testLogin.testLogin();
    }

}
