package me.yv84.springlayout.repository.mybatis;


import me.yv84.springlayout.model.Account;

/**
 * Created by yv84 on 3/19/15.
 */
public interface AccountMapper {
    Account selectByPrimaryKey(Long id);
}
