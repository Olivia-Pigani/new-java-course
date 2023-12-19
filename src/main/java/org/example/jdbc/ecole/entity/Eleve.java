package org.example.jdbc.ecole.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Eleve {



    private int id;
    private String firstName;
    private String lastName;
    private int classNumber;
    private Date dateDegree;


    public Eleve(int id, String firstName, String lastName, int classNumber, Date dateDegree) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classNumber = classNumber;
        this.dateDegree = dateDegree;
    }




}
