package bankApp.repository;

import bankApp.entities.Account;

public interface AccountRepository {
    void create(Account account);

    Account findById(int accountId);

//    Account update(int accountId, Account accountDetails);

    void delete(int accountId);
}
