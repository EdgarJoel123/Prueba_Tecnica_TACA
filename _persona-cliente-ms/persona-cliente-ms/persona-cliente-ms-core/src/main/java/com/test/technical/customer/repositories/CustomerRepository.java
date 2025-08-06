package com.test.technical.customer.repositories;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.test.technical.common.repositories.JPAQueryDslBaseRepository;
import com.test.technical.costumer.entities.CustomerEntity;
import com.test.technical.costumer.repositories.ICustomerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.test.technical.costumer.entities.QCustomerEntity.customerEntity;

@Lazy
@Repository
public class CustomerRepository extends JPAQueryDslBaseRepository<CustomerEntity> implements ICustomerRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public CustomerRepository() {
        super(CustomerEntity.class);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return from(customerEntity)
                .orderBy(new OrderSpecifier<>(Order.DESC, customerEntity.id))
                .fetchAll().fetch();
    }

    @Override
    public Optional<CustomerEntity> findById(Long customer_id) {
        return from(customerEntity)
                .where(customerEntity.id.eq(customer_id))
                .stream().findAny();
    }
}
