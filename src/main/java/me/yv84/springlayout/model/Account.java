package me.yv84.springlayout.model;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name="user")
public class Account extends BaseModel {

    @Column(nullable = false, unique = true)
    private String username;


    public String getUsername() {
        return username;
    }

    public Account() {}
    
    public Account(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return getUsername();
    }

}
