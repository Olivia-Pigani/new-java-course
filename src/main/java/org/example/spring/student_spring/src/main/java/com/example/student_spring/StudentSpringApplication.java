package com.example.student_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentSpringApplication.class, args);

        System.out.println("http://localhost:8081/");




    }

}
