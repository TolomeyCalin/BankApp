package BankApp.Repo;

import BankApp.Entities.Account;
import BankApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountHibernateRepository implements AccountRepository {

    @Override
    public void create(Account account) {
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
    public Account findById(Long id) {
        Account result = null;
        try (Session session = getSession()) {
            result = session.find(Account.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Account update(Long id, Account accountDetails) {
        Account result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Account accountToBeUpdated = session.find(Account.class, id);

            transaction = session.beginTransaction();

            accountToBeUpdated.setIBAN(accountDetails.getIBAN());
            accountToBeUpdated.setBalance(accountDetails.getBalance());
            accountToBeUpdated.setAccountType(accountDetails.getAccountType());

            session.update(accountToBeUpdated);

            transaction.commit();
            result = session.find(Account.class, id);
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
            Account accountToBeDeleted = session.find(Account.class, id);

            transaction = session.beginTransaction();

            session.delete(accountToBeDeleted);

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
