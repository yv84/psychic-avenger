package me.yv84.springlayout.repository.jdbc;

import me.yv84.springlayout.model.Account;

import java.util.List;


public interface AccountDao {

    List<Account> getAll();

    Account get(Long id);
    
}
