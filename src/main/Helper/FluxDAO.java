package main.Helper;

import main.Model.Flux;
import main.Model.FluxCellData;
import main.View.ErrorMessageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Implementazione del pattern di accesso
 * ai dati di Flusso
 * @author Federico Amici
 */
public class FluxDAO {

    private Connection conn;

    public FluxDAO () {
        openConnection();
    }

    /**
     * Viene aperta una connessione con il database
     */
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

    /**
     * Viene chiusa la connessione con il database
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vengono recuperati tutti i valori del flusso in base ai parametri immessi
     * @param fluxType Il tipo di flusso su cui effettuare calcoli
     * @param lineSelected Riga su cui effettuare calcoli
     * @param aperture Aperture del flusso
     * @return ArrayList di Double contenente i valori recuperati
     */
    public ArrayList<Double> retrieveValLineDB(String fluxType, String lineSelected, String aperture) {

        ArrayList<Double> list = new ArrayList<>();
        Statement stmt = null;
        int i = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully")
            stmt = conn.createStatement();
            ResultSet rs = null;

            rs = stmt.executeQuery("SELECT valore FROM " + fluxType + " WHERE (valore IS NOT null) AND " +
                    "atomo LIKE '"+ lineSelected + "' AND aperture LIKE '%"+ aperture +"%';");

            while(rs.next()){
                list.add(rs.getDouble("valore"));
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return list;

    }

    /**
     * Vengono recuperati tutti i valori del flusso in base ai parametri immessi
     * @param fluxType Il tipo di flusso su cui effettuare calcoli
     * @param lineSelected Riga su cui effettuare calcoli
     * @return ArrayList di Double contenente i valori recuperati
     */
    public ArrayList<Double> retrieveValLineDB(String fluxType, String lineSelected) {

        ArrayList<Double> list = new ArrayList<>();
        Statement stmt = null;
        int i = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully")
            stmt = conn.createStatement();
            ResultSet rs = null;

            rs = stmt.executeQuery("SELECT valore FROM " + fluxType + " WHERE (valore IS NOT null) AND " +
                    "atomo LIKE '"+ lineSelected + "';");

            while(rs.next()){
                list.add(rs.getDouble("valore"));
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return list;

    }

    /**
     * Vengono recuperati tutti i valori del flusso in base ai parametri immessi
     * @param galaxy Nome della galassia di cui recuperare i dati
     * @param atom Riga del flusso da recuperare
     * @param table Tabella del database da cui recuperare i dati
     * @return Un oggetto flusso contenente le info richieste
     */
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

    /**
     * Vengono recuperati tutti i valori del flusso in base ai parametri immessi
     * @param galaxy Nome della galassia di cui recuperare i dati
     * @param atoms Righe del flusso da recuperare
     * @param table Tabella del database da cui recuperare i dati
     * @return Una lista di FluxCellData contenenti i dati recuperati dal database
     */
    public ObservableList<FluxCellData> retrieveValErrFluxDB(String galaxy, String[] atoms, String table) {
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
                    new ErrorMessageView(table, atoms[i]);
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

    /**
     * Controlla nel database quali righe del flusso sono presenti per una data galassia
     * @param galaxyName Nome della galassia
     * @param table Tabella su cui effettuare il controllo
     * @return Lista di flussi con valori non nulli
     */
    public ArrayList<Flux> retrieveAllFluxForGalaxyName(String galaxyName, String table) {

        ArrayList<Flux> list = new ArrayList<>();
        Statement stmt = null;
        Flux flux = null;
        String nome = "";

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully")
            stmt = conn.createStatement();
            int i = 0;
            ResultSet rs = stmt.executeQuery("SELECT nomegalassia,atomo,valore FROM " + table + " WHERE nomegalassia LIKE '" + galaxyName + "%' AND" +
                                                                                            "(valore IS NOT NULL);");
            while(rs.next()) {
                String nomeGalassia = rs.getString("nomegalassia");
                if(!(nomeGalassia.contains(nome)))
                    return null;
                String atomo = rs.getString("atomo");
                Double valore = rs.getDouble("valore");
                flux = new Flux(atomo, valore);
                list.add(flux);
                nome = nomeGalassia;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        System.out.println("Operation done successfully");

        return list;
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
        FluxDAO fluxDAO = new FluxDAO();
        ArrayList<Double> list = fluxDAO.retrieveValLineDB("flussorighehp", "OI63");
        int i = 0;
        while(i<list.size()) {
            System.out.println(list.get(i));
            i++;
        }
    }
}
