package models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
public class Commande {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int total;

    private LocalDate dateCommande;

    @ManyToMany(mappedBy = "commandeList")
    List<Produit> produitList = new ArrayList<>();

    @OneToOne(mappedBy = "commande")
    private Adresse adresseLivraison;

}
