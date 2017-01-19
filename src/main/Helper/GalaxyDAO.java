package main.Helper;

import main.Model.*;
import main.View.ErrorGenericView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

/**
 * La classe che gestisce il recupero di istanze
 * della classe Galassia
 * @author Federico Amici
 */
public class GalaxyDAO {

    private Connection conn;

    public GalaxyDAO() {
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

    /**
     * Recupera tutte le galassie presenti nella base di dati
     * @return La lista di galassie
     */
    public ObservableList<GalaxyData> retrieveGalaxiesDB() {

        ObservableList<GalaxyData> obs = FXCollections.observableArrayList();
        Statement stmt = null;
        int i = 1;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia;" );
            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String nomeAlternativo = rs.getString("nomealt");
                double redshift  = rs.getDouble("redshift");
                Galaxy galaxy = new Galaxy(nome, nomeAlternativo, redshift);
                GalaxyData galaxyData = new GalaxyData(galaxy, i++);
                obs.add(galaxyData);
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
     * Ricerca nel database tutte le occorrenze di galassie rispondenti
     * ai criteri immessi
     * @param redshiftValue Valore soglia del redhift
     * @param lgt Stringa che indica il verso della query
     * @return Viene ritornata una lista di Galassie corrispondente ai criteri di ricerca
     */
    public ObservableList<GalaxyData> retrieveGalaxiesDB(Double redshiftValue, String lgt) {
        ObservableList<GalaxyData> obs = FXCollections.observableArrayList();
        Statement stmt = null;
        int i = 1;

        try {
            Class.forName("org.postgresql.Driver");
            /*conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");*/
            conn.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia WHERE redshift " + lgt + " " + redshiftValue + "ORDER BY redshift;" );
            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String nomeAlternativo = rs.getString("nomealt");
                double redshift  = rs.getDouble("redshift");
                String classeSpettrale = rs.getString("classespettrale");
                Galaxy galaxy = new Galaxy(nome, nomeAlternativo, redshift);
                GalaxyData galaxyData = new GalaxyData(galaxy, i++);
                obs.add(galaxyData);
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
     * Ricerca nel database una galassia per nome
     * @param nameGalaxy Nome della galassia da ricercare
     * @return Un oggetto di tipo Galassia, altrimenti null
     */
    /*public Galaxy searchGalaxyForName(String nameGalaxy) {

        Statement stmt = null;
        Galaxy galaxy = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            String nome = null;
            Double redshift = null;
            CoordinateAngolari coordinateAngolari = null;
            Double ars, decs;
            Integer arh, arm, decdeg, decm;
            String sign;
            CaratteristicheFisiche caratteristicheFisiche = new CaratteristicheFisiche();
            String nomeAlt = null;

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM galassia WHERE nome LIKE '"+nameGalaxy+"%';" );

            if(rs.next()) { //Corretto il bug relativo al recupero di più galassie
                //while (rs.next()) {
                    nome = rs.getString("nome");
                    nomeAlt = rs.getString("nomealt");
                    redshift = rs.getDouble("redshift");
                //}
                if (nome == null)
                    return null;

                rs = stmt.executeQuery("SELECT * FROM coordinateangolari WHERE nomegalassia LIKE '" + nameGalaxy + "%';");
                //while (rs.next()) {
                if(rs.next()) {
                    arh = rs.getInt("ARh");
                    arm = rs.getInt("ARm");
                    ars = rs.getDouble("ARs");
                    decdeg = rs.getInt("decdeg");
                    decm = rs.getInt("decmin");
                    decs = rs.getDouble("decsec");
                    sign = rs.getString("decsign");
                    Declination dec = new Declination(sign, decdeg, decm, decs);
                    RightAscension ra = new RightAscension(arh, arm, ars);
                    coordinateAngolari = new CoordinateAngolari(dec, ra);
                }
                rs = stmt.executeQuery("SELECT * FROM caratteristichefisiche WHERE nomegalassia LIKE '" + nameGalaxy + "%';");
                //while (rs.next()) {
                if(rs.next()) {
                    String valLum = rs.getString("valorelum");
                    String refLum = rs.getString("riferimentolum");
                    String valMet = rs.getString("valoremet");
                    String refMet = rs.getString("riferimentomet");
                    String errMet = rs.getString("erroremet");
                    if (valLum != null || valMet != null || errMet != null || refLum != null || refMet != null) {
                        Luminosita luminosita = new Luminosita(valLum, refLum);
                        Metallicita metallicita = new Metallicita(valMet, errMet, refMet);
                        caratteristicheFisiche = new CaratteristicheFisiche(nome, metallicita, luminosita);
                    } else {
*//*                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Nessuna corrispondenza in caratteristichefisiche");
                    alert.showAndWait();
                    //return null;*//*
                        new ErrorGenericView("Errore nel recupero delle caratteristiche fisiche\n per la galassia" + nome);
                    }

                }
            }

            if(rs.next()) {
                new ErrorGenericView("I criteri immessi recuperano\n più di una galassia");
            }

            galaxy = new Galaxy(nome,nomeAlt,redshift,caratteristicheFisiche,coordinateAngolari);

            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return galaxy;
    }*/

    /**
     * Ricerca nel database una galassia per nome
     * @param nameGalaxy Nome della galassia da ricercare
     * @throws SQLException
     * @return Un oggetto di tipo Galassia, altrimenti null
     */
    public ObservableList<Galaxy> searchGalaxyForName(String nameGalaxy) throws SQLException {

        Statement stmt = null, stmt2 = null, stmt3 = null;
        ObservableList<Galaxy> obs = FXCollections.observableArrayList();
        Galaxy galaxy;
        ResultSet rs1 = null, rs2 = null, rs3 = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            String nome = null;
            Double redshift = null;
            CoordinateAngolari coordinateAngolari = null;
            Double ars, decs;
            Integer arh, arm, decdeg, decm;
            String sign;
            Luminosita luminosita;
            Metallicita metallicita;
            CaratteristicheFisiche caratteristicheFisiche = new CaratteristicheFisiche();
            String nomeAlt = null;

            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            stmt3 = conn.createStatement();

            rs1 = stmt.executeQuery( "SELECT * FROM galassia WHERE nome LIKE '"+nameGalaxy+"%';" );
            rs2 = stmt2.executeQuery("SELECT * FROM coordinateangolari WHERE nomegalassia LIKE '" + nameGalaxy + "%';");
            rs3 = stmt3.executeQuery("SELECT * FROM caratteristichefisiche WHERE nomegalassia LIKE '" + nameGalaxy + "%';");

            //if(rs.next()) { //Corretto il bug relativo al recupero di più galassie
            while (rs1.next() && rs2.next() && rs3.next()) {
                galaxy = new Galaxy();

                nome = rs1.getString("nome");
                nomeAlt = rs1.getString("nomealt");
                redshift = rs1.getDouble("redshift");
                if (nome == null)
                    return null;
                galaxy.setName(nome);
                galaxy.setRedshift(redshift);
                galaxy.setAltName(nomeAlt);
                //}

                //while (rs.next()) {
                //if(rs.next()) {
                    arh = rs2.getInt("ARh");
                    arm = rs2.getInt("ARm");
                    ars = rs2.getDouble("ARs");
                    decdeg = rs2.getInt("decdeg");
                    decm = rs2.getInt("decmin");
                    decs = rs2.getDouble("decsec");
                    sign = rs2.getString("decsign");
                    Declination dec = new Declination(sign, decdeg, decm, decs);
                    RightAscension ra = new RightAscension(arh, arm, ars);
                    coordinateAngolari = new CoordinateAngolari(dec, ra);
                    galaxy.setCoordinateAngolari(coordinateAngolari);


                //while (rs.next()) {
                //if(rs.next()) {

                    String valLum = rs3.getString("valorelum");
                    String refLum = rs3.getString("riferimentolum");
                    String valMet = rs3.getString("valoremet");
                    String refMet = rs3.getString("riferimentomet");
                    String errMet = rs3.getString("erroremet");
                    if (valLum != null || valMet != null || errMet != null || refLum != null || refMet != null) {
                        if (valLum == null && refLum == null) {
                            valLum = refLum = " ";
                        }
                        if (valMet == null && errMet == null && refMet == null) {
                            valMet = errMet = refMet = " ";
                        }
                        luminosita = new Luminosita(valLum, refLum);
                        metallicita = new Metallicita(valMet, errMet, refMet);
                        caratteristicheFisiche = new CaratteristicheFisiche(nome, metallicita, luminosita);
                        galaxy.setCaratteristicheFisiche(caratteristicheFisiche);
                    } else {
/*                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Nessuna corrispondenza in caratteristichefisiche");
                    alert.showAndWait();
                    //return null;*/
                        new ErrorGenericView("Non vi sono dati sulle\n caratteristiche fisiche\n per la galassia" + nome);
                    }

                obs.add(galaxy);
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } finally {
            rs1.close();
            rs2.close();
            rs3.close();
            stmt.close();
            stmt2.close();
            stmt3.close();
            conn.close();
        }

        return obs;
    }

    /**
     * Recupera dal database tutte quelle galassie in base alle
     * coordinate immesse dall'utente
     * @param declination Declinazione del punto
     * @param rightAscension Ascensione retta del punto
     * @param number Numero massimo di galassie da recuperare
     * @param radius Valore soglia del raggio
     * @return La lista di galassie recuperate
     */
    public ObservableList<GalaxyDataRadius> retrieveGalaxiesDB(Declination declination, RightAscension rightAscension,
                                                     int number, double radius) {
        ObservableList<GalaxyDataRadius> obs = FXCollections.observableArrayList();
        Statement stmt = null;
        CoordinateAngolari acPoint = new CoordinateAngolari(declination, rightAscension);
        double relativeDistance;
        int i = 0; //Il contatore serve per limitarci alle prime number galassie trovate
        ArrayList<Galaxy> list = null;

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
            list = new ArrayList<>();
            while ( rs.next() ) {
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

                    CoordinateAngolari acGalaxy = new CoordinateAngolari(new Declination(sign, decDegrees, decMinutes, decSeconds),
                            new RightAscension(arHours, arMinutes, arSeconds));
                    relativeDistance = CoordinateAngolari.computeDistanceBetweenCoordinates(acGalaxy, acPoint);
                    if(relativeDistance <= radius){
                        Galaxy galaxy = new Galaxy(nomeGalassia, nomealt, redshift, relativeDistance);
                        list.add(galaxy);
                    }
            }

            Collections.sort(list);
            System.out.println(list);

            while(i < number && i<list.size()) {
                GalaxyDataRadius galaxyData = new GalaxyDataRadius(list.get(i), i+1, list.get(i).getRelativeDistance());
                obs.add(galaxyData);
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
}
