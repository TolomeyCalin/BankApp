package bankApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Bank")
@Table(name = "bank")

public class Bank {
    @Id
    @Column(name = "Bank_Id")
    private int bankId;
    @Column(name = "BankName")
    private String bankName;
    @Column(name = "Customer_Id")
    private int customerId;
    @Column(name = "Account_Id")
    private int accountId;
    public Bank() {
    }
    public String getBankName() {
        return bankName;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "Bank_id=" + bankId +
                ", BankName='" + bankName + '\'' +
                ", Customer_id=" + customerId +
                ", Account_id=" + accountId +
                '}';
    }
}


