package com.test.technical.customer.controllers;

import com.test.technical.common.Response;
import com.test.technical.costumer.services.ICustomService;
import com.test.technical.customer.CustomerCreateDTO;
import com.test.technical.customer.CustomerResponse;
import com.test.technical.customer.CustomerUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@Lazy
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private ICustomService customService;

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping("/all")
    public ResponseEntity<Response<List<CustomerResponse>>> findAll() {
        try {
            List<CustomerResponse> customers = customService.findAll();
            return ResponseEntity.ok(
                    Response.<List<CustomerResponse>>builder()
                            .data(customers)
                            .code(200)
                            .message("SUCCESS")
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Response.<List<CustomerResponse>>builder()
                            .code(404)
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Operation(summary = "Obtener cliente por ID")
    @GetMapping("/{customer_id}")
    public ResponseEntity<Response<CustomerResponse>> getById(@PathVariable Long customer_id) {
        try {
            CustomerResponse response = customService.getCustomerResponse(customService.findById(customer_id));
            return ResponseEntity.ok(
                    Response.<CustomerResponse>builder()
                            .data(response)
                            .code(200)
                            .message("SUCCESS")
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Response.<CustomerResponse>builder()
                            .code(404)
                            .message("Cliente no encontrado con ID " + customer_id)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Operation(summary = "Crear nuevo cliente")
    @PostMapping("/new")
    public ResponseEntity<Response<CustomerResponse>> create(@Valid @RequestBody CustomerCreateDTO body) {
        try {
            CustomerResponse response = customService.create(body);
            return new ResponseEntity<>(
                    Response.<CustomerResponse>builder()
                            .data(response)
                            .code(201)
                            .message("Cliente creado correctamente")
                            .build(),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Response.<CustomerResponse>builder()
                            .code(400)
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "Actualizar cliente")
    @PatchMapping("/update")
    public ResponseEntity<Response<CustomerResponse>> update(@Valid @RequestBody CustomerUpdateDTO body) {
        try {
            CustomerResponse response = customService.update(body);
            return new ResponseEntity<>(
                    Response.<CustomerResponse>builder()
                            .data(response)
                            .code(200)
                            .message("Cliente actualizado correctamente")
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Response.<CustomerResponse>builder()
                            .code(404)
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Operation(summary = "Eliminar cliente")
    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long customer_id) {
        try {
            customService.delete(customer_id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Cliente no encontrado con ID " + customer_id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
