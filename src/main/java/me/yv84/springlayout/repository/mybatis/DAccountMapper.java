package me.yv84.springlayout.repository.mybatis;

import me.yv84.springlayout.model.Account;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by yv84 on 3/19/15.
 */
public interface DAccountMapper {

    @SelectProvider(type=DSQLProvider.class,
        method="selectAccountById")
    @Results({
        @Result(id=true, column="ID", property = "id"),
        @Result(column="USERNAME", property="username")
    })
    Account selectAccountById(Long id);

    @SelectProvider(type=DSQLProvider.class,
        method="selectAllAccounts")
    @Results({
        @Result(id=true, column="ID", property = "id"),
        @Result(column="USERNAME", property="username")
    })
    List<Account> selectAllAccounts();

    @SelectProvider(type=DSQLProvider.class,
        method="insertAccount")
    @SelectKey(statement="call identity()",
        keyProperty="id", before=false, resultType=long.class)
    Long insertAccount(Account account);
    
    @SelectProvider(type=DSQLProvider.class,
        method="updateAccount")
    void updateAccount(Account account);

    @SelectProvider(type=DSQLProvider.class,
        method="deleteAccountById")
    void deleteAccount(Account account);
    
}
