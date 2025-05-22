# Documentación sobre `@Column` en Spring Boot

`@Column` es una anotación de JPA (Java Persistence API) que se utiliza para especificar los detalles de mapeo de un campo de una entidad a una columna en la base de datos. Permite personalizar el nombre, tipo, y otras propiedades de la columna que corresponde a un atributo de la entidad.

## 1. Introducción

La anotación `@Column` proporciona una forma de definir cómo se debe mapear cada atributo de la entidad a la tabla de la base de datos. Esto incluye la posibilidad de establecer restricciones, nombres de columna, y otros atributos que afectan el comportamiento de la columna en la base de datos.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Column`

```java
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombre;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // Getters y Setters
}
```

### Desglose

- `@Column(name = "nombre_usuario")`: Especifica que el atributo `nombre` se debe mapear a la columna `nombre_usuario` en la tabla.
- `nullable = false`: Indica que esta columna no puede ser nula.
- `length = 50`: Define la longitud máxima de la columna como 50 caracteres.
- `unique = true`: Establece que el valor en esta columna debe ser único en la tabla.

## 3. Propiedades Comunes de `@Column`

La anotación `@Column` admite varias propiedades que permiten un control detallado sobre el mapeo de columnas:

### 3.1. name

- Especifica el nombre de la columna en la base de datos.

```java
@Column(name = "nombre_columna")
```

### 3.2. nullable

- Indica si la columna puede contener valores nulos.

```java
@Column(nullable = false)
```

### 3.3. unique

- Define si los valores en la columna deben ser únicos.

```java
@Column(unique = true)
```

### 3.4. length

- Establece la longitud máxima de una cadena de texto en la columna.

```java
@Column(length = 100)
```

### 3.5. columnDefinition

- Permite definir el tipo de columna en la base de datos.

```java
@Column(columnDefinition = "TEXT")
```

## 4. Uso de `@Column` en Relaciones

Cuando se trabaja con relaciones entre entidades, `@Column` también puede ser utilizada para definir columnas relacionadas.

### Ejemplo de Relación

```java
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rol", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    // Getters y Setters
}
```

## 5. Conclusión

La anotación `@Column` es una herramienta poderosa en JPA que permite personalizar el mapeo de atributos de entidades a columnas en la base de datos. Proporciona flexibilidad y control sobre la estructura de la base de datos, asegurando que las entidades se comporten de acuerdo a los requisitos específicos de la aplicación.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Column.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
