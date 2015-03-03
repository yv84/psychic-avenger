package me.yv84.springlayout.repository.jpa.impl;

import me.yv84.springlayout.model.BaseModel;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;



public class BaseDao<T extends BaseModel> {

    static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
    
    private Class<T> clazz
        = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    public List<T> getAll() {
        logger.debug("dao.jpa getAll");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        logger.debug("builder-> " + builder);
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        logger.debug("criteria-> " + criteria);
        Root<T> tRoot = criteria.from(clazz);
        Predicate[] predicates = new Predicate[0];
        criteria.where(predicates);
        List<T> result = entityManager.createQuery(criteria).getResultList();
        logger.debug("result-> " + result);
        return result;
    }
    
    public T get(final Long id) {
        logger.debug("dao.jpa get");
        if (id != null) {
            T object =  entityManager.find(clazz, id);

            if (object != null) {
                return object;
            }
        }
        return null;
    }

    public void save(final T entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public void add(final T entity) {
        save(entity);
    }

    public void update(final T entity) {
        save(entity);
    }

    public void delete(final Long entityId) {
        final T entity = get(entityId);
        entityManager.remove(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }
    
}
