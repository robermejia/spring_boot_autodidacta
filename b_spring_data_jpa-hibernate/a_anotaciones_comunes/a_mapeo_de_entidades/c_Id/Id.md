# Documentación sobre `@Id` en Spring Boot

`@Id` es una anotación de JPA (Java Persistence API) que se utiliza para marcar un campo en una entidad como la clave primaria. La clave primaria es un identificador único para cada registro en la tabla correspondiente de la base de datos.

## 1. Introducción

La clave primaria es esencial para la integridad de los datos y permite a JPA identificar de manera única cada instancia de una entidad. Sin esta anotación, JPA no podrá gestionar correctamente las operaciones de persistencia.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Id`

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
}
```

### Desglose

- `@Entity`: Marca la clase `Usuario` como una entidad JPA.
- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica que el valor del campo `id` se generará automáticamente por la base de datos.

## 3. Estrategias de Generación de Claves Primarias

Existen varias estrategias para generar claves primarias, que se pueden especificar con la anotación `@GeneratedValue`.

### Estrategias Comunes

1. **AUTO**: JPA elige la estrategia de generación más adecuada.
   ```java
   @GeneratedValue(strategy = GenerationType.AUTO)
   ```

2. **IDENTITY**: La base de datos genera el valor de la clave primaria.
   ```java
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   ```

3. **SEQUENCE**: Utiliza una secuencia de la base de datos para generar el valor.
   ```java
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
   ```

4. **TABLE**: Utiliza una tabla para generar el valor de la clave primaria.
   ```java
   @GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_table")
   ```

## 4. Claves Compuestas

Si una entidad requiere más de un campo como clave primaria, se puede utilizar una clave compuesta mediante la anotación `@IdClass` o `@EmbeddedId`.

### Ejemplo de Clave Compuesta con `@IdClass`

```java
import javax.persistence.*;

@Entity
@IdClass(UsuarioId.class)
public class Usuario {

    @Id
    private Long id;
    
    @Id
    private String email;

    private String nombre;

    // Getters y Setters
}
```

### Clase de Clave Compuesta

```java
import java.io.Serializable;

public class UsuarioId implements Serializable {
    private Long id;
    private String email;

    // Getters, Setters, equals() y hashCode()
}
```

## 5. Conclusión

La anotación `@Id` es fundamental en JPA para definir claves primarias en entidades. Permite a los desarrolladores gestionar de manera eficiente la persistencia de datos y asegurar la integridad de la base de datos.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Id.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
