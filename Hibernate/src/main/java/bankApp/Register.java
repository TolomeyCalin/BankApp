package bankApp;

import bankApp.config.HibernateUtil;
import bankApp.entities.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.logging.Logger;

public class Register {
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    public void register() {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();
             Scanner scanner = new Scanner(System.in)) {

            String emptyMessage = "This field should not be empty!\n";
            String invalidMessage = "This field is invalid\n";
            boolean userInputCorrect = false;

            transaction = session.beginTransaction();
            logger.info("Introduce your credentials:");


            String firstName= null;
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


            String cnp;
            do {
                logger.info("Social Security Number: " );
                cnp = scanner.nextLine();

                if (cnp == null || cnp.isEmpty()) {
                    logger.warning(emptyMessage);
                    userInputCorrect= false;

                } else if (cnp.matches("^[0-9]+$") && cnp.length() != 13 ) {
                    logger.warning("Social Security Number should be 13 digits.\n");
                    userInputCorrect= false;

                } else if (!cnp.matches("^[0-9]+$")) {
                    logger.warning(invalidMessage);
                    userInputCorrect= false;

                } else
                    userInputCorrect=true;

            } while(!userInputCorrect);




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

                } else if (!password.matches("^[0-9]+$")) {
                    logger.warning(invalidMessage);
                    userInputCorrect=false;

                }else
                    userInputCorrect= true;

            }while(!userInputCorrect);



            Customer customer = new Customer(username, password, firstName, lastName,cnp, email);

            session.save(customer);
            transaction.commit();
            logger.info("Successful registration");
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }
}
