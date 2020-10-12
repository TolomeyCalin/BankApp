package BankApp.Repo;

import BankApp.Entities.TransactionHistory;

public interface TransactionHistoryRepository {
    void create(TransactionHistory account);

    TransactionHistory findById(Long id);

    TransactionHistory update(Long id, TransactionHistory accountDetails);

    void delete(Long id);
}
