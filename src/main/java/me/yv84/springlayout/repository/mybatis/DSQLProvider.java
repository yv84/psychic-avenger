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
            VALUES("ID", "#{id}");
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
    
    public String accountWithFullnameById() {
        return new SQL() {{
            SELECT("u.id, u.username, f.ID as fid");
            FROM("USER AS u");
            INNER_JOIN("FULLNAME AS f ON u.id = f.id");
            WHERE("u.ID=#{id}");
        }}.toString();
    }
    
    public String selectFullnameById() {
        return new SQL() {{
            SELECT("f.FIRSTNAME, f.LASTNAME");
            FROM("FULLNAME AS f");
            WHERE("f.ID=#{id}");
        }}.toString();
    }

}
