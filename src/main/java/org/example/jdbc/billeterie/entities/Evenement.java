package org.example.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Evenement {

    private int id;
    private String nom;
    private Date date;
    private String heure;
    private Lieu lieu;
    private  float prix;
    private int nbrBilletVendu=0;

    public Evenement(int id,String nom, Date date, String heure, float prix){
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.heure = heure;
        this.prix = prix;

    }

    public Evenement(String nom, Date date, String heure, Lieu lieu, float prix) {
        this.nom = nom;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.prix = prix;
    }

    public boolean verifDIspoBillet (){
        return this.nbrBilletVendu < this.lieu.getCapacite();
    }

    public boolean vendreBillet (){
        if(verifDIspoBillet()){
            this.nbrBilletVendu ++;
            System.out.println("un billet a ete acheté il reste "+(this.lieu.getCapacite()-this.nbrBilletVendu)+" places disponible.");
            return true;
        }
        else {
            System.out.println("il n'y a plus de billets disponible");
            return false;
        }
    }

    public boolean annulerBillet(){
        if(this.nbrBilletVendu == 0){
            System.out.println("il n'y a eu aucun billet vendu pour cette evenement");
            return false;
        }
        else{
            this.nbrBilletVendu --;
            System.out.println("le billet a bien ete annulé");
            return true;
        }
    }


}
