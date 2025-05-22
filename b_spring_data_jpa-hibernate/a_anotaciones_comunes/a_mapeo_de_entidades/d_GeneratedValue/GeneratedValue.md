# Documentación sobre `@GeneratedValue` en Spring Boot

`@GeneratedValue` es una anotación de JPA (Java Persistence API) que se utiliza para especificar la estrategia de generación de valores para una clave primaria en una entidad. Esta anotación es comúnmente utilizada junto con `@Id` para definir cómo se deben generar los identificadores únicos de las instancias de la entidad.

## 1. Introducción

La generación automática de valores para claves primarias es esencial para asegurar que cada registro en la base de datos tenga un identificador único. `@GeneratedValue` permite a los desarrolladores definir cómo se generarán estos identificadores, facilitando la gestión de la persistencia de datos.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@GeneratedValue`

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

- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica que el valor del campo `id` se generará automáticamente por la base de datos.

## 3. Estrategias de Generación

Existen varias estrategias que se pueden utilizar con `@GeneratedValue`, cada una adecuada para diferentes contextos:

### 3.1. AUTO

- JPA elige la estrategia de generación más adecuada según el proveedor de la base de datos.

```java
@GeneratedValue(strategy = GenerationType.AUTO)
```

### 3.2. IDENTITY

- La base de datos genera el valor de la clave primaria. Esta estrategia es común en bases de datos que soportan columnas de identidad, como MySQL.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

### 3.3. SEQUENCE

- Utiliza una secuencia de la base de datos para generar el valor. Esta estrategia es común en bases de datos como Oracle.

```java
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
```

- Además, se puede definir un generador de secuencias:

```java
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_sequence", allocationSize = 1)
```

### 3.4. TABLE

- Utiliza una tabla para generar el valor de la clave primaria. Esta estrategia es menos común y se utiliza principalmente para compatibilidad con bases de datos que no soportan secuencias.

```java
@GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_table")
```

## 4. Conclusión

La anotación `@GeneratedValue` es crucial en JPA para la gestión de claves primarias. Permite a los desarrolladores definir cómo se generan los identificadores únicos, asegurando la integridad y la eficiencia en la persistencia de datos.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/GeneratedValue.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
