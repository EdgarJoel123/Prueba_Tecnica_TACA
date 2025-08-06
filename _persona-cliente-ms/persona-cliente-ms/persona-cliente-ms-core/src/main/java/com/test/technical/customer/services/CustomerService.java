package com.test.technical.customer.services;





import com.test.technical.common.services.BaseService;
import com.test.technical.costumer.entities.CustomerEntity;
import com.test.technical.costumer.repositories.ICustomerRepository;
import com.test.technical.costumer.services.ICustomService;
import com.test.technical.customer.CustomerCreateDTO;
import com.test.technical.customer.CustomerResponse;
import com.test.technical.customer.CustomerUpdateDTO;
import com.test.technical.person.entities.PersonEntity;
import com.test.technical.person.repositories.IPersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Validated
@Lazy
@Service
@Transactional
public class CustomerService extends BaseService<CustomerEntity, ICustomerRepository> implements ICustomService {

    @Autowired
    private IPersonRepository personRepository;

    public CustomerService(ICustomerRepository repository) {
        super(repository);
    }

    @Override
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(this::getCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerEntity findById(Long customerID) {
        return repository.findById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer Id not found"));
    }

    @Override
    public CustomerResponse create(CustomerCreateDTO customerCreateDTO) {
        try {
            // 1. Validación de campos obligatorios (puedes expandir más si quieres)
            if (customerCreateDTO.getName() == null || customerCreateDTO.getCi() == null) {
                throw new RuntimeException("Datos incompletos para crear la persona.");
            }

            // 2. Crear y guardar la persona
            PersonEntity newPerson = PersonEntity.builder()
                    .name(customerCreateDTO.getName())
                    .gender(customerCreateDTO.getGender())
                    .age(customerCreateDTO.getAge())
                    .ci(customerCreateDTO.getCi())
                    .address(customerCreateDTO.getAddress())
                    .phone(customerCreateDTO.getPhone())
                    .build();

            personRepository.save(newPerson); // Aquí ya se genera el ID

            // 3. Crear y guardar el cliente (usando el ID recién generado)
            CustomerEntity customerEntity = CustomerEntity.builder()
                    .password(customerCreateDTO.getPassword())
                    .status("1")
                    .person_id(newPerson.getId())  // Usa el ID generado
                    .personEntity(newPerson)
                    .build();

            repository.save(customerEntity); // IMPORTANTE: guardar también el customer

            // 4. Retornar el response
            return getCustomerResponse(customerEntity);

        } catch (RuntimeException e) {
            log.error("Error en la creación del Cliente", e);
            throw new RuntimeException("Error en la creación del Cliente: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error inesperado", e);
            throw new RuntimeException("Error inesperado: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerResponse update(CustomerUpdateDTO customerUpdateDTO) {
        try {
            // 1. Buscar el cliente
            Optional<CustomerEntity> optionalCustomer = repository.findById(customerUpdateDTO.getCustomerID());
            if (optionalCustomer.isEmpty()) {
                throw new RuntimeException("Cliente no encontrado con ID: " + customerUpdateDTO.getCustomerID());
            }
            CustomerEntity customerEntity = optionalCustomer.get();

            // 2. Buscar la persona relacionada
            Optional<PersonEntity> optionalPerson = personRepository.findById(customerEntity.getPerson_id());
            if (optionalPerson.isEmpty()) {
                throw new RuntimeException("Persona no encontrada con ID: " + customerEntity.getPerson_id());
            }
            PersonEntity personEntity = optionalPerson.get();

            // 3. Actualizar todos los campos de persona
            personEntity.setName(customerUpdateDTO.getName());
            personEntity.setGender(customerUpdateDTO.getGender());
            personEntity.setAge(customerUpdateDTO.getAge());
            personEntity.setCi(customerUpdateDTO.getCi());
            personEntity.setAddress(customerUpdateDTO.getAddress());
            personEntity.setPhone(customerUpdateDTO.getPhone());

            // 4. Actualizar campos del cliente
            customerEntity.setStatus(customerUpdateDTO.getStatus());

            // 5. Guardar ambas entidades
            personRepository.save(personEntity);    // actualiza o hace merge
            repository.save(customerEntity);        // actualiza o hace merge

            // 6. Construir y devolver respuesta
            return getCustomerResponse(customerEntity);

        } catch (Exception e) {
            log.error("Error al actualizar el cliente con ID: {}", customerUpdateDTO.getCustomerID(), e);
            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long customerID) {
        try {
            // 1. Buscar el cliente
            Optional<CustomerEntity> optionalCustomer = repository.findById(customerID);
            if (optionalCustomer.isEmpty()) {
                throw new RuntimeException("Cliente no encontrado con ID: " + customerID);
            }

            CustomerEntity customerEntity = optionalCustomer.get();

            // 2. Eliminar el cliente
            repository.delete(customerEntity);


        } catch (Exception e) {
            log.error("Error al eliminar el cliente con ID: {}", customerID, e);
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }


    @Override
    public CustomerResponse getCustomerResponse(CustomerEntity customerEntity) {
        return CustomerResponse.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getPersonEntity().getName())
                .gender(customerEntity.getPersonEntity().getGender())
                .age(customerEntity.getPersonEntity().getAge())
                .ci(customerEntity.getPersonEntity().getCi())
                .address(customerEntity.getPersonEntity().getAddress())
                .phone(customerEntity.getPersonEntity().getPhone())
                .status(customerEntity.getStatus())
                .build();

    }
}
