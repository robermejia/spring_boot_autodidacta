# Documentación sobre `@Table` en Spring Boot

`@Table` es una anotación de JPA (Java Persistence API) que se utiliza para especificar la tabla en la base de datos a la que se debe mapear una entidad. Permite personalizar el nombre de la tabla y sus propiedades.

## 1. Introducción

Cuando se utiliza la anotación `@Entity`, JPA asume que el nombre de la tabla es el mismo que el de la clase. Sin embargo, si deseas que la entidad mapee a una tabla con un nombre diferente o si necesitas especificar otras configuraciones, puedes usar `@Table`.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Table`

```java
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Getters y Setters
}
```

### Desglose

- `@Entity`: Marca la clase `Usuario` como una entidad JPA.
- `@Table(name = "usuarios")`: Especifica que la entidad `Usuario` se debe mapear a la tabla `usuarios` en la base de datos.

## 3. Propiedades Adicionales de `@Table`

La anotación `@Table` también permite definir propiedades adicionales que pueden ser útiles en ciertos contextos.

### Ejemplo con Propiedades Adicionales

```java
@Table(
    name = "usuarios",
    schema = "public",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
```

### Desglose de Propiedades

- `schema`: Especifica el esquema de la base de datos al que pertenece la tabla.
- `uniqueConstraints`: Permite definir restricciones de unicidad en columnas específicas, en este caso, el campo `email` debe ser único.

## 4. Uso de `@Table` en Relaciones

Cuando se trabaja con relaciones entre entidades, `@Table` también puede ser utilizada para definir la tabla de una entidad relacionada.

### Ejemplo de Relación con `@Table`

```java
@Entity
@Table(name = "roles")
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

## 5. Conclusión

La anotación `@Table` es una herramienta poderosa en JPA que permite personalizar el mapeo de entidades a tablas en la base de datos. Facilita la gestión de la estructura de la base de datos y asegura que las entidades se comporten de acuerdo a las necesidades específicas de la aplicación.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Table.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
