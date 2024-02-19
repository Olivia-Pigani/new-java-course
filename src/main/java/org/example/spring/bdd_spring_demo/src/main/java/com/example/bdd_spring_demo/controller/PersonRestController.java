package com.example.bdd_spring_demo.controller;


import com.example.bdd_spring_demo.entity.Person;
import com.example.bdd_spring_demo.service.PersonService;
import com.example.bdd_spring_demo.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonRestController {


    private PersonServiceImpl personService;

    @Autowired
    public PersonRestController(PersonServiceImpl personService) {
        this.personService=personService;
    }

    @GetMapping("/list")
    public List<Person> getAllPerson(){
    return personService.finsAll();
}

@GetMapping("/addoneperson")
    public void addOnePerson(){
    personService.save(Person.builder().firstName("toto").lastName("otto").email("qdqdqz@email.com").build());

}

@PostMapping("/addperson")
    public void addPerson(){
        personService.save(Person.builder().firstName("totopost").lastName("otto").email("qdqdqz@email.com").build());
}


@PostMapping("/updateaperson/{personId}")
    public void updateAPerson(@PathVariable("personId") Long personId, @RequestBody Person personUpdated){
        Person personToUpdate = personService.findPersonById(personId);
        if (personToUpdate != null){
            personToUpdate.setFirstName(personUpdated.getFirstName());
            personToUpdate.setLastName(personUpdated.getLastName());
            personToUpdate.setEmail(personUpdated.getEmail());
            personService.update(personId, personUpdated );

        }
}




}
