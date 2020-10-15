package bankApp.Services;

import bankApp.entities.Customer;
import bankApp.repository.CustomerHibernateRepository;

import java.util.Scanner;
import java.util.logging.Logger;

public class Register {

    private static final Logger logger = Logger.getLogger(Register.class.getName());

    public static void register() {


        Scanner scanner = new Scanner(System.in);
        String emptyMessage = "This field should not be empty!\n";
        String invalidMessage = "This field is invalid\n";
        boolean userInputCorrect = false;

        logger.info("Introduce your credentials:");


        String firstName = getFirstName(scanner, emptyMessage, invalidMessage);

        String lastName = getLastName(scanner, emptyMessage, invalidMessage);

        String cnp = getCnp(scanner, emptyMessage, invalidMessage);

        String email = getEmail(scanner, emptyMessage, invalidMessage);

        String username = getUsername(scanner, emptyMessage, invalidMessage);

        String password = getPassword(scanner, emptyMessage, invalidMessage);

        Customer customer = new Customer(firstName, lastName, cnp, email, username, password);
        CustomerHibernateRepository customerHibernateRepository = new CustomerHibernateRepository();
        customerHibernateRepository.create(customer);

        logger.info("Successful registration");

    }

    private static String getLastName(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String lastName;
        do{
            logger.info("Last name: " );
            lastName = scanner.nextLine();

            if (lastName == null || lastName.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect= false;

            } else if (!lastName.matches("^[a-zA-Z]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect= false;
            } else
                userInputCorrect=true;

        }while(!userInputCorrect);
        return lastName;
    }

    private static String getFirstName(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String firstName = null;
        do{
            logger.info("First name: " );
            firstName=scanner.nextLine();

            if (firstName == null || firstName.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect =false;

            } else if (!firstName.matches("^[a-zA-Z]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect =false;

            }else
                userInputCorrect= true;
        }while (!userInputCorrect);
        return firstName;
    }

    private static String getPassword(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String password;
        do{
            logger.info("Password - should contain at least one capital letter and one digit: " );
            password = scanner.nextLine();

            if (password == null || password.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (password.matches("^[a-zA-Z0-9]+$") && password.length() < 6) {
                logger.warning("Password is too short. It should contain at least 6 characters.\n");
                userInputCorrect=false;

            } else if (!password.matches("[a-zA-Z0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            }else
                userInputCorrect= true;

        }while(!userInputCorrect);
        return password;
    }

    private static String getUsername(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String username;
        do{
            logger.info("Username: " );
            username = scanner.nextLine();

            if (username == null || username.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (!username.matches("^[a-zA-Z0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            }else
                userInputCorrect=true;

        }while(!userInputCorrect);
        return username;
    }

    private static String getEmail(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String email;
        do{
            logger.info("Email address: " );
            email = scanner.nextLine();

            if (email == null || email.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            } else
                userInputCorrect= true;

        }while(!userInputCorrect);
        return email;
    }

    private static String getCnp(Scanner scanner, String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String cnp;
        do {
            logger.info("CNP: " );
            cnp = scanner.nextLine();

            if (cnp == null || cnp.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect= false;

            } else if (cnp.matches("^[0-9]+$") && cnp.length() != 13 ) {
                logger.warning("Your CNP should be 13 digits.\n");
                userInputCorrect= false;

            } else if (!cnp.matches("^[0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect= false;

            } else
                userInputCorrect=true;

        } while(!userInputCorrect);
        return cnp;
    }
}