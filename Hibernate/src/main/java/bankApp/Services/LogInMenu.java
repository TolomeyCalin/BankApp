package bankApp.Services;

import bankApp.entities.Customer;

import java.util.Scanner;
import java.util.logging.Logger;

public class LogInMenu {

    private static final Logger logger = Logger.getLogger(LogInMenu.class.getName());

    public void showMenu(Customer customer) {
        Portfolio portfolio = new Portfolio();
        Transfer transfer = new Transfer();
        Deposit deposit = new Deposit();
//        Withdraw withdraw = new Withdraw();


        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        logger.info ("Welcome " + customer.getUserName() + "!");


        logger.info ("\n\u001B[7m\033[1;30m        SDABank Control Panel        \033[0m\n\n"
                +"[\033[1;31m(1)\u001B[0m] View portfolio and balance\n"
                +"[\033[1;32m(2)\u001B[0m] Transfer money\n"
                +"[\033[1;34m(3)\u001B[0m] Deposit money\n"
                +"[\033[1;35m(4)\u001B[0m] Withdraw money\n"
                +"[\033[1;36m(5)\u001B[0m] Exit\n");

        do {
            System.out.println("\n\u001B[7m\033[1;30m        Please select a service        \033[0m\n\n");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    portfolio.viewPortfolio(customer);
                    System.out.println();
                    break;
                case 2:
                    transfer.transferMoney(customer);
                    break;
                case 3:
                    deposit.depositMoney(customer);
                    System.out.println();
                    break;
//                case 4:
//                    logger.info("**********************");
//                    withdraw.withdrawMoney(customer);
//                    logger.info("**********************");
//                    break;
                case 5:
                    break;
                default:
                    logger.info("Invalid choice. Select one of the above options.");
                    System.out.println();
                    break;
            }

         }while(choice != 5);
        System.out.println("Thank you for using our services. Good bye!");
    }

}
