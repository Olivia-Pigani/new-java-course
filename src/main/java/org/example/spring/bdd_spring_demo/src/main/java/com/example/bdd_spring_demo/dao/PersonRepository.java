package com.example.bdd_spring_demo.dao;

import com.example.bdd_spring_demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Long> {


    @Modifying
    @Query("update Person p set p.firstName= :firstName, p.lastName = :lastName, p.email= :email where p.id = :id")
    void updatePerson(@Param(value = "id") long id, @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName, @Param(value = "email") String email);


}
