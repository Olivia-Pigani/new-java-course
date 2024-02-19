package com.example.bdd_spring_demo.service;

import com.example.bdd_spring_demo.entity.Person;

import java.util.List;

public interface PersonService {




    List<Person> finsAll();

    Person findPersonById(long id);

    void save(Person person);

    void update(Long id, Person updatedPerson);














}
