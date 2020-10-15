import bankApp.entities.TransactionHistory;
import bankApp.repository.TransactionHistoryHibernateRepository;

public class App {
    public static void main(String[] args) {
//        StartMenu.loadStartMenu();

        TransactionHistory transactionHistory = new TransactionHistory();
        TransactionHistoryHibernateRepository transactionHistoryHibernateRepository = new TransactionHistoryHibernateRepository();
        transactionHistoryHibernateRepository.create(transactionHistory);
    }
}
