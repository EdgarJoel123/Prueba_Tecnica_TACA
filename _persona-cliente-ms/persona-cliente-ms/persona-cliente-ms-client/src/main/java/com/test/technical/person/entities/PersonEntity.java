package com.test.technical.person.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "prueba_tecnica.pt_person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scsegt_persona_seq")
    @SequenceGenerator(
            name = "scsegt_persona_seq",
            sequenceName = "scsegt_persona_seq",
            schema = "prueba_tecnica",
            allocationSize = 1
    )
    @Column(name = "id_person")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age; // ❗ Estaba mal mapeado antes

    @Column(name = "ci")
    private String ci;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}

