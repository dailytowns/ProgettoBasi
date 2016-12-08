package Helper;

import Model.Galaxy;
import Model.User;
import Model.UserHib;
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
        //String[] headerLine;

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
                insertRecords("INSERT INTO galassia(nome, nomealternativo, redshift) VALUES ('" +
                        nextLine[0] + "', '" + nextLine[25] + "', " + Double.valueOf(nextLine[8]) + ");");
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
                String nome = rs.getString("Nome");
                String nomeAlternativo = rs.getString("NomeAlternativo");
                double redshift  = rs.getDouble("Redshift");
                System.out.println( "NOME = " + nome );
                System.out.println( "NOMEALTERNATIVO = " + nomeAlternativo );
                System.out.println( "REDSHIFT = " + redshift );

                Galaxy galaxy = new Galaxy(nome, nomeAlternativo, redshift);
                obs.add(galaxy);
            }
            rs.close();
            stmt.close();
            conn.close();
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
                String nomeAlternativo = rs.getString("nomealternativo");
                double redshift  = rs.getDouble("redshift");
                //System.out.println( "NOME = " + nome );
                //System.out.println( "NOMEALTERNATIVO = " + nomeAlternativo );
                //System.out.println( "REDSHIFT = " + redshift );

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

    public void resetGalassia() {

        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            String sql = "DROP TABLE galassia;";
            stmt.executeUpdate(sql);
            conn.commit();

            stmt = conn.createStatement();
            sql = "CREATE TABLE public.galassia\n" +
                    "(\n" +
                    "  nome CHARACTER VARYING NOT NULL,\n" +
                    "  nomealternativo CHARACTER VARYING,\n" +
                    "  redshift DOUBLE PRECISION,\n" +
                    "  CONSTRAINT galassia_pkey PRIMARY KEY (nome)\n" +
                    ")\n" +
                    "WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "ALTER TABLE public.galassia\n" +
                    "  OWNER TO postgres;\n";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        psqlDBHelper.searchGalaxyForName("M95");
        //psqlDBHelper.resetGalassia();
        //psqlDBHelper.importCSVGalaxies("C:\\Users\\feder\\Desktop\\ProgettoBasi\\progetto15161\\MRTable3_sample.csv");
    }
}