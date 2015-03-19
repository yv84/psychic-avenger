package me.yv84.springlayout.service.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.AccountDao;
import me.yv84.springlayout.repository.mybatis.AAccountMapper;
import me.yv84.springlayout.repository.mybatis.AccountMapper;
import me.yv84.springlayout.repository.mybatis.DAccountMapper;
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

    private AccountMapper mybatisDao;
    public AccountMapper getMuserMapper() {
        return mybatisDao;
    }
    @Autowired
    public void setMuserMapper(AccountMapper mybatisDao) {
        this.mybatisDao = mybatisDao;
    }
    
    private AAccountMapper aMybatisDao;
    public AAccountMapper getAuserMapper() {
        return aMybatisDao;
    }
    @Autowired
    public void setAuserMapper(AAccountMapper aMybatisDao) {
        this.aMybatisDao = aMybatisDao;
    }

    private DAccountMapper dMybatisDao;
    public DAccountMapper getDuserMapper() {
        return dMybatisDao;
    }
    @Autowired
    public void setDuserMapper(DAccountMapper dMybatisDao) {
        this.dMybatisDao = dMybatisDao;
    }
    
    
    @Override
    public List<Account> getAll() {
        return jdbcAccountDao.getAll();
    }

    @Override
    public Account get(Long id) {
        return dMybatisDao.selectAccountById(id);
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
