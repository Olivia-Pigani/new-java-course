package com.example.spring_todos.entity;

import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private Long id;

    private String name;
    private String description;

    private boolean isDone;



}
