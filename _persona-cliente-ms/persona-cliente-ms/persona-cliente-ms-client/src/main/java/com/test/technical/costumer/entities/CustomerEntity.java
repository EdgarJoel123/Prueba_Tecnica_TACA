package com.test.technical.costumer.entities;


import com.test.technical.person.entities.PersonEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "prueba_tecnica.pt_customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scsegt_customer_seq")
    @SequenceGenerator(name = "scsegt_customer_seq", sequenceName = "scsegt_customer_seq", schema = "prueba_tecnica", allocationSize = 1)
    @Column(name = "id_customer")
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;

    @Column(name = "person_id", insertable = false, updatable = false)
    private String person_id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity personEntity;
}
