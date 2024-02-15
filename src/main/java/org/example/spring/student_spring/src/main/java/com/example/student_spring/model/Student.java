package com.example.student_spring.model;

import com.example.student_spring.validation.MyValid;
import jakarta.validation.constraints.*;
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


    @NotNull(message = "firstName can not be null")
    @NotBlank
    @MyValid
    private String prenom;

    @NotNull(message = "lastName can not be null")
    @NotBlank
    @Size(min = 1, max = 60, message = "Name must have more than 1 characters and less than 60 characters")
    private String nom;


    @NotBlank
    @NotNull
    @Email(message = "Email is not valid")
//    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Email is not valid" )
    private String email;


    @Min(18)
    @Max(120)
    private int age;


}
