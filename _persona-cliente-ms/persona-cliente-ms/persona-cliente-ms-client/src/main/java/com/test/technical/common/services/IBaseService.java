package com.test.technical.common.services;

public interface IBaseService<T> {
    void save(T obj);

    void update(T obj);
}
