package BankApp.Entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Transaction")
@Table(name = "Transaction")

public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Transaction_id")
    private int Transaction_id;
    @Column(name = "Account_id")
    private int Account_id;
    @Column(name = "Deposit")
    private double Deposit;
    @Column(name = "Amount")
    private double Amount;
    @Column(name = "WithDraw")
    private double WithDraw;
    @Column(name = "Balance")
    private double Balance;
    @Column(name = "changed_at")
    private Timestamp changed_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    private List<TransactionHistory> TransactionHistory = new ArrayList<>();

    public TransactionHistory() {
    }

    public int getAccount_id() {
        return Account_id;
    }
    public List<TransactionHistory> getTransaction() {
        return TransactionHistory;
    }
    public void setTransaction(List<TransactionHistory> transactionHistory) {
        this.TransactionHistory = transactionHistory;
    }
    public double getAmount() {
        return Amount;
    }
    public void setAmount(double amount) {
        Amount = amount;
    }
    public void setAccount_id(int account_id) {
        Account_id = account_id;
    }
    public double getDeposit() {
        return Deposit;
    }
    public void setDeposit(double deposit) {
        Deposit = deposit;
    }
    public double getWithDraw() {
        return WithDraw;
    }
    public void setWithDraw(double withDraw) {
        WithDraw = withDraw;
    }
    public double getBalance() {
        return Balance;
    }
    public void setBalance(double balance) {
        Balance = balance;
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
                "Transaction_id=" + Transaction_id +
                ", Account_id=" + Account_id +
                ", Deposit=" + Deposit +
                ", Amount=" + Amount +
                ", WithDraw=" + WithDraw +
                ", Balance=" + Balance +
                ", changed_at=" + changed_at +
                ", account=" + account +
                ", TransactionHistory=" + TransactionHistory +
                '}';
    }
}
