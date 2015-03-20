package me.yv84.springlayout.repository.mybatis;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.model.Fullname;
import org.apache.ibatis.annotations.*;

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
        method="selectFullnameById")
    @Results({
        @Result(id=true, column="ID", property = "id"),
        @Result(column="FIRSTNAME", property="firstname"),
        @Result(column="LASTNAME", property="lastname")
    })
    Fullname selectFullnameById(Long id);

    @SelectProvider(type=DSQLProvider.class,
        method="accountWithFullnameById")
    @Results({
        @Result(id=true, column="ID", property = "id"),
        @Result(column="USERNAME", property="username"),
        @Result(column="fid", property="fullname",
            one=@One(select="selectFullnameById")),
    })
    Account accountWithFullnameById(Long id);

    @SelectProvider(type=DSQLProvider.class,
        method="selectAllAccounts")
    @Results({
        @Result(id=true, column="ID", property = "id"),
        @Result(column="USERNAME", property="username")
    })
    List<Account> selectAllAccounts();

    @InsertProvider(type=DSQLProvider.class,
        method="insertAccount")
    @SelectKey(statement="SELECT MAX(id) + 1 FROM USER",
        keyProperty="id", before=true, resultType=long.class)
    Long insertAccount(Account account);
    
    @UpdateProvider(type=DSQLProvider.class,
        method="updateAccount")
    void updateAccount(Account account);

    @DeleteProvider(type=DSQLProvider.class,
        method="deleteAccountById")
    void deleteAccount(Account account);
    
}
