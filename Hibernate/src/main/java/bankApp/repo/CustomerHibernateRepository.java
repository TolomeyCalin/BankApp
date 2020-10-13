package bankApp.repo;

import bankApp.entities.Customer;
import bankApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerHibernateRepository implements CustomerRepository {

    public CustomerHibernateRepository() {

    }

    @Override
    public void create(Customer customer) {
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
    public Customer findById(int customerId) {
        Customer result = null;
        try (Session session = getSession()) {
            result = session.find(Customer.class, customerId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Customer update(int customerId, Customer customerDetails) {
        Customer result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Customer customerToBeUpdated = session.find(Customer.class, customerId);

            transaction = session.beginTransaction();

            customerToBeUpdated.setFirstName(customerDetails.getFirstName());
            customerToBeUpdated.setLastName(customerDetails.getLastName());
            customerToBeUpdated.setAddress(customerDetails.getAddress());
            customerToBeUpdated.setCnp(customerDetails.getCnp());
            customerToBeUpdated.setDateOfBirth(customerDetails.getDateOfBirth());
            customerToBeUpdated.setEmail(customerDetails.getEmail());
            customerToBeUpdated.setPhone(customerDetails.getPhone());
            customerToBeUpdated.setPassword(customerDetails.getPassword());
            customerToBeUpdated.setUsername(customerDetails.getUsername());

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
    public void delete(int customerId) {
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

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

}
