package me.yv84.springlayout.service.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.AccountDao;
import me.yv84.springlayout.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "accountManager")
public class AccountManagerImpl implements AccountManager {

    @Autowired
    private AccountDao jdbcAccountDao;
    
    @Autowired
    private AccountDao jpaAccountDao;

    @Autowired
    private AccountDao hibernateAccountDao;
    
    @Override
    public List<Account> getAll() {
        return hibernateAccountDao.getAll();
    }

    @Override
    public Account get(Long id) {
        return hibernateAccountDao.get(id);
    }

    @Override
    public void add(Account account) {
        jdbcAccountDao.add(account);
    }

    @Override
    public void update(Account account) {
        jdbcAccountDao.update(account);
    }

    @Override
    public void delete(Account account) {
        jdbcAccountDao.delete(account);
    }

}
