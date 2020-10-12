package bankApp.repo;

import bankApp.entities.Customer;

public interface CustomerRepository {

    void create(Customer customer);

    Customer findById(int customerId);

    Customer update(int customerId, Customer customerDetails);

    void delete(int customerId);
}
