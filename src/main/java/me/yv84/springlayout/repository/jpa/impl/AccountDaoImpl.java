package me.yv84.springlayout.repository.jpa.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.jpa.AccountDao;
import org.springframework.stereotype.Repository;


@Repository(value = "jpaAccountDao")
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {
    
}
