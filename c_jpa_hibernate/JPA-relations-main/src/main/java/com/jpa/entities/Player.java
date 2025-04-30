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
@Entity
public class Player {

    // HIBERNATE => LLave primaría
    @Id
    // HIBERNATE => Especificar valore de llava primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    // HIBERNATE => Personaliza detalles de la tabla como: nombre, longitud, null, unico
    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    private String nationality;

    // HIBERNATE => Relación de n a 1 con la tabla relacionada
    @ManyToOne(targetEntity = Club.class)

    // HIBERNATE => Se utliza en relaciones de ,n a 1, 1 a n, 1 a 1 para espeficicar la columna de la clavé foránea.
    @JoinColumn(name = "id_club")
    private Club club;
}
