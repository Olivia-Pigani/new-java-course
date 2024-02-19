package com.example.bdd_spring_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personne")
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;








}
