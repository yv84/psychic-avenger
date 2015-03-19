package me.yv84.springlayout.repository.mybatis;

import me.yv84.springlayout.model.Account;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by yv84 on 3/19/15.
 */
public interface AAccountMapper {
    @Select("select u.USERNAME" +
        " from USER as u" +
        " where u.ID=#{id}")
    @Results({
    @Result(id=true, column="ID", property = "id"),
    @Result(column="USERNAME", property="username")
    })
    Account findUserById(Long id);
}
