package me.yv84.springlayout.repository.jpa.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.AccountDao;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "jpaAccountDao")
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

}
