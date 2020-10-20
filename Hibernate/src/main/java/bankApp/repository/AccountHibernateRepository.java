package bankApp.repository;

import bankApp.config.HibernateUtil;
import bankApp.entities.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.logging.Logger;

public class AccountHibernateRepository implements AccountRepository {

    private static final Logger logger = Logger.getLogger(AccountHibernateRepository.class.getName());

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

//    @Override
//    public Account update(int accountId, Account accountDetails) {
//        Account result = null;
//        Transaction transaction = null;
//        try (Session session = getSession()) {
//            Account accountToBeUpdated = session.find(Account.class, accountId);
//
//            transaction = session.beginTransaction();
//
//            accountToBeUpdated.setIban(accountDetails.getIban());
//            accountToBeUpdated.setAccountType(accountDetails.getAccountType());
//
//            session.update(accountToBeUpdated);
//
//            transaction.commit();
//            result = session.find(Account.class, accountId);
//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }

    public List<Account> findAllById(int customerId) {
        List<Account> result = null;
        String query = "SELECT * FROM account where customer_id = '" + customerId +"'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Account> nquery = session.createNativeQuery(query, Account.class);
            result = nquery.getResultList();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }

    public Account findByIban(String iban) {
        Account result = null;
        String query = "select * from account where iban = '" + iban + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Account> nquery = session.createNativeQuery(query, Account.class);
            List<Account> foundAccounts = nquery.getResultList();

            if (foundAccounts.isEmpty()) {
                return result;
            } else {
                result = foundAccounts.get(0);
            }
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }

    public void updateBalance (long id,  double balance){
        Transaction transaction = null;
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            account = session.find(Account.class, id);
            account.setBalance(balance);
            session.update(account);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.warning(e.getMessage());
        }

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
