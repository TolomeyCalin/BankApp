package bankApp.Services;


import bankApp.entities.Account;
import bankApp.entities.Customer;
import bankApp.entities.TransactionHistory;
import bankApp.repository.AccountHibernateRepository;
import bankApp.repository.TransactionHistoryHibernateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Deposit {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Deposit.class.getName());

    public void depositMoney(Customer customer) {

        AccountHibernateRepository accountHibernateRepository = new AccountHibernateRepository();
        long accountIdReceiver;

        showAccounts(customer, accountHibernateRepository);

        Account accountToBeUpdated = getAccountToBeUpdated(accountHibernateRepository);
        accountIdReceiver = accountToBeUpdated.getId();
        LocalDate transactionDate = LocalDate.now();
        double sumToDeposit = getSumToDeposit();
        final String description = "Deposit";


        TransactionHistoryHibernateRepository transactionHistoryHibernateRepository = new TransactionHistoryHibernateRepository();
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistoryHibernateRepository.create(transactionHistory);


        double newBalance = accountToBeUpdated.getBalance() + sumToDeposit;
        accountHibernateRepository.updateBalance(accountToBeUpdated.getId(), newBalance);
        logger.info("Your new balance is " + newBalance);


    }

    private double getSumToDeposit() {
        double sumToDeposit = 0;
        boolean isValid = false;

        while (!isValid) {
            logger.info("Insert the amount to be deposited:");
            if (scanner.hasNextDouble()) {
                sumToDeposit = scanner.nextDouble();
                isValid = true;
                if (sumToDeposit <= 0) {
                    logger.info("Invalid amount. Try again");
                    isValid = false;
                }
            } else {
                logger.info("Invalid amount. Try again");
            }
            scanner.nextLine();
        }
        return sumToDeposit;
    }

    private Account getAccountToBeUpdated(AccountHibernateRepository accountHibernateRepository) {
        Account accountToBeUpdated;
        logger.info("Select account.");
        boolean input = false;

        do {
            logger.info("Type your iban");
            String iban = scanner.nextLine();
            accountToBeUpdated = accountHibernateRepository.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (accountToBeUpdated == null) {
                logger.warning("Account not found. Try again");
                input = false;
            } else {
                input = true;
            }

        }while(!input);
        return accountToBeUpdated;
    }

    private void showAccounts(Customer customer, AccountHibernateRepository accountHibernateRepository) {
        List<Account> accounts;
        logger.info("You have the following accounts");
        accounts = accountHibernateRepository.findAllById(customer.getId());

        if (accounts == null)
            logger.info("null");
        else if (accounts.isEmpty())
            logger.info("Cannot find any accounts");
        else {
            accounts.forEach(account->logger.info(account.getBalance()+  " " + account.getAccountCurrencyEnum()+  " " + account.getIban()));}
    }
}
