package models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Transient
    private String description;

    private Statut statut;


    public Todo() {
    }

    public Todo( String title, String description, Statut statut) {
        this.title = title;
        this.description = description;
        this.statut = statut;
    }
}
