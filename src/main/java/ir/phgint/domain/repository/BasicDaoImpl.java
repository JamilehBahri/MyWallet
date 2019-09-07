package ir.phgint.domain.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class BasicDaoImpl<T, PK extends Serializable>  implements BasicDao<T, PK> {

    protected Class<T> entityClass;

    public BasicDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T find(T t) {
        return this.entityManager.find(entityClass, t);
    }

    @Override
    public T find(Serializable id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public T save(T t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
//        o = this.entityManager.merge(o);
        this.entityManager.remove(t);

    }
}
