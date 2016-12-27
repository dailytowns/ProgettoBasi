package Helper;

import Model.*;
import com.opencsv.CSVReader;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.enterprise.event.Event;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

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
                closeConnection();

            } catch (SQLException ex) {
                System.err.println( ex.getClass().getName()+": "+ ex.getMessage() );
                System.exit(0);
            }

        }
        System.out.println("Records created successfully");
    }

     private void insertRecordsHibernate(UserHib user) {
        // A SessionFactory is set up once for an application!
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }

    }

    public ObservableList<Galaxy> retrieveGalaxiesDB() {

        ObservableList<Galaxy> obs = FXCollections.observableArrayList();
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            /*conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");*/
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia;" );
            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String nomeAlternativo = rs.getString("nomealt");
                double redshift  = rs.getDouble("redshift");
                String classeSpettrale = rs.getString("classespettrale");
                System.out.println( "NOME = " + nome );
                System.out.println( "NOMEALTERNATIVO = " + nomeAlternativo );
                System.out.println( "REDSHIFT = " + redshift );
                System.out.println( "CLASSESPETTRALE = " + classeSpettrale);
                Galaxy galaxy = new Galaxy(nome, nomeAlternativo, redshift);
                obs.add(galaxy);
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return obs;
    }

    public ObservableList<Galaxy> retrieveGalaxiesDB(Double redshiftValue, String lgt) {
        ObservableList<Galaxy> obs = FXCollections.observableArrayList();
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            /*conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");*/
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia WHERE redshift " + lgt + " " + redshiftValue + ";" );
            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String nomeAlternativo = rs.getString("nomealt");
                double redshift  = rs.getDouble("redshift");
                String classeSpettrale = rs.getString("classespettrale");
                Galaxy galaxy = new Galaxy(nome, nomeAlternativo, redshift);
                obs.add(galaxy);
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return obs;
    }

    public ObservableList<Galaxy> retrieveGalaxiesDB(Declination declination, RightAscension rightAscension,
                                                     int number, double radius) {
        ObservableList<Galaxy> obs = FXCollections.observableArrayList();
        Statement stmt = null;
        AngularCoordinate acPoint = new AngularCoordinate(declination, rightAscension);
        double relativeDistance;
        int i = 0; //Il contatore serve per limitarci alle prime number galassie trovate

        try {
            Class.forName("org.postgresql.Driver");
            /*conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");*/
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * " +
                    " FROM galassia INNER JOIN coordinateangolari ON galassia.nome = coordinateangolari.nomegalassia" );
            while ( rs.next() ) {
                if(i < number) {
                    String nomeGalassia = rs.getString("nome");
                    String nomealt = rs.getString("nomealt");
                    Double redshift = rs.getDouble("redshift");
                    /*String classeSpettrale = rs.getString("classespettrale");*/
                    String sign = rs.getString("decsign");
                    Integer decDegrees = rs.getInt("decdeg");
                    Integer decMinutes = rs.getInt("decmin");
                    Double decSeconds = rs.getDouble("decsec");
                    Integer arHours = rs.getInt("arh");
                    Integer arMinutes = rs.getInt("arm");
                    Double arSeconds = rs.getDouble("ars");

                    AngularCoordinate acGalaxy = new AngularCoordinate(new Declination(sign, decDegrees, decMinutes, decSeconds),
                            new RightAscension(arHours, arMinutes, arSeconds));
                    relativeDistance = AngularCoordinate.computeDistanceBetweenCoordinates(acGalaxy, acPoint);
                    if(relativeDistance <= radius){
                        Galaxy galaxy = new Galaxy(nomeGalassia, nomealt, redshift);
                        obs.add(galaxy);
                    }
                    i++;
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return obs;
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO \"registereduser\" (\"name\", \"surname\", \"email\", \"userid\", \"password\") VALUES ('" + user.getName() + "', '" + user.getSurname() + "', '" +
                            user.getEmail() + "', '" + user.getUserId() + "', '" + user.getPassword() +"');";
        //insertRecords(sql);
        UserHib userHib = new UserHib(user.getUserId(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail());
        insertRecordsHibernate(userHib);
    }

    public Galaxy searchGalaxyForName(String nameGalaxy) {
        Statement stmt = null;
        Galaxy galaxy = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia WHERE nome LIKE '"+nameGalaxy+"%';" );
            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String nomeAlternativo = rs.getString("nomealt");
                double redshift  = rs.getDouble("redshift");
                galaxy = new Galaxy(nome, nomeAlternativo, redshift);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println(galaxy.getName());
        return galaxy;
    }

    public void createTableGalassia() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE galassia (" +
                    "nome CHARACTER VARYING PRIMARY KEY," +
                    "nomealt CHARACTER VARYING," +
                    "redshift DOUBLE PRECISION," +
                    "classespettrale CHARACTER VARYING);";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableCoordinateAngolari() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE coordinateangolari (" +
                    "nomegalassia CHARACTER VARYING REFERENCES galassia," +
                    "ARh INTEGER," +
                    "ARm INTEGER," +
                    "ARs DOUBLE PRECISION," +
                    "decsign CHARACTER VARYING," +
                    "decdeg INTEGER," +
                    "decmin INTEGER," +
                    "decsec DOUBLE PRECISION," +
                    "PRIMARY KEY (nomegalassia));";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableCaratteristiche() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE caratteristichefisiche (" +
                    "nomegalassia CHARACTER VARYING REFERENCES galassia, " +
                    "tipologia CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "errore DOUBLE PRECISION," +
                    "riferimento INTEGER," +
                    "PRIMARY KEY (nomegalassia, tipologia));";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableDistanza() {
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                conn.setAutoCommit(true);
                System.out.println("Opened database successfully");

                stmt = conn.createStatement();
                String sql = "CREATE TABLE distanza (" +
                        "nomegalassia CHARACTER VARYING REFERENCES galassia, " +
                        "valore DOUBLE PRECISION," +
                        "riferimento INTEGER," +
                        "PRIMARY KEY (nomegalassia));";
                stmt.execute(sql);
                stmt.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
            }
        }

    public void deleteTable(String table) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "DROP TABLE " + table;
            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            //System.exit(0);
        }
    }

    public boolean checkGalaxyTable() {
        boolean result = true;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, "galassia",
                   null);
            if (res.next()) {
                result = true;
            } else
                result = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insertRecord(String sql) {
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

    public boolean checkTable(String table) {
        boolean exist = true;

        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, table, null);
            if (tables.next()) {
                exist = true;
            } else {
                exist = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public void createTableFlusso() {

    }

    public void createTableAtomo() {

    }

    public static void main(String[] args) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        //psqlDBHelper.searchGalaxyForName("M95");
        //psqlDBHelper.deleteGalassia();
        //psqlDBHelper = new PsqlDBHelper();
        //psqlDBHelper.createTableGalassia();
        //psqlDBHelper.checkGalaxyTable();
        //psqlDBHelper.importCSVGalaxies("C:\\Users\\feder\\Desktop\\ProgettoBasi\\progetto15161\\MRTable3_sample.csv");
    }
}