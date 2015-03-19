package me.yv84.springlayout.repository.mybatis;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by yv84 on 3/19/15.
 */
public class DSQLProvider {
    
    public String selectAccountById() {
        return new SQL() {{
            SELECT("u.ID, u.USERNAME");
            FROM("USER u");
            WHERE("u.ID=#{id}");
    }}.toString();
    }
    
}
