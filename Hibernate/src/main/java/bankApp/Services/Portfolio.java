package bankApp.Services;


import bankApp.entities.Account;
import bankApp.entities.Customer;
import bankApp.repository.AccountHibernateRepository;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Portfolio {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Portfolio.class.getName());

    public void viewPortfolio(Customer customer) {

        AccountHibernateRepository accountHibernateRepository = new AccountHibernateRepository();
        List<Account> accounts;

        logger.info("You have the following accounts: ");

        accounts = accountHibernateRepository.findAllById(customer.getId());
        if (accounts == null) {
            logger.info("Invalid account. Please login again");
        } else if (accounts.isEmpty()) {
            logger.info("Cannot find any accounts");
        } else {
            accounts.forEach(account -> logger.info(  account.getId() + " " + account.getBalance() + " " + account.getAccountType() + " " + account.getIban()));
        }
    }
}
