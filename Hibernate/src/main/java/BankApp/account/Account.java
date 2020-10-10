package BankApp.account;

public class Account {
    private int account_id;
    private Long IBAN;
    private double balance;
    private String accountType;

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

