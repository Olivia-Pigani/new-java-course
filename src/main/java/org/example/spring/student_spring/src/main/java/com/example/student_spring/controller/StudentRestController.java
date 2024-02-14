package com.example.student_spring.controller;

import com.example.student_spring.model.Student;
import com.example.student_spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/base")
public class StudentRestController {

    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }


}
