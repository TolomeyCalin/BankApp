package bankApp.entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TransactionHistory")
@Table(name = "transaction_history")

public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TransactionHistory_Id")
    private int transactionHistoryId;
    @Column(name = "Bank_Id")
    private int bankId;
    @Column(name = "Deposit")
    private double deposit;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "Withdraw")
    private double withdraw;
    @Column(name = "Balance")
    private double balance;
    @Column(name = "changed_at")
    private Timestamp changed_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    private List<TransactionHistory> TransactionHistory = new ArrayList<>();

    public TransactionHistory() {
    }

    public int getBankId() {
        return bankId;
    }
    public List<TransactionHistory> getTransaction() {
        return TransactionHistory;
    }
    public void setTransaction(List<TransactionHistory> transactionHistory) {
        this.TransactionHistory = transactionHistory;
    }

    public TransactionHistory(int bankId, double deposit, double amount,
                              double withdraw, double balance, Timestamp changed_at,
                              Account account, List<bankApp.entities.TransactionHistory> transactionHistory) {
        this.bankId = bankId;
        this.deposit = deposit;
        this.amount = amount;
        this.withdraw = withdraw;
        this.balance = balance;
        this.changed_at = changed_at;
        this.account = account;
        TransactionHistory = transactionHistory;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getTransactionHistoryId() {
        return transactionHistoryId;
    }

    public void setTransactionHistoryId(int transactionHistoryId) {
        this.transactionHistoryId = transactionHistoryId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
    public double getDeposit() {
        return deposit;
    }
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
    public double getWithdraw() {
        return withdraw;
    }
    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Timestamp getChanged_at() {
        return changed_at;
    }
    public void setChanged_at(Timestamp changed_at) {
        this.changed_at = changed_at;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionHistoryId=" + transactionHistoryId +
                ", bankId=" + bankId +
                ", deposit=" + deposit +
                ", amount=" + amount +
                ", withdraw=" + withdraw +
                ", balance=" + balance +
                ", changed_at=" + changed_at +
                ", account=" + account +
                ", TransactionHistory=" + TransactionHistory +
                '}';
    }
}
