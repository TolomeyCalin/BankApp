package BankApp.customer;


import javax.persistence.*;
import java.util.Date;


@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "Date")
    private Date DateOfBirth;
    @Column(name = "CNP")
    private long CNP;
    @Column(name = "Address")
    private String Address;
    @Column(name = "Phone")
    private String Phone;
    @Column(name = "Email")
    private String Email;

    public Customer() {
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public long getCNP() {
        return CNP;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
