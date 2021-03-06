package me.yv84.springlayout.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;


@Entity(name="user")
public class Account extends BaseModel {

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Role> roles;

    @OneToOne(mappedBy = "account")
    private Fullname fullname;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "address_fk")
    private List<Address> addresses;

    public Account() {}

    public Account(String username) {
        this.username = username;
    }
    
    public Account(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Fullname getFullname() {
        return fullname;
    }

    public void setFullname(Fullname fullname) {
        this.fullname = fullname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Account id = " + id +
            " {" +
            "username='" + username + '\'' +
            ", roles=" + roles +
            ", fullname=" + fullname +
            ", addresses=" + addresses +
            '}';
    }
}
