package me.yv84.springlayout.service.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.jdbc.AccountDao;
import me.yv84.springlayout.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "accountManager")
public class AccountManagerImpl implements AccountManager {

    @Autowired
    private AccountDao accountDao;
    
    @Override
    public List<Account> getAll() {
        return accountDao.getAll();
    }

    @Override
    public Account get(Long id) {
        return accountDao.get(id);
    }

    @Override
    public void add(Account account) {
        accountDao.add(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Account account) {
        accountDao.delete(account);
    }

}
