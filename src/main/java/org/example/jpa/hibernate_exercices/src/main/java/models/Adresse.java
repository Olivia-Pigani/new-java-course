package models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "adresse")
@Data
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rue;

    private String ville;

    private int codePostal;

    @OneToOne
    private Commande commande;






}
