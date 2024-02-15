package com.example.student_spring.controller;

import com.example.student_spring.model.Student;
import com.example.student_spring.service.IStudentService;
import com.example.student_spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;


    @Value("M2I")
    private String academyName;

    @Value("otto@m2i.com")
    private String academyContact;



    @GetMapping
    public String getHomePage(Model model) {
        List<Student> studentList = studentService.getAllStudents(); // if its empty
        model.addAttribute("studentList", studentList);


        model.addAttribute("name",academyName);
        model.addAttribute("contact",academyContact);


        System.out.println("go to homepage");
        return "home";
    }

    @GetMapping("/studentslist")
    public String getAllStudents(@RequestParam(name = "search",required = false) String search,Model model) {
        if (search == null ){
            model.addAttribute("studentList", studentService.getAllStudents());
        }else {
            StudentService castedService = (StudentService) studentService;
            model.addAttribute("studentList", castedService.searchStudents(search));
        }

        return "home";
    }


    @GetMapping("/details/{studentId}")
    public String getStudentDetails(@PathVariable("studentId") UUID id, Model model) {
        Student student = studentService.getAStudentById(id);
        if (student == null){
            return "redirect:/";
        }else {
            model.addAttribute("student", student);
            return "student-details";
        }

    }

    @GetMapping("/form")
    public String getForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }
    @GetMapping("/form/{studentId}")
    public String showUpdateForm(@PathVariable("studentId") UUID id, Model model) {
        Student studentToUpdate = studentService.getAStudentById(id);
        if (studentToUpdate != null) {
            model.addAttribute("student", studentToUpdate);
            return "form";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/addOrUpdateStudent")
    public String submitStudent(@ModelAttribute("student") Student student) {
        if (student.getId() == null) {
            studentService.saveAStudent(student);
        } else {
            studentService.updateAStudent(student.getId(), student);
        }
        return "redirect:/";
    }

    @PostMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("studentId") UUID id) {
        studentService.deleteAStudent(id);
        return "redirect:/";
    }







}



