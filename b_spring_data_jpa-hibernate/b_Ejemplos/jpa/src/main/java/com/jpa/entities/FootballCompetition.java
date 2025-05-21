package com.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

// LOMBOX
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// HIBERNATE => Indica que es una entidad persistente
@Entity
public class FootballCompetition {

    // HIBERNATE => LLave primaría
    @Id
    // HIBERNATE => Especificar valore de llava primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // HIBERNATE => Personaliza detalles de la tabla como: nombre, longitud, null, unico
    @Column(name = "cuantity_price")
    private int cuantityPrice;

    // HIBERNATE => Personaliza detalles de la tabla como: nombre, longitud, null, unico
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    // HIBERNATE => Personaliza detalles de la tabla como: nombre, longitud, null, unico
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    // HIBERNATE => Relación de n a n con la tabla relacionada
    @ManyToMany(targetEntity = Club.class, fetch = FetchType.LAZY)
    private List<Club> clubs;
}
