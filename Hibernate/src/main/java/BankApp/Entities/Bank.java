package BankApp.Entities;

import javax.persistence.Column;

public class Bank {
    @Column(name = "BankName")
    private String BankName;
    @Column(name = "Customer_id")
    private int Customer_id;
    @Column(name = "Account_id")
    private int Account_id;
    public Bank() {
    }
    public String getBankName() {
        return BankName;
    }
    public void setBankName(String bankName) {
        BankName = bankName;
    }
    public int getCustomer_id() {
        return Customer_id;
    }
    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }
    public int getAccount_id() {
        return Account_id;
    }
    public void setAccount_id(int account_id) {
        Account_id = account_id;
    }
    @Override
    public String toString() {
        return "Bank{" +
                "BankName='" + BankName + '\'' +
                ", Customer_id=" + Customer_id +
                ", Account_id=" + Account_id +
                '}';
    }
}

