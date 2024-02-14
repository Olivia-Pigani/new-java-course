package com.example.student_spring.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private UUID id;
    private String prenom;
    private String nom;
    private String email;
    private int age;
//    @Value("${academy.name}")
//    private String academyName;
//    @Value("${academy.contact}")
//    private String academyContact;

}