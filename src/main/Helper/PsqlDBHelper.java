package main.Helper;

import java.sql.*;
import java.util.*;

/**
 * Classe che gestisce l'interazione con il database
 * @author Federico Amici
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
            Class.forName("org.postgresql.Driver");
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

    public void createTableUser() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE registereduser (" +
                    "name CHARACTER VARYING," +
                    "surname CHARACTER VARYING," +
                    "email CHARACTER VARYING," +
                    "userid CHARACTER VARYING NOT NULL," +
                    "password CHARACTER VARYING," +
                    "PRIMARY KEY (userid))";
            stmt.execute(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
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
                    "gradidec DOUBLE PRECISION," +
                    "gradiar DOUBLE PRECISION," +
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
            st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
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

    public int countRowsTable(String table) {

        Statement stmt = null;
        int count = 0;

        try {
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) as numerorighe FROM " + table + ";" );
            while ( rs.next() ) {
                count = rs.getInt("numerorighe");
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return count;

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
