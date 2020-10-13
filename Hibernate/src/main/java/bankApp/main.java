package bankApp;

import bankApp.entities.Customer;
import bankApp.repo.CustomerHibernateRepository;

public class main {
    public static void main(String[] args) {

        Customer customer1 = new Customer();
        customer1.setAddress("Tudor Vladimirescu");
        customer1.setCnp("1800101447799");
        customer1.setDateOfBirth("01-01-1980");
        customer1.setFirstName("Grigore");
        customer1.setLastName("Pop");
        customer1.setEmail("grigorepop@gmail.com");
        customer1.setPhone("0728000999");
        customer1.setUsername("grigore");
        customer1.setPassword("grigore123");
        CustomerHibernateRepository customerHibernateRepository = new CustomerHibernateRepository();
        customerHibernateRepository.create(customer1);

        //        Account account = new Account("RO 12BRD240SV123456123456", 500000, "Debit");
//        AccountHibernateRepository accountHibernateRepository = new AccountHibernateRepository();
//        accountHibernateRepository.create(account);
    }
}
