package bankApp.Services;


import bankApp.entities.Customer;
import bankApp.repository.CustomerHibernateRepository;

import java.util.Scanner;
import java.util.logging.Logger;

public class Login {
    private static final Logger logger = Logger.getLogger(Login.class.getName());


    CustomerHibernateRepository customerHibernateRepository = new CustomerHibernateRepository();
    Customer customer;
    boolean login = false;
    int logCounter = 0;

    public Customer loginCheck(){
        do {
            logger.info("Please Enter Username:");
            Scanner userScanner = new Scanner(System.in);
            String username = userScanner.nextLine();
            customer = customerHibernateRepository.findByUsername(username);

            if ("".equals(username)) {
                logger.warning("Username field cannot be empty");
                login = false;

            } else if (customer == null) {
                logger.warning("User not found");
                logCounter++;
                logger.warning(3 - (logCounter) + " login attempts remaining");
                login = false;
            } else {
                login = false;
                logger.info("Please Enter Password:");
                Scanner passScanner = new Scanner(System.in);
                String password = passScanner.nextLine();

                if ("".equals(password)) {
                    logger.warning("This field cannot be empty");
                    login = false;
                } else if (customer != null && !password.equals(customer.getPassword())) {
                    logger.warning("Wrong password! Please try again");
                    logCounter++;
                    logger.warning(3 - (logCounter) + " login attempts remaining");
                    login = false;
                } else {
                    logger.info("Login Successful");
                    login = true;
                    return customer;
                }

                if (logCounter == 3) {
                    logger.info("Login failed. Please try again later.");
                    System.exit(0);
                }

            }
        } while (logCounter < 3 && !login);


        return null;
    }
}

