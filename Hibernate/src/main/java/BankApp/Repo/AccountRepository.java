package BankApp.Repo;

import BankApp.Entities.Account;

public interface AccountRepository {
    void create(Account account);

    Account findById(Long id);

    Account update(Long id, Account accountDetails);

    void delete(Long id);
}
