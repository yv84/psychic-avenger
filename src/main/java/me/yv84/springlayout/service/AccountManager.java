package me.yv84.springlayout.service;

import me.yv84.springlayout.model.Account;

import java.util.List;


public interface AccountManager {

    List<Account> getAll();

    Account get(Long id);
    Long add(Account account);
    void update(Account account);
    void delete(Account account);
    
}
