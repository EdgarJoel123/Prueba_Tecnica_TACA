package com.test.technical.person.repositories;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.test.technical.common.repositories.JPAQueryDslBaseRepository;
import com.test.technical.person.entities.PersonEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.test.technical.person.entities.QPersonEntity.personEntity;


@Lazy
@Repository
public class PersonRepository extends JPAQueryDslBaseRepository<PersonEntity> implements IPersonRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public PersonRepository() {
        super(PersonEntity.class);
    }

    @Override
    public List<PersonEntity> findAll() {
        return from(personEntity)
                .orderBy(new OrderSpecifier<>(Order.DESC, personEntity.id))
                .fetchAll().fetch();
    }

    @Override
    public Optional<PersonEntity> findById(Long person_id) {
        return from(personEntity)
                .where(personEntity.id.eq(person_id))
                .stream().findAny();
    }
}
