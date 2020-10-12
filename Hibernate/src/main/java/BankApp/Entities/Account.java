package BankApp.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "IBAN")
    private Long IBAN;
    @Column(name = "balance")
    private double balance;
    @Column(name = "accountType")
    private String accountType;

    @OneToMany(
            mappedBy = "bank",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TransactionHistory> transactionHistories = new ArrayList<>();


    public Account() {


    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Long getIBAN() {
        return IBAN;
    }

    public void setIBAN(Long IBAN) {
        this.IBAN = IBAN;
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
                "account_id=" + account_id +
                ", IBAN=" + IBAN +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}

