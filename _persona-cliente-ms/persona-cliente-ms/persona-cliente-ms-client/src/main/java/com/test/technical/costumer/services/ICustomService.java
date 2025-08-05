package com.test.technical.costumer.services;


import com.test.technical.costumer.entities.CustomerEntity;
import com.test.technical.customer.CustomerCreateDTO;
import com.test.technical.customer.CustomerResponse;
import com.test.technical.customer.CustomerUpdateDTO;

import java.util.List;
import java.util.Map;

public interface ICustomService {
    List<CustomerResponse> findAll();
    CustomerEntity findById(Long customerID);
    CustomerResponse create(CustomerCreateDTO customerCreateDTO);
    CustomerResponse update(CustomerUpdateDTO customerUpdateDTO);
    void delete(Long customerID);
    CustomerResponse getAnnexedResponse(CustomerEntity customerEntity);
}
