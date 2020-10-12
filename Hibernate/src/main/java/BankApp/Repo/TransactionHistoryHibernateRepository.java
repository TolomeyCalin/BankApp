package BankApp.Repo;

import BankApp.Entities.TransactionHistory;
import BankApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionHistoryHibernateRepository implements TransactionHistoryRepository {

    @Override
    public void create(TransactionHistory account) {
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
    public TransactionHistory findById(Long id) {
        TransactionHistory result = null;
        try (Session session = getSession()) {
            result = session.find(TransactionHistory.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TransactionHistory update(Long id, TransactionHistory accountDetails) {
        TransactionHistory result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            TransactionHistory transactionHistory = session.find(TransactionHistory.class, id);

            transaction = session.beginTransaction();

            transactionHistory.setAccount_id(accountDetails.getAccount_id());
            transactionHistory.setDeposit(accountDetails.getDeposit());
            transactionHistory.setAmount(accountDetails.getAmount());
            transactionHistory.setWithDraw(accountDetails.getWithDraw());
            transactionHistory.setBalance(accountDetails.getBalance());
            session.update(transactionHistory);

            transaction.commit();
            result = session.find(TransactionHistory.class, id);
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
            TransactionHistory transactionHistory = session.find(TransactionHistory.class, id);

            transaction = session.beginTransaction();

            session.delete(transactionHistory);

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
