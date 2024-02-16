package com.example.student_spring.service;

import com.example.student_spring.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;





// REPLACE REPOSITORY
@Service
public class StudentService implements IStudentService{


    private final Map<UUID, Student> students;

    public StudentService() {
        this.students = new HashMap<>();

        Student student1 = Student.builder()
                .id(UUID.randomUUID())
                .prenom("Ben")
                .nom("Affleck")
                .age(45)
                .email("ba@gmail.com")
                .build();

        Student student2 = Student.builder()
                .id(UUID.randomUUID())
                .prenom("Alicia")
                .nom("Keys")
                .age(38)
                .email("ak@gmail.com")
                .build();

        Student student3 = Student.builder()
                .id(UUID.randomUUID())
                .prenom("David")
                .nom("Hasseloff")
                .age(55)
                .email("dh@gmail.com")
                .build();

        Student student4 = Student.builder()
                .id(UUID.randomUUID())
                .prenom("Meryl")
                .nom("Streep")
                .age(70)
                .email("ms@gmail.com")
                .build();

        Student student5 = Student.builder()
                .id(UUID.randomUUID())
                .prenom("Chuck")
                .nom("Norris")
                .age(70)
                .email("cn@gmail.com")
                .build();


        students.put(student1.getId(),student1);
        students.put(student2.getId(),student2);
        students.put(student3.getId(),student3);
        students.put(student4.getId(),student4);
        students.put(student5.getId(),student5);

    }


    //CRUD

    @Override
    public List<Student> getAllStudents(){
        students.forEach((key, value) -> {
            System.out.println("Clé: " + key + ", Valeur: " + value);
        });
        return students.values().stream().toList();
    }


    @Override
    public Student getAStudentById(UUID studentId){
        return students.values().stream().filter(s->s.getId().equals(studentId)).findFirst().orElse(null);
    }


    @Override
    public Student saveAStudent(Student newStudent){
        if (newStudent.getId() == null) {
            newStudent.setId(UUID.randomUUID());
            students.put(newStudent.getId(), newStudent);
        }
        return null;
    }


    @Override
    public void deleteAStudent(UUID studentId){
        if (studentId != null ){
            students.remove(studentId);
        }

        //removeIf


    }


    @Override
    public Student getASttudentByHisName(String studentName){
        return students.values().stream().filter(student -> student.getNom().equals(studentName)).findFirst().orElse(null);
    }


    @Override
    public void updateAStudent(UUID id, Student student) {
        Student studentToUpdate = getAStudentById(id);
        if (studentToUpdate != null){
            studentToUpdate.setPrenom(student.getPrenom());
            studentToUpdate.setNom(student.getNom());
            studentToUpdate.setEmail(student.getEmail());
            studentToUpdate.setAge(student.getAge());

            students.put(id, studentToUpdate);

        }
    }



    @Override
    public List<Student> searchStudents(String search) {
        return students.values().stream().filter(student -> student.getPrenom().toLowerCase().contains(search.toLowerCase()) || student.getNom().toLowerCase().contains(search.toLowerCase())).toList();
    }

}
