package ir.phgint.domain.repository.manually;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DaoRepositoryImpl <T, PK extends Serializable>  implements DaoRepository<T, PK> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entityClass;

    private CriteriaBuilder criteriaBuilder = null;

    public DaoRepositoryImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {

        StreamSupport.stream(entities.spliterator(), false)
                .forEach(entityManager::persist);

        return entities;
    }

    @Override
    public T findOne(PK id) {
        return entityManager.find(entityClass, id);

    }

    @Override
    public boolean exists(PK id) {
        return (entityManager.find(entityClass, id) != null ? true : false);
    }

    @Override
    public Iterable<T> findAll() {

        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();

    }

    @Override
    public Iterable<T> findAll(Iterable<PK> ids) {

        return StreamSupport.stream(ids.spliterator(),false).map(this::findOne).collect(Collectors.toList());

    }

    @Override
    public T find(T entitiy) {
        return entityManager.find(entityClass, entitiy);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {

        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.orderBy(orderByClauseBuilder(query, root, sort));
        query.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();

    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        long totalCount = entityCount();

        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        if(pageable.getSort() != null)
            query.orderBy(orderByClauseBuilder(query, root, pageable.getSort()));

        query.select(root);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(), pageable, totalCount);

    }

    @Override
    public void flush() {

        entityManager.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;

    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {

        StreamSupport.stream(entities.spliterator(),false)
               .forEach(entityManager::remove);
    }

    @Override
    public void deleteAllInBatch() {
        Iterable<T> allEntities = findAll();
        StreamSupport.stream(allEntities.spliterator() , false)
                .forEach(entityManager::remove);
    }

    @Override
    public T getOne(PK entity) {
        return entityManager.find(entityClass,entity);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return null;
    }

    @Override
    public long entityCount() {

        CriteriaQuery<Long> query = entityManager.getCriteriaBuilder().createQuery(Long.class);
        Root<T> root = query.from(entityClass);
        Expression<Long> expression =entityManager.getCriteriaBuilder().count(root);
        query.select(expression);
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            logger.info("findByIdWithCmdExec() => query.getSingleResult failed" +
                    " ,exception: " + ex.getMessage(), ex);
        }

        return 0;


    }

    @Override
    public void delete(PK id) {

        entityManager.remove(id);

    }

    @Override
    public void delete(T entity) {

        entityManager.remove(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {

        StreamSupport.stream(entities.spliterator(), false)
                .forEach(entityManager::remove);
    }

    @Override
    public void deleteAll() {
        Iterable<T> allEntities = findAll();
        StreamSupport.stream(allEntities.spliterator() , false)
                .forEach(entityManager::remove);
    }

    protected List<Order> orderByClauseBuilder(CriteriaQuery<T> query, Root<T> root, Sort sort) {

        List<Order> orders = new ArrayList<>();
        while(sort.iterator().hasNext()) {
            Sort.Order order = sort.iterator().next();
            if(order.getDirection() == Sort.Direction.ASC)
                orders.add(entityManager.getCriteriaBuilder().asc(root.get(order.getProperty())));
            else
                orders.add(entityManager.getCriteriaBuilder().desc(root.get(order.getProperty())));
        }
        return orders;
    }

}
