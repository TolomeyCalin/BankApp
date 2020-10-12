package BankApp.Repo;

import BankApp.Entities.Bank;
import BankApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankHibernateRepository implements BankRepository {


    @Override
    public void create(Bank account) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            

            session.save(account);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    
    @Override
    public Bank findById(Long id) {
        Bank result = null;
        try (Session session = getSession()) {
            result = session.find(Bank.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    @Override
    public Bank update(Long id, Bank accountDetails) {
        Bank result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Bank bank = session.find(Bank.class, id);

            transaction = session.beginTransaction();

            bank.setBankName(accountDetails.getBankName());


            session.update(bank);

            transaction.commit();
            result = session.find(Bank.class, id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Bank bank = session.find(Bank.class, id);

            transaction = session.beginTransaction();

            session.delete(bank);

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
