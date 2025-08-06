package com.test.technical.common.repositories;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

public abstract class JPAQueryDslBaseRepository<T> extends QuerydslRepositorySupport implements
    IQueryDslBaseRepository<T> {


    /**
     * Entity class
     */
    private final Class<T> domainClass;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public JPAQueryDslBaseRepository(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T obj) {
        getEntityManager().merge(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T obj) {
        getEntityManager().remove(obj);
    }

    /**
     * Find page by query
     *
     * @param query JPQL query
     * @param pageable Page
     * @param <Q> Entity
     * @return Page
     */
    protected <Q> Page<Q> findPagedData(JPQLQuery<Q> query, Pageable pageable) {
        JPQLQuery<Q> countQuery = cloneQuery((JPAQuery<Q>) query);
        return PageableExecutionUtils.getPage(getQuerydsl().applyPagination(pageable, query).fetch(), pageable, countQuery::fetchCount);
    }

    /**
     * Clone query
     *
     * @param query JPQL query
     * @param <P> Query by entity
     * @return Query
     */
    protected <P> JPQLQuery<P> cloneQuery(JPAQuery<P> query) {
        return query.clone(getEntityManager());
    }

    /**
     * Update with audit fields.
     *
     * @param path EntityPath
     * @return UpdateClause
     */
    protected UpdateClause<JPAUpdateClause> updateWithAudit(EntityPath<?> path) {
        UpdateClause<JPAUpdateClause> update = super.update(path);
        /*update.set(Expressions.path(String.class,
                getNameFromPath(QAbstractBaseAuditable.abstractBaseAuditable.lastModifiedByUser)),
            keycloakUserInfo.getUserId());
        update.set(Expressions.path(Date.class,
                getNameFromPath(QAbstractBaseAuditable.abstractBaseAuditable.lastModifiedDate)),
            DateUtil.currentDate());*/
        return update;
    }

    /**
     * Get name from path.
     *
     * @param path Path
     * @return name
     */
    protected String getNameFromPath(Path<?> path) {
        return path.getMetadata().getName();
    }

}
