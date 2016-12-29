package Helper;

import Model.*;
import View.FluxCell;
import com.opencsv.CSVReader;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
            result = res.next();
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

    public void insertRecord(ContFluxHP contFluxHP) {

        PreparedStatement pstmt = null;
        try {
            String query ="INSERT INTO flussocontinuo(nomegalassia, tipologia, upperlimit, atomo, valore, " +
                    "aperture, ref160, errore) VALUES (?,?,?,?,?,?,?,?);";

            // create PrepareStatement object
            pstmt = conn.prepareStatement(query);
            /*NOMEGALASSIA*/
            pstmt.setString(1, contFluxHP.getNomeGalassia());
            /*TIPOLOGIA FLUSSO*/
            pstmt.setString(2, contFluxHP.getTipologia());
            /*UPPERLIMIT*/
            if(contFluxHP.getUpperLimit().equals("NO") || contFluxHP.getUpperLimit().equals(null))
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            else
                pstmt.setString(3, contFluxHP.getUpperLimit());
            /*ATOMO*/
            pstmt.setString(4, contFluxHP.getAtomo());
            /*VALORE*/
            if(contFluxHP.getValore() == null)
                pstmt.setNull(5, Types.DOUBLE);
            else
                pstmt.setDouble(5, contFluxHP.getValore());
            /*APERTURE*/
            pstmt.setString(6, contFluxHP.getAperture());
            /*REF160um*/
            if(contFluxHP.getRef160um() == null)
                pstmt.setNull(7, Types.VARCHAR);
            else
                pstmt.setString(7, contFluxHP.getRef160um());
            /*ERRORE*/
            if(contFluxHP.getError() == null)
                pstmt.setNull(8, Types.DOUBLE);
            else
                pstmt.setDouble(8, contFluxHP.getError());

            // execute query, and return number of rows created
            int rowCount = pstmt.executeUpdate();
            System.out.println("rowCount=" + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkTable(String table) {
        boolean exist = true;

        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // Controlla se esiste la tabella "table"
            ResultSet tables = dbm.getTables(null, null, table, null);
            exist = tables.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public void createTableFlusso() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE flusso (" +
                    "nomegalassia CHARACTER VARYING," +
                    "tipologia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "aperture CHARACTER VARYING," +
                    "errore DOUBLE PRECISION);";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableFlussoContinuo() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE flussocontinuo (" +
                    "nomegalassia CHARACTER VARYING," +
                    "tipologia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "aperture CHARACTER VARYING," +
                    "ref160 CHARACTER VARYING," +
                    "errore DOUBLE PRECISION);";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableFlussoSp() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE flussosp (" +
                    "nomegalassia CHARACTER VARYING," +
                    "tipologia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "errore DOUBLE PRECISION," +
                    "irsmode CHARACTER VARYING);";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public Flux retrieveValFluxDB (String galaxy, String atom, String table) {

        Flux result = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully")
            stmt = conn.createStatement();
            int i = 0;
            ResultSet rs = stmt.executeQuery("SELECT * FROM (SELECT * FROM " + table + " WHERE nomegalassia LIKE " +
                        "'" + galaxy + "%" + "') AS TEMP WHERE TEMP.atomo='" + atom + "';");
            try {
                while(rs.next()) {
                    Double value = rs.getDouble("valore");
                    String _value = String.valueOf(value);
                    String upperLimit = rs.getString("upperlimit");
                    result = new Flux(galaxy, table, atom, _value, upperLimit, null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return result;
    }

    public ObservableList retrieveValErrFluxDB(String galaxy, String[] atoms, String table) {
        ObservableList<FluxCellData> obs = FXCollections.observableArrayList();

        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully")
            stmt = conn.createStatement();
            int i = 0;
            ResultSet rs = null;
            while(i<atoms.length) {
                rs = stmt.executeQuery("SELECT * FROM (SELECT * FROM " + table + " WHERE nomegalassia LIKE " +
                        "'" + galaxy + "%" + "') AS TEMP WHERE TEMP.atomo='" + atoms[i] + "';");
                parseRow(rs, obs);

                if(obs.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Nessuna riga trovata in " + table);
                    alert.showAndWait();
                }

                i++;
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

    private void parseRow(ResultSet rs, ObservableList<FluxCellData> obs) {

        try {
            while (rs.next()) {
                String nomeGalassia = rs.getString("nomegalassia");
                String atomo = rs.getString("atomo");
                String upperLimit = rs.getString("upperlimit");
                Double valore = rs.getDouble("valore");
                Double errore = rs.getDouble("errore");
                FluxCellData fluxCellData = new FluxCellData(nomeGalassia, atomo, errore, upperLimit, valore);
                obs.add(fluxCellData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        //psqlDBHelper.searchGalaxyForName("M95");
        //psqlDBHelper.deleteGalassia();
        //psqlDBHelper = new PsqlDBHelper();
        //psqlDBHelper.createTableGalassia();
        //psqlDBHelper.checkGalaxyTable();
        //psqlDBHelper.importCSVGalaxies("C:\\Users\\feder\\Desktop\\ProgettoBasi\\progetto15161\\MRTable3_sample.csv");
        //psqlDBHelper.retrieveValErrFluxDB("IZw1", new String[]{"OI63", "CII158"}, "flusso");
        if(psqlDBHelper.retrieveValFluxDB("Mrk938", "SIII", "flussocontinuo") == null)
            System.out.println("aoidfodinvodnvosdvnofsvosfn");

    }
}
