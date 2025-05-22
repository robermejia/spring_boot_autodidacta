# Documentación sobre `@Entity` en Spring Boot

`@Entity` es una anotación en JPA (Java Persistence API) que se utiliza para marcar una clase como una entidad, lo que significa que se puede mapear a una tabla en una base de datos. Es fundamental en la creación de aplicaciones que utilizan bases de datos relacionales.

## 1. Introducción

Las entidades representan objetos que se almacenan en la base de datos y son gestionadas por un contexto de persistencia. Cada instancia de una entidad corresponde a una fila en la tabla correspondiente en la base de datos.

## 2. Uso Básico

### Ejemplo de Clase de Entidad

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### Desglose

- `@Entity`: Marca la clase `Usuario` como una entidad JPA.
- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica que el valor del campo `id` se generará automáticamente por la base de datos.

## 3. Relacionando Entidades

Las entidades pueden tener relaciones entre sí, como uno a uno, uno a muchos y muchos a muchos.

### Ejemplo de Relación Uno a Muchos

```java
import javax.persistence.*;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    // Getters y Setters
}
```

### Desglose

- `@OneToMany(mappedBy = "rol")`: Indica que un rol puede estar asociado a múltiples usuarios.

## 4. Repositorios de Entidades

Para interactuar con las entidades, se suelen usar interfaces de repositorio que extienden `JpaRepository`.

### Ejemplo de Repositorio

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
```

## 5. Conclusión

La anotación `@Entity` es crucial en la creación de aplicaciones que utilizan JPA para la persistencia de datos en Spring Boot. Permite a los desarrolladores definir entidades que representan datos en la base de datos de manera estructurada y eficiente.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Entity.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
