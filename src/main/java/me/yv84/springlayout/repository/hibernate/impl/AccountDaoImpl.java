package me.yv84.springlayout.repository.hibernate.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.AccountDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository(value = "hibernateAccountDao")
public class AccountDaoImpl implements AccountDao {

    static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private static final String GET_ALL_ACCOUNT_HQL
        = "FROM Account a \n";

    private static final String GET_ACCOUNT_HQL
        = "FROM Account a \n"
        + "WHERE a.id = :id";
    
    @Override
    public List<Account> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(GET_ALL_ACCOUNT_HQL);
        return (List<Account>) query.list();
    }

    @Override
    public Account get(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(GET_ACCOUNT_HQL);
        query.setLong("id", id);
        return (Account) query.list().get(0);
    }

    @Override
    public void add(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Account account) {

    }
}
