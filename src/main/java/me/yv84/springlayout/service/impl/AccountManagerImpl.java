package me.yv84.springlayout.service.impl;

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
    public List<Object> getAll() {
        return accountDao.getAll();
    }

    @Override
    public Object[] get(Long id) {
        return accountDao.get(id);
    }
    
}
