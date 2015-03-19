package me.yv84.springlayout.repository.mybatis;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by yv84 on 3/19/15.
 */
public class DSQLProvider {

    public String selectAllAccounts() {
        return new SQL() {{
            SELECT("u.ID, u.USERNAME");
            FROM("USER u");
        }}.toString();
    }
    
    public String selectAccountById() {
        return new SQL() {{
            SELECT("u.ID, u.USERNAME");
            FROM("USER u");
            WHERE("u.ID=#{id}");
    }}.toString();
    }

    public String insertAccount() {
        return new SQL() {{
            INSERT_INTO("USER");
            VALUES("USERNAME", "#{username}");
        }}.toString();
    }

    public String updateAccount() {
        return new SQL() {{
            UPDATE("USER");
            SET("USERNAME = #{username}");
            WHERE("ID=#{id}");
        }}.toString();
    }

    public String deleteAccountById() {
        return new SQL() {{
            DELETE_FROM("USER");
            WHERE("ID=#{id}");
        }}.toString();
    }
    
    
    
}
