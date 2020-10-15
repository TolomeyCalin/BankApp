package bankApp.repository;

import bankApp.entities.Bank;

public interface BankRepository {
    void create(Bank bank);

    Bank findById(int bankId);

    Bank update(int bankId, Bank bankName);

    void delete(int bankId);
}
