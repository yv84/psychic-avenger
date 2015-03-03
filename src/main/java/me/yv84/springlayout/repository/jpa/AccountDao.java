package me.yv84.springlayout.repository.jpa;

import me.yv84.springlayout.model.Account;


public interface AccountDao {
    
    public Account get(final Long id);
    
}
