package me.yv84.springlayout.service;

import java.util.List;


public interface AccountManager {

    List<Object> getAll();

    Object[] get(Long id);
    
}
