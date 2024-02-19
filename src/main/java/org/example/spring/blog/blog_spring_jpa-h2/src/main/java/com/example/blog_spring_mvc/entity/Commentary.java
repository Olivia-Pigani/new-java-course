package com.example.blog_spring_mvc.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull(message = "you must type your username")
    @NotBlank
    @Size(min = 10,max = 50)
    private String userName;


    @NotNull
    @NotBlank
    @Email(message = "you must type a valid email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 10,max = 500)
    private String content;

    private BlogPost blogPost;

}
