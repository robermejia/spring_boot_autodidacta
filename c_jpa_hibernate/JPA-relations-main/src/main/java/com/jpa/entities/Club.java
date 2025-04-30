package com.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// LOMBOX
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// HIBERNATE => Indica que es una entidad persistente
@Entity
public class Club {

    // HIBERNATE => LLave primaría
    @Id
    // HIBERNATE => Especificar valore de llava primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // HIBERNATE => Relación de 1 a 1 con la tabla relacionada
    @OneToOne(targetEntity = Coach.class, cascade = CascadeType.PERSIST)
    private Coach coach;

    // HIBERNATE => Relación de 1 a n con la tabla relacionada
    @OneToMany(targetEntity = Player.class, fetch = FetchType.LAZY)
    private List<Player> players;

    // HIBERNATE => Relación de n a 1 con la tabla relacionada
    @ManyToOne(targetEntity = FootballAssociation.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FootballAssociation footballAssociation;

    // HIBERNATE => Relación de n a n con la tabla relacionada
    @ManyToMany(targetEntity = FootballCompetition.class, fetch = FetchType.LAZY)

    // HIBERNATE => Relación de n a n, sino Hibernate crea una tabla intermedia
    @JoinTable(name = "club_competition", joinColumns = @JoinColumn(name = "club"), inverseJoinColumns = @JoinColumn(name = "competition"))
    private List<FootballCompetition> footballCompetitions;
}
