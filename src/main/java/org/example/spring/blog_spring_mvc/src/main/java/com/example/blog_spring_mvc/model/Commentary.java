package com.example.blog_spring_mvc.model;


import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {

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
