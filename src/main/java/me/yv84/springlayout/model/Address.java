package me.yv84.springlayout.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by yv84 on 3/20/15.
 */
@Entity(name="address")
public class Address extends BaseModel  {

    @ManyToOne
    @JoinColumn(name="account_fk",
        insertable=false, updatable=false,
        nullable=false)
    private Account account;

    @Column(nullable = true, unique = false)
    private String city;
    
}
