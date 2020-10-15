package bankApp.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Bank")
@Table(name = "bank")

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "bankName")
    private String bankName;
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="account_bank",
            joinColumns=@JoinColumn(name="bank_id"),
            inverseJoinColumns=@JoinColumn(name="account_id")
    )
    private List<Account> accounts;

    public Bank() {
    }

    public Bank(int id, String bankName,  List<Account> accounts) {
        this.id = id;
        this.bankName = bankName;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
