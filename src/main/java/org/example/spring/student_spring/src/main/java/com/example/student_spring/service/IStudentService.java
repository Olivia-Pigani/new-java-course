package com.example.student_spring.service;

import com.example.student_spring.model.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    public List<Student> getAllStudents();


    public Student getAStudentById(UUID studentId);


    public Student saveAStudent(Student newStudent);

    public void deleteAStudent(UUID studentId);


    public void updateAStudent(UUID id, Student student);


    public Student getASttudentByHisName(String studentName);


    public List<Student> searchStudents(String search);






}
