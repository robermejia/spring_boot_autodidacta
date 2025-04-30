package com.robermejia.lombok.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data: anotación combinada que genera @ToString, @EqualsAndHashCode, @Getter, @Setter y @RequiredArgsConstructor.
// Es muy conveniente, pero puede ser menos eficiente en casos de clases muy grandes.

// @Builder: Genera un patrón de diseño Builder, facilitando la creación de objetos con múltiples argumentos de forma
// legible y flexible. Requiere la importación de lombok.Builder.

// @RequiredArgsConstructor: Genera un constructor con argumentos solo para los atributos que requieren inicialización
// (los que son final o que tienen un valor inicial sin asignar).

// @ToString: Genera un método toString() que muestra los valores de los atributos de la clase de forma legible.
//  Puedes personalizar el comportamiento con el atributo includeFieldNames = true.

// @NoArgsConstructor => Crea contructor vacio
@NoArgsConstructor

// @AllArgsConstructor => Crea contructor con parametros
@AllArgsConstructor

// @Getter => Crea los Getters
@Getter

// @Setter => Crea los Setter
@Setter

// Clase POJO
public class Club {

    private int id;
    private String nombre;

}
