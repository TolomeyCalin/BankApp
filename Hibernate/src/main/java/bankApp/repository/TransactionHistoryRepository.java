package bankApp.repository;

import bankApp.entities.TransactionHistory;

public interface TransactionHistoryRepository {
    void create(TransactionHistory account);

    TransactionHistory findById(int transactionHistoryId);

    TransactionHistory update(int transactionHistoryId, TransactionHistory transactionDetails);

    void delete(int transactionHistoryId);
}
