package bankApp.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Account")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "IBAN")
    private String iban;
    @Column(name = "accountType")
    private String accountType;
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="account_id")
    private List<TransactionHistory> transactionHistoryList;
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="account_bank",
            joinColumns=@JoinColumn(name="account_id"),
            inverseJoinColumns=@JoinColumn(name="bank_id")
    )
    private List<Bank> banks;

    public Account() {
    }

    public Account(int id, String iban, String accountType, Customer customer, List<TransactionHistory> transactionHistoryList, List<Bank> banks) {
        this.id = id;
        this.iban = iban;
        this.accountType = accountType;
        this.customer = customer;
        this.transactionHistoryList = transactionHistoryList;
        this.banks = banks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TransactionHistory> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public void setTransactionHistoryList(List<TransactionHistory> transactionHistoryList) {
        this.transactionHistoryList = transactionHistoryList;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", accountType='" + accountType + '\'' +
                ", customer=" + customer +
                ", transactionHistoryList=" + transactionHistoryList +
                ", banks=" + banks +
                '}';
    }
}