package bankApp.repo;

import bankApp.entities.Account;
import bankApp.config.HibernateUtil;
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
    public Account findById(int accountId) {
        Account result = null;
        try (Session session = getSession()) {
            result = session.find(Account.class, accountId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Account update(int accountId, Account accountDetails) {
        Account result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Account accountToBeUpdated = session.find(Account.class, accountId);

            transaction = session.beginTransaction();

            accountToBeUpdated.setIban(accountDetails.getIban());
            accountToBeUpdated.setBalance(accountDetails.getBalance());
            accountToBeUpdated.setAccountType(accountDetails.getAccountType());

            session.update(accountToBeUpdated);

            transaction.commit();
            result = session.find(Account.class, accountId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void delete(int accountId) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Account accountToBeDeleted = session.find(Account.class, accountId);

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
