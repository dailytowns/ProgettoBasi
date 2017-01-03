package Helper;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.*;

/**
 * Created by feder on 29/09/2016.
 */
public class PsqlDBHelper {

    private Connection conn;

    public PsqlDBHelper () {
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
                    "valoremet DOUBLE PRECISION," +
                    "erroremet DOUBLE PRECISION," +
                    "riferimentomet INTEGER," +
                    "valorelum DOUBLE PRECISION," +
                    "riferimentolum INTEGER," +
                    "PRIMARY KEY (nomegalassia));";
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

    public void createTableFlussoRigheHP() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE flussorighehp (" +
                    "nomegalassia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "aperture CHARACTER VARYING," +
                    "errore DOUBLE PRECISION," +
                    "PRIMARY KEY (nomegalassia, atomo));";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void createTableFlussoContinuoHP() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE flussocontinuo (" +
                    "nomegalassia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "aperture CHARACTER VARYING," +
                    "ref160 CHARACTER VARYING," +
                    "errore DOUBLE PRECISION," +
                    "PRIMARY KEY (nomegalassia, atomo));";
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
            String sql = "CREATE TABLE flussorighesp (" +
                    "nomegalassia CHARACTER VARYING," +
                    "upperlimit CHARACTER VARYING," +
                    "atomo CHARACTER VARYING," +
                    "valore DOUBLE PRECISION," +
                    "errore DOUBLE PRECISION," +
                    "irsmode CHARACTER VARYING," +
                    "PRIMARY KEY (nomegalassia, atomo));";
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
                    result = new Flux(galaxy, atom, _value, upperLimit, null);
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
/*        if(psqlDBHelper.retrieveValFluxDB("Mrk938", "SIII", "flussocontinuo") == null)
            System.out.println("aoidfodinvodnvosdvnofsvosfn");*/
       // UserHib userHib = new UserHib("doiidov", "dovndv", "dovindv", "dojvboduvd", "odvndov");
       // psqlDBHelper.insertRecordsHibernate(userHib);
       // psqlDBHelper.retrieveRecordHibernate();
    }
}
