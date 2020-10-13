package bankApp.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Account")
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_Id")
    private int accountId;
    @Column(name = "IBAN")
    private String iban;
    @Column(name = "Balance")
    private double balance;
    @Column(name = "AccountType")
    private String accountType;

    @OneToMany(
            mappedBy = "bank",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TransactionHistory> transactionHistories = new ArrayList<>();


    public Account(String iban, double balance, String accountType) {
        this.iban = iban;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + accountId +
                ", IBAN=" + iban +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}

