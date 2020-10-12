package BankApp.Repo;

import BankApp.Entities.Bank;

public interface BankRepository {
    void create(Bank account);

    Bank findById(Long id);

    Bank update(Long id, Bank accountDetails);

    void delete(Long id);
}
