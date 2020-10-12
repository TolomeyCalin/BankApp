package bankApp.config;

import bankApp.entities.Account;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtil {



        private static SessionFactory sessionFactory = buildSessionFactory();

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        private static SessionFactory buildSessionFactory() {
            Configuration configuration = createConfig();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        }

        private static Configuration createConfig() {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "1m2u3i4e");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);

        //One to many
//        configuration.addAnnotatedClass(Bank.class);
        configuration.addAnnotatedClass(Account.class);

//        configuration.addAnnotatedClass(TransactionHistory.class);
//        configuration.addAnnotatedClass(Customer.class);


            return configuration;
        }
}