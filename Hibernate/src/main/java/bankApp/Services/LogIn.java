package bankApp.Services;



import bankApp.repository.CustomerHibernateRepository;

import java.util.Scanner;
import java.util.logging.Logger;

public class LogIn {

    private static final Logger logger = Logger.getLogger(Register.class.getName());



    public static void login() {
        CustomerHibernateRepository customerHibernateRepository = new CustomerHibernateRepository();
        boolean login = false;
        int logcounter = 0;


        while (logcounter < 3 && login == false) {

            logger.info("Please Enter Username:");
            Scanner userscanner = new Scanner(System.in);
            String username = userscanner.nextLine();


            if ("".equals(username)) {
                logger.warning("Username field cannot be empty");
            } else if ( username == null) {
                logger.warning("User not found");
                logcounter++;
                logger.warning(3 - (logcounter) + " login attempts remaining");
            } else {

                System.out.println("-------------------");
                while (logcounter < 3 && login == false) {
                    logger.info("Please Enter Password:");
                    Scanner passscanner = new Scanner(System.in);
                    String password = passscanner.nextLine();
                    long customerIdByUsername = customerHibernateRepository.findIdByUsername(username);
                    long customerIdByPassword = customerHibernateRepository.findIdByPassword(password);


                    if ("".equals(password)) {
                        logger.warning("This filed cannot be empty");

                    } else if (customerIdByUsername != customerIdByPassword) {
                        logger.warning("Wrong password! Please try again");
                        logcounter++;
                        logger.warning(3 - (logcounter) + " login attempts remaining");

                    } else if (customerIdByUsername == customerIdByPassword) {
                        logger.info("LOGIN SUCCESSFUL");
                        login = true;
                        break;

                    }

                }
            }

        }
    }
}
