package com.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// LOMBOX
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// HIBERNATE => Indica que es una entidad persistente
@Entity
public class FootballAssociation {

    // HIBERNATE => LLave primaría
    @Id
    // HIBERNATE => Especificar valore de llava primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String president;
}
