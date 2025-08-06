package com.test.technical.costumer.repositories;

import com.test.technical.common.repositories.IQueryDslBaseRepository;
import com.test.technical.costumer.entities.CustomerEntity;
import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends IQueryDslBaseRepository<CustomerEntity> {
    List<CustomerEntity> findAll();
    Optional<CustomerEntity> findById(Long customer_id);
}
