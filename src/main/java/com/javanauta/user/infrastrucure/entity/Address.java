package com.javanauta.user.infrastrucure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Long number;

    @Column(name = "complement", length = 50)
    private String complement;

    @Column(name = "city", length = 150)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "postal_code", length = 9)
    private String postal_code;

}
