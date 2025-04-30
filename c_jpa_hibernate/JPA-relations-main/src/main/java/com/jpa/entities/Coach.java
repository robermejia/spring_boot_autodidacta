package com.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// HIBERNATE => Indica que es una entidad persistente
public class Coach {

    // HIBERNATE => LLave primaría
    @Id
    // HIBERNATE => Especificar valore de llava primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // HIBERNATE => Personaliza detalles de la tabla como: nombre, longitud, null, unico
    @Column(name = "name", length = 10, nullable = false, unique = true)
    private String name;

    // HIBERNATE => Personaliza detalles de la tabla como: nombre, tipo de dato, longitud, null, unico
    @Column(name = "last_name", columnDefinition = "VARCHAR(100)")
    private String lastName;

    private String nationality;
    private Integer age;
}
