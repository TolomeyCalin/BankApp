package bankApp.repo;

import bankApp.entities.Bank;
import bankApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankHibernateRepository implements BankRepository {


    @Override
    public void create(Bank bank) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            

            session.save(bank);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    
    @Override
    public Bank findById(int bankId) {
        Bank result = null;
        try (Session session = getSession()) {
            result = session.find(Bank.class, bankId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    @Override
    public Bank update(int bankId, Bank bankName) {
        Bank result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Bank bank = session.find(Bank.class, bankId);

            transaction = session.beginTransaction();

            bank.setBankName(bankName.getBankName());


            session.update(bank);

            transaction.commit();
            result = session.find(Bank.class, bankId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    @Override
    public void delete(int bankId) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Bank bank = session.find(Bank.class, bankId);

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
