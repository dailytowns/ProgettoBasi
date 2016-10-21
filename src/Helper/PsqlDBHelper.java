package Helper;

import Model.User;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by feder on 29/09/2016.
 */
public class PsqlDBHelper {

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user;
    private String password;
    private Connection conn;

    public PsqlDBHelper (String user, String password) {
        //this.url = url;
        this.user = user;
        this.password = password;
        openConnection();
    }

    private void openConnection() {

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "portento123");

        try {
            conn = DriverManager.getConnection(this.url, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(String url) {

    }

    public boolean checkUser() {
        boolean authenticated = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registereduser WHERE userid ='" + this.user + "' AND " +
                                                                                    "password = '" + this.password + "'");
            int count = 0;

            while (rs.next())
                count++;

            if(count == 1)
                authenticated = true;
            else
                authenticated = false;

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public void registerUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "portento123");

            System.out.println("Opened database successfully");

            String sql = "INSERT INTO registereduser(Name,Surname,Email,UserId,Password) VALUES (?,?,?,?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUserId());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                closeConnection(url);

            } catch (SQLException ex) {
                System.err.println( ex.getClass().getName()+": "+ ex.getMessage() );
                System.exit(0);
            }

        }
        System.out.println("Records created successfully");

    }

}