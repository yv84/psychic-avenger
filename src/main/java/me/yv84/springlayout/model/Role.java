package me.yv84.springlayout.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by yv84 on 3/20/15.
 */
@Entity(name="role")
public class Role extends BaseModel {

    @Column(nullable = false, unique = true)
    private String authority;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = {
        @JoinColumn(name = "role_fk", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "account_fk",
            nullable = false, updatable = false) })
    private Set<Account> accounts;
    
    public Role() {}

    public Role(String username) {
        this.authority = username;
    }

    public Role(Long id, String username) {
        this.id = id;
        this.authority = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return getAuthority();
    }

}
