package bankApp.entities;

import javax.persistence.*;
import java.security.Timestamp;

@Entity(name = "TransactionHistory")
@Table(name = "transactionhistory")

public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "deposit")
    private double deposit;
    @Column(name = "amount")
    private double amount;
    @Column(name = "withdraw")
    private double withdraw;
    @Column(name = "balance")
    private double balance;
    @Column(name = "currency")
    private String currency;
    @Column(name = "changed_at")
    private Timestamp changed_at;


    public TransactionHistory() {
    }
    public TransactionHistory(int id, double deposit, double amount, double withdraw, double balance, String currency, Timestamp changed_at) {
        this.id = id;
        this.deposit = deposit;
        this.amount = amount;
        this.withdraw = withdraw;
        this.balance = balance;
        this.currency = currency;
        this.changed_at = changed_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
                "id=" + id +
                ", deposit=" + deposit +
                ", amount=" + amount +
                ", withdraw=" + withdraw +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", changed_at=" + changed_at +
                '}';
    }
}

