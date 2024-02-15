package com.example.student_spring.controller;

import com.example.student_spring.model.Student;
import com.example.student_spring.service.IStudentService;
import com.example.student_spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/base")
public class StudentRestController {

    private final IStudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }


    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id")UUID id){
        return studentService.getAStudentById(id);
    }


    @PostMapping("student/add")
    public Student saveAStudent(@RequestBody Student student){
        student.setId(UUID.randomUUID());
        return studentService.saveAStudent(student);
    }


    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable UUID id){
        studentService.deleteAStudent(id);
    }



    @PutMapping("/student/{id}")
    public void updateStudent(@PathVariable UUID id, @RequestBody Student updatedStudent){
         studentService.updateAStudent(id,updatedStudent);
    }


}
