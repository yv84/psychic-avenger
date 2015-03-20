package me.yv84.springlayout.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Set;


@Entity(name="user")
public class Account extends BaseModel {

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Role> roles;

    @OneToOne(mappedBy = "account")
    private Fullname fullname;

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

    @Override
    public String toString() {
        return getUsername();
    }

}
