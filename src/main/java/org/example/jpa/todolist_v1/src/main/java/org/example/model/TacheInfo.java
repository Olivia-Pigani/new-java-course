package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "tache_infos")
public class TacheInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String description;

    private Date dateEcheance;

    private int priority;


    @OneToOne(mappedBy = "tacheInfo", cascade = CascadeType.ALL)
    private Task task;


}
