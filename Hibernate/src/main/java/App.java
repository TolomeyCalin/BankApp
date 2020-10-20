import bankApp.Services.StartMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        StartMenu.loadStartMenu();

//        TransactionHistory transactionHistory = new TransactionHistory();
//        TransactionHistoryHibernateRepository transactionHistoryHibernateRepository = new TransactionHistoryHibernateRepository();
//        transactionHistoryHibernateRepository.create(transactionHistory);
//        AppMenu appMenu = new AppMenu();
//        appMenu.bankList();
//        try {
//            appMenu.register();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
