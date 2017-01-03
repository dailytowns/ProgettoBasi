package Helper;

import Model.User;

import java.sql.*;
import java.util.Properties;

/**
 * @author Federico Amici
 * La classe si occupa di gestire il CRUD User
 */
public class UserDAO {

    private Connection conn;

    public UserDAO() {
        openConnection();
    }

    private void openConnection() {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "portento123");

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(String user, String password) {
        boolean authenticated = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registereduser WHERE userid ='" + user + "' AND " +
                    "password = '" + password + "'");
            int count = 0;

            while (rs.next())
                count++;

            authenticated = count == 1;

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public boolean checkAdmin(String user, String password) {
        boolean authenticated = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM amministratore WHERE userid ='" + user + "' AND " +
                    "password = '" + password + "'");
            int count = 0;

            while (rs.next())
                count++;

            authenticated = count == 1;

            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public void insertUser(User user, String table) {
        String sql = "INSERT INTO " + table + " (\"name\", \"surname\", \"email\", \"userid\", \"password\") VALUES ('"
                + user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getUserId() +
                "', '" + user.getPassword() +"');";

        Statement st = null;
        try {
            Class.forName("org.postgresql.Driver");
            st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
