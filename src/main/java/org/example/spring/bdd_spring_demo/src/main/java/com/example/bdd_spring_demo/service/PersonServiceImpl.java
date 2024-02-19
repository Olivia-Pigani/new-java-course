package com.example.bdd_spring_demo.service;

import com.example.bdd_spring_demo.dao.PersonRepository;
import com.example.bdd_spring_demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> finsAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(long id) {
        return personRepository.getReferenceById(id);
    }

    @Override
    public void save(Person person) {
    personRepository.save(person);
    }

    @Override
    public void update(Long id, Person updatedPerson) {
        String personfN = updatedPerson.getFirstName();
        String personlN = updatedPerson.getLastName();
        String personEmail = updatedPerson.getEmail();
        personRepository.updatePerson(id,personfN,personlN,personEmail);
    }
}
