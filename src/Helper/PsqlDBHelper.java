package Helper;

import Model.User;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
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

    public PsqlDBHelper () {
        //this.url = url;
        //this.user = user;
        //this.password = password;
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

    public boolean checkUser(String user, String password) {
        boolean authenticated = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registereduser WHERE userid ='" + user + "' AND " +
                                                                                    "password = '" + password + "'");
            int count = 0;

            while (rs.next())
                count++;

            if(count == 1)
                authenticated = true;
            else
                authenticated = false;

            rs.close();
            st.close();
            conn.close();

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

    /**
     * Importa il file delle galassie.
     * @param path È il percorso del file da importare
     */
    public void importCSVGalaxies(String path) {
        CSVReader reader = null;
        String[] nextLine;
        String[] headerLine;

        int i = 0;
        try {
            reader = new CSVReader(new FileReader(path), '\t', '\'', 64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*Il ciclo while permette di saltare l'eventuale header
        try {
            while ((headerLine = reader.readNext()) != null) {
                i = 0;
                // nextLine[] is an array of values from the line
                System.out.println("headerLine " + headerLine[0]);
                if (headerLine.length > 1) {
                    System.out.println(headerLine.length);
                    System.out.println("Prima di sql");
                    insertRecords("INSERT INTO Galassia VALUES (" + headerLine[0] + ", " + headerLine[25] + ", " + headerLine[8] + ");");
                    System.out.println("Dopo sql");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            while ((nextLine = reader.readNext()) != null) {
                i = 0;
                System.out.println("length " + nextLine.length);
                // nextLine[] is an array of values from the line
                /*if (nextLine.length > 1) {
                    while (i < nextLine.length) {
                        System.out.print(nextLine[i] + " ");
                        i++;
                    }
                }*/
                insertRecords("INSERT INTO \"Galassia\" (\"Nome\", \"NomeAlternativo\", \"Redshift\") VALUES ('" + nextLine[0] + "', '" + nextLine[25] + "', " + Double.valueOf(nextLine[8]) + ");");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertRecords(String sql) {
        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "portento123");
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }
}