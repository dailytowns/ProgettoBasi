package Helper;

import Model.User;
import Model.UserHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Federico Amici
 * La classe si occupa di gestire il CRUD User
 */
public class UserDAO {

    private Connection conn;

    public UserDAO() {
        openConnection();
    }

    private void openConnection() {
        /*Nel caso dell'applicazione web sembra essere indispensabile*/
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /***********************************************************/

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public boolean checkAdmin(String user, String password) {
        boolean authenticated = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM amministratore WHERE userid ='" + user + "' AND " +
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

    public void insertRecordsHibernate(UserHib user) {
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

    public ArrayList<UserHib> retrieveUserHibernate() {
        // A SessionFactory is set up once for an application!
        SessionFactory sessionFactory;
        ArrayList<UserHib> result = new ArrayList<UserHib>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaQuery<UserHib> cq = session.getCriteriaBuilder().createQuery(UserHib.class);
            cq.from(UserHib.class);
            List<UserHib> userHibList = session.createQuery(cq).getResultList();

            for (UserHib userHib : userHibList) {
                System.out.println("Student details - "+userHib.getName()+" -- "+userHib.getEmail());
                result.add(userHib);
            }

            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return result;
    }

    public void insertUser(User user, String table) {
        String sql = "INSERT INTO " + table + " (\"name\", \"surname\", \"email\", \"userid\", \"password\") VALUES ('"
                + user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getUserId() +
                "', '" + user.getPassword() +"');";

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

    public void deleteUser(UserHib userHib){
        // A SessionFactory is set up once for an application!
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(userHib);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void updateRecordHibernate(UserHib userHib) {
        // A SessionFactory is set up once for an application!
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(userHib);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        //UserBean userBean = new UserBean();
        /*userBean.setUsername("usermario");
        userBean.setPassword("passmario");
        if(userDAO.checkUser(userBean.getUsername(), userBean.getPassword()))
            System.out.println("DIfh");*/
        //userDAO.retrieveUserHibernate();
        UserHib userHib = new UserHib("osinoicn228", "dosnvosidnvosdnv33", "sldnvosdinv", "sldjvnsdvjonvsd", "lsdjvn");
        userDAO.insertRecordsHibernate(userHib);
    }
}
