package bankApp.repo;

import bankApp.entities.TransactionHistory;
import bankApp.config.HibernateUtil;
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
    public TransactionHistory findById(int transactionHistoryId) {
        TransactionHistory result = null;
        try (Session session = getSession()) {
            result = session.find(TransactionHistory.class, transactionHistoryId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TransactionHistory update(int transactionHistoryId, TransactionHistory transactionDetails ) {
        TransactionHistory result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            TransactionHistory transactionHistory = session.find(TransactionHistory.class, transactionHistoryId);

            transaction = session.beginTransaction();

            transactionHistory.setAccountId(transactionDetails.getAccountId());
            transactionHistory.setDeposit(transactionDetails.getDeposit());
            transactionHistory.setAmount(transactionDetails.getAmount());
            transactionHistory.setWithdraw(transactionDetails.getWithdraw());
            transactionHistory.setBalance(transactionDetails.getBalance());
            session.update(transactionHistory);

            transaction.commit();
            result = session.find(TransactionHistory.class, transactionHistoryId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void delete(int transactionHistoryId) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            TransactionHistory transactionHistory = session.find(TransactionHistory.class, transactionHistoryId);

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
