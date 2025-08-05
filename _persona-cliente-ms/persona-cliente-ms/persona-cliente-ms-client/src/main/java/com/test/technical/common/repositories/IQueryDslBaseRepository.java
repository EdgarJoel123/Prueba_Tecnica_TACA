package com.test.technical.common.repositories;

public interface IQueryDslBaseRepository<T> {
    void save(T obj);

    void update(T obj);
    void delete(T obj);
}
