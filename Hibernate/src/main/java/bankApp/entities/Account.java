package bankApp.entities;


import bankApp.Services.Iban;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Account")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "IBAN")
    private String iban;
    @Column(name = "balance")
    private double balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "accountType")
    private accountType accountType;
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column (name = "currency")
    @Enumerated (EnumType.STRING)
    private AccountCurrencyEnum accountCurrencyEnum;
    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="transaction_id")
    private List<TransactionHistory> transactionHistory;

    public Account() {
    }

    public Account(int id, String iban, Double balance, accountType accountType, Customer customer, List<TransactionHistory> transactionHistory, Enum accountCurrencyEnum) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.accountType = accountType;
        this.customer = customer;
        this.transactionHistory = transactionHistory;
        this.accountCurrencyEnum = (AccountCurrencyEnum) accountCurrencyEnum;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountCurrencyEnum getAccountCurrencyEnum() {
        return accountCurrencyEnum;
    }

    public void setAccountCurrencyEnum(AccountCurrencyEnum accountCurrencyEnum) {
        this.accountCurrencyEnum = accountCurrencyEnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban() {
        this.iban = Iban.ibanGenerator();
    }

    public bankApp.entities.accountType getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        switch (accountType) {
            case "CREDIT EURO":
                   this.accountType = bankApp.entities.accountType.CREDIT_EURO;
                   break;
            case "CREDIT RON":
                    this.accountType = bankApp.entities.accountType.CREDIT_RON;
            case "DEBIT EURO":
                    this.accountType = bankApp.entities.accountType.DEBIT_EURO;
            case "DEBIT RON":
                    this.accountType = bankApp.entities.accountType.DEBIT_RON;
                break;
            default:
        }
    }

    public List<TransactionHistory> getTransactionHistoryList() {
        return transactionHistory;
    }

    public void setTransactionHistoryList(List<TransactionHistory> transactionHistoryList) {
        this.transactionHistory = transactionHistoryList;
    }

    public void addTransactions(TransactionHistory tempTransaction) {

        if (transactionHistory == null) {
            transactionHistory = new ArrayList<>();
        }
        transactionHistory.add(tempTransaction);
        tempTransaction.setAccount(this);
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", customer=" + customer +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}

