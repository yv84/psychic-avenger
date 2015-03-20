package me.yv84.springlayout.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by yv84 on 3/20/15.
 */
@Entity(name="fullname")
public class Fullname extends BaseModel  {

    @OneToOne(optional = false)
    @JoinColumn(name = "account_fk")
    private Account account;

    @Column(nullable = true, unique = false)
    private String firstname;

    @Column(nullable = true, unique = false)
    private String lastname;

    public Fullname() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Fullname{" +
            "account=" + account +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
    }
}
