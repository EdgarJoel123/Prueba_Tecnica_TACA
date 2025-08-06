package com.test.technical.person.repositories;



import com.test.technical.common.repositories.IQueryDslBaseRepository;

import com.test.technical.person.entities.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends IQueryDslBaseRepository<PersonEntity> {
    List<PersonEntity> findAll();
    Optional<PersonEntity> findById(Long person_id);
}
