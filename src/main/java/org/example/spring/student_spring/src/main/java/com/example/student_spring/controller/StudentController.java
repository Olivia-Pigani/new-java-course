package com.example.student_spring.controller;

import com.example.student_spring.model.Student;
import com.example.student_spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    @Value("${academy.name}")
//    private final String academyName;
//
//    @Value("${academy.contact}")
//    private final String academyContact;


    @GetMapping
    public String getHomePage(Model model) {
        List<Student> studentList = studentService.getAllStudents(); // if its empty
        model.addAttribute("studentList", studentList);
        System.out.println("go to homepage");
        return "home";
    }

    @GetMapping("/studentslist")
    public String getAllStudents(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("studentList", studentList);
        return "home";
    }


    @GetMapping("/details/{studentId}")
    public String getStudentDetails(@PathVariable("studentId") UUID id, Model model) {
        Student student = studentService.getAStudentById(id);
        model.addAttribute("student", student);
        return "student-details";
    }

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }

    @PostMapping("/addstudent")
    public String submitStudent(@ModelAttribute("student") Student newStudent) {
        newStudent.setId(UUID.randomUUID());
        studentService.saveAStudent(newStudent);
        return "redirect:/";
    }


}



