package bankApp.repository;

import bankApp.config.HibernateUtil;
import bankApp.entities.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CustomerHibernateRepository implements CustomerRepository {

    public CustomerHibernateRepository() {

    }
        @Override
        public void create (Customer customer){
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                session.save(customer);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        @Override
        public Customer findById ( int customerId){
            Customer result = null;
            try (Session session = getSession()) {
                result = session.find(Customer.class, customerId);
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            return result;
        }

        @Override
        public Customer update ( int customerId, Customer customerDetails){
            Customer result = null;
            Transaction transaction = null;
            try (Session session = getSession()) {
                Customer customerToBeUpdated = session.find(Customer.class, customerId);

                transaction = session.beginTransaction();

                customerToBeUpdated.setFirstName(customerDetails.getFirstName());
                customerToBeUpdated.setLastName(customerDetails.getLastName());
                customerToBeUpdated.setCnp(customerDetails.getCnp());
                customerToBeUpdated.setEmail(customerDetails.getEmail());
                customerToBeUpdated.setPassword(customerDetails.getPassword());
                customerToBeUpdated.setUserName(customerDetails.getUserName());


                session.update(customerToBeUpdated);

                transaction.commit();
                result = session.find(Customer.class, customerId);
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println(e.getMessage());
            }
            return result;
        }

        @Override
        public void delete ( int customerId){
            Transaction transaction = null;
            try (Session session = getSession()) {
                Customer customerToBeDeleted = session.find(Customer.class, customerId);

                transaction = session.beginTransaction();

                session.delete(customerToBeDeleted);

                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println(e.getMessage());
            }
        }
        public long findIdByUsername (String username){
            long result = 0;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                String query = "select customer_id from customer where username =\"" + username + "\"";
                NativeQuery<Customer> nativeQuery = session.createNativeQuery(query);
                result = nativeQuery.getFirstResult();
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            return result;
        }

        public long findIdByPassword (String password){
            long result = 0;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                String query = "select customer_id from customer where password =\"" + password + "\"";
                NativeQuery<Customer> nativeQuery = session.createNativeQuery(query);
                result = nativeQuery.getFirstResult();
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            return result;
        }

        private Session getSession () {
            return HibernateUtil.getSessionFactory().openSession();
        }

    public Customer findByUsername(String username) {
        Customer result = null;
        String query = "select * from customer where username = '" + username + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Customer> nquery = session.createNativeQuery(query, Customer.class);
            List<Customer> foundCustomers = nquery.getResultList();
            if (foundCustomers.isEmpty()) {
                return result;
            } else {
                result = foundCustomers.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
