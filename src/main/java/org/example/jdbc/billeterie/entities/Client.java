package org.example.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private List<Evenement> listeBillets = new ArrayList<>();

    public Client(int id,String nom, String prenom, String email) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public void acheterBillet (Evenement event){
        if(event.vendreBillet()){
            listeBillets.add(event);
            System.out.println("le billet pour l'evenement "+event+" a bien ete acheter");
        }
        else{
            System.out.println("impossible d'acheter un billet");
        }
    }

    public void annulerBillet (Evenement event){
        if(event.annulerBillet()){
           this.listeBillets = this.listeBillets.stream().filter(e -> e.getNom() != event.getNom()).collect(Collectors.toList());
            System.out.println("le billet pour l'évenement "+event+" à bien ete annulé");
        }
    }



}
