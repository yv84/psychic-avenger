package me.yv84.springlayout.repository.jdbc;

import java.util.List;


public interface AccountDao {

    List<Object> getAll();

    Object[] get(Long id);
    
}
