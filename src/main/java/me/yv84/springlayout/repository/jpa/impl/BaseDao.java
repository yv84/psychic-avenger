package me.yv84.springlayout.repository.jpa.impl;

import me.yv84.springlayout.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;


public class BaseDao<T extends BaseModel> {

    private Class<T> clazz
        = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    public T get(final Long id) {
        if (id != null) {
            T object =  entityManager.find(clazz, id);

            if (object != null) {
                return object;
            }
        }
        return null;
    }
    
}
