package BankApp.transaction;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Transaction")
@Table(name = "Transaction")

public class Transaction {
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
    @ManyToMany(mappedBy = "AccountDetails")
    private List<Transaction> Transaction = new ArrayList<>();

    public Transaction() {
    }

    public int getAccount_id() {
        return Account_id;
    }
    public List<Transaction> getTransaction() {
        return Transaction;
    }
    public void setTransaction(List<Transaction> transaction) {
        this.Transaction = transaction;
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

}
