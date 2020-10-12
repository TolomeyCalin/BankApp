package bankApp;

import bankApp.entities.Account;
import bankApp.repo.AccountHibernateRepository;

public class main {
    public static void main(String[] args) {
        Account account = new Account("RO 12BRD240SV123456123456", 500000, "Debit");
        AccountHibernateRepository accountHibernateRepository = new AccountHibernateRepository();
        accountHibernateRepository.create(account);

    }
}
