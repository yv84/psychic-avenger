package me.yv84.springlayout.repository;

import me.yv84.springlayout.model.Account;

import java.util.List;


public interface AccountDao {

    List<Account> getAll();

    Account get(Long id);
    void add(Account account);
    void update(Account account);
    void delete(Long id);
    void delete(Account account);
    
}
