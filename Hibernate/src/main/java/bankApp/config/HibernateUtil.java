package bankApp.config;


import bankApp.entities.Account;
import bankApp.entities.Bank;
import bankApp.entities.Customer;
import bankApp.entities.TransactionHistory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

    import static java.util.logging.Level.OFF;
    public class HibernateUtil {
        private static SessionFactory sessionFactory;
        public static SessionFactory getSessionFactory() {
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(OFF);
            if(sessionFactory==null){
                try{
                    Configuration configuration = new Configuration();
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bankapp?serverTimezone=UTC");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "1m2u3i4e");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                    settings.put(Environment.SHOW_SQL, "true");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                    settings.put(Environment.HBM2DDL_AUTO, "update");


                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(Account.class);
                    configuration.addAnnotatedClass(Bank.class);
                    configuration.addAnnotatedClass(Customer.class);
                    configuration.addAnnotatedClass(TransactionHistory.class);
                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                sessionFactory= configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}