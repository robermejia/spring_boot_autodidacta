# Documentación sobre `mappedBy` en Spring Boot

`mappedBy` es un atributo utilizado en las anotaciones de JPA (Java Persistence API) para definir el lado inverso de una relación bidireccional entre dos entidades. Indica que una entidad no es responsable de la relación y que el otro lado de la relación es el que maneja la clave foránea.

## 1. Introducción

Cuando se establece una relación entre dos entidades (por ejemplo, uno a uno, uno a muchos o muchos a muchos), es posible que desee que una de las entidades controle la relación. `mappedBy` se utiliza para especificar qué propiedad en la otra entidad es responsable de la relación.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `mappedBy`

#### Relación Uno a Muchos

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {

    @Id
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor") // Indica que la propiedad 'autor' en 'Libro' es la que controla la relación
    private List<Libro> libros;

    // Getters y Setters
}
```

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;

    @ManyToOne
    private Autor autor; // Controla la relación

    // Getters y Setters
}
```

### Desglose

- **Autor**:
  - `@OneToMany(mappedBy = "autor")`: Indica que la relación es bidireccional y que la propiedad `autor` en la entidad `Libro` es la que maneja la clave foránea.

- **Libro**:
  - `@ManyToOne`: Indica que cada `Libro` está asociado a un único `Autor`.

## 3. Propiedades de `mappedBy`

### 3.1. Control de Relaciones

- **Lado No Propietario**: `mappedBy` se utiliza en el lado no propietario de la relación. Esto significa que no se debe establecer la clave foránea en esta entidad; en su lugar, se gestiona en la otra entidad.

### 3.2. Bidireccionalidad

- **Relaciones Bidireccionales**: `mappedBy` es esencial para establecer relaciones bidireccionales, donde cada lado de la relación puede acceder a los datos del otro lado.

## 4. Ejemplo Completo de Uso

```java
@Entity
public class Autor {

    @Id
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    // Getters y Setters
}

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;

    @ManyToOne
    private Autor autor; // Este es el lado propietario de la relación

    // Getters y Setters
}
```

### Desglose

En este ejemplo, `Autor` tiene una relación uno a muchos con `Libro`, y `mappedBy` indica que el campo `autor` en `Libro` es el que controla la relación.

## 5. Consideraciones

- **Integridad Referencial**: Asegúrese de que las relaciones definidas con `mappedBy` mantengan la integridad referencial en la base de datos.
- **Carga de Entidades**: La estrategia de carga (eager o lazy) puede influir en cómo se cargan las entidades y su rendimiento.

## 6. Conclusión

La propiedad `mappedBy` es fundamental para definir relaciones bidireccionales en JPA. Permite especificar qué lado de la relación es el propietario, mejorando la claridad y la estructura del modelo de datos.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/OneToMany.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
