package bankApp.Services;


import bankApp.entities.Account;
import bankApp.entities.AccountCurrencyEnum;
import bankApp.entities.Customer;
import bankApp.entities.TransactionHistory;
import bankApp.repository.AccountHibernateRepository;
import bankApp.repository.TransactionHistoryHibernateRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Transfer {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Transfer.class.getName());


    public void transferMoney(Customer customer) {

        AccountHibernateRepository accountHibernateRepository = new AccountHibernateRepository();
        long accountIdReceiver;

        showAccounts(customer, accountHibernateRepository);

        Account senderAccount = getSenderAccount(accountHibernateRepository);
        Account receiverAccount = getReceiverAccount(accountHibernateRepository);
        accountIdReceiver = receiverAccount.getId();
        double sumToTransfer = getSumToTransfer(senderAccount);
        LocalDate transactionDate = LocalDate.now();
        double sumToTransferConversed = getSumToTransferConversed(senderAccount, receiverAccount, sumToTransfer);


        TransactionHistoryHibernateRepository transactionHistoryHibernateRepository = new TransactionHistoryHibernateRepository();
        TransactionHistory transactions = new TransactionHistory();
        transactionHistoryHibernateRepository.create(transactions);


        BigDecimal bd = BigDecimal.valueOf(sumToTransfer).setScale(3, RoundingMode.HALF_EVEN);
        double newBalanceForSender = senderAccount.getBalance() - bd.doubleValue();
        accountHibernateRepository.updateBalance(senderAccount.getId(), newBalanceForSender);


        bd = BigDecimal.valueOf(sumToTransferConversed).setScale(3, RoundingMode.HALF_EVEN);
        double newBalanceForReceiver = receiverAccount.getBalance() + bd.doubleValue();
        accountHibernateRepository.updateBalance(receiverAccount.getId(), newBalanceForReceiver);

        logger.info( "Your new balance is " + newBalanceForSender);

    }

    private double getSumToTransferConversed(Account senderAccount, Account receiverAccount, double sumToTransfer) {
        double sumToTransferConversed = 0.0;

        if ( senderAccount.getAccountCurrencyEnum().toString().equals(receiverAccount.getAccountCurrencyEnum().toString())){
            sumToTransferConversed = sumToTransfer;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR)){
            sumToTransferConversed = sumToTransfer/ 4.89;


        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON)){
            sumToTransferConversed = sumToTransfer * 4.89;


        }
        return sumToTransferConversed;
    }

    private Account getReceiverAccount(AccountHibernateRepository accountHibernateRepository) {
        Account receiverAccount;
        String iban;
        boolean input;
        do {
            logger.info("Type receiver's iban");
            iban = scanner.nextLine();
            receiverAccount = accountHibernateRepository.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (receiverAccount == null) {
                logger.warning("Account not found. Try again");
                input = false;
            } else {

                input = true;
            }
        } while(!input);
        return receiverAccount;
    }

    private double getSumToTransfer(Account senderAccount) {
        double sumToTransfer =0;
        boolean isValid = false;
        while (!isValid) {
            logger.info("Insert the amount to be sent:");
            if (scanner.hasNextDouble()) {
                sumToTransfer = scanner.nextDouble();
                isValid = true;
                if (sumToTransfer <= 0) {
                    logger.info("Invalid amount. Try again");
                    isValid = false;
                }
                if (sumToTransfer > senderAccount.getBalance()) {
                    logger.info("Insufficient funds");
                    isValid = false;
                }
            } else {
                logger.info("Invalid amount. Try again");
            }
            scanner.nextLine();
        }
        return sumToTransfer;
    }

    private Account getSenderAccount(AccountHibernateRepository accountHibernateRepository) {
        Account senderAccount;
        logger.info("Select account.");
        boolean input = false;

        do {
            logger.info("Type your iban");
            String iban = scanner.nextLine();
            senderAccount = accountHibernateRepository.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (senderAccount == null) {
                logger.warning("Account not found. Try again");
                input = false;

            } else {
                input = true;
            }

        }while(!input);
        return senderAccount;
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
