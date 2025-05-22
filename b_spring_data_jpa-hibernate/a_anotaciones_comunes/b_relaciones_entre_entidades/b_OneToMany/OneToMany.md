# Documentación sobre `@OneToMany` en Spring Boot

`@OneToMany` es una anotación de JPA (Java Persistence API) que se utiliza para definir una relación uno a muchos entre dos entidades. Esto significa que una instancia de una entidad puede estar asociada con múltiples instancias de otra entidad.

## 1. Introducción

Las relaciones uno a muchos son útiles para modelar situaciones donde un registro puede tener múltiples registros relacionados. Por ejemplo, un autor puede tener varios libros asociados a él.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@OneToMany`

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Autor {

    @Id
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor")
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
    private Autor autor;

    // Getters y Setters
}
```

### Desglose

- **Autor**:
  - `@OneToMany(mappedBy = "autor")`: Indica que la relación es bidireccional y que el campo `libros` es el lado no propietario de la relación.

- **Libro**:
  - `@ManyToOne`: Indica que cada `Libro` está asociado a un único `Autor`.

## 3. Propiedades de `@OneToMany`

### 3.1. `cascade`

Permite definir el comportamiento de las operaciones de persistencia que se deben aplicar a las entidades relacionadas. Por ejemplo:

```java
@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
private List<Libro> libros;
```

Esto significa que si se guarda, actualiza o elimina un `Autor`, también se aplicarán las mismas operaciones a sus `Libros`.

### 3.2. `fetch`

Define cómo se deben cargar las entidades relacionadas. Puede ser `FetchType.LAZY` (carga diferida) o `FetchType.EAGER` (carga inmediata).

```java
@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
private List<Libro> libros;
```

## 4. Ejemplo de Uso

### Ejemplo Completo de Relación Uno a Muchos

```java
@Entity
public class Autor {

    @Id
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    // Getters y Setters
}

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;

    @ManyToOne
    private Autor autor;

    // Getters y Setters
}
```

### Desglose

En este ejemplo, un `Autor` puede tener múltiples `Libros`, y cualquier operación de persistencia en `Autor` afectará a los `Libros` asociados debido al uso de `cascade`.

## 5. Consideraciones

- **Integridad Referencial**: Asegúrese de que las relaciones uno a muchos mantengan la integridad referencial en la base de datos.
- **Estrategia de Carga**: Elegir entre carga diferida y carga inmediata puede afectar el rendimiento de la aplicación. Evaluar cada caso de uso es recomendable.

## 6. Conclusión

La anotación `@OneToMany` es fundamental para definir relaciones uno a muchos en JPA. Permite estructurar las entidades de manera que reflejen relaciones múltiples, facilitando el diseño y la implementación de aplicaciones complejas.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/OneToMany.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
