package com.test.technical.common.services;


import com.test.technical.common.repositories.IQueryDslBaseRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T, R extends IQueryDslBaseRepository<T>> implements
    IBaseService<T> {

    protected final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(T obj) {
        this.repository.save(obj);
    }

    @Transactional
    @Override
    public void update(T obj) {
        this.repository.save(obj);
    }
}
