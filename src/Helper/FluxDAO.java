package Helper;

import Model.Flux;
import Model.FluxCellData;
import Model.Galaxy;
import View.FluxCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Properties;

/**
 * @author Federico Amici
 * Classe che gestisce il CRUD di Flusso
 */
public class FluxDAO {

    private Connection conn;

    public FluxDAO () {
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
}
