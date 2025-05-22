# Documentación sobre `@Temporal` en Spring Boot

`@Temporal` es una anotación de JPA (Java Persistence API) que se utiliza para especificar cómo se debe mapear un campo de tipo `Date`, `Time` o `Timestamp` en una entidad a una columna en la base de datos. Esta anotación permite definir el tipo de información temporal que se desea almacenar.

## 1. Introducción

La anotación `@Temporal` es esencial para indicar a JPA cómo debe tratar las fechas y horas, ya que el manejo de estos tipos de datos puede variar según el proveedor de la base de datos y el tipo de columna utilizado.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Temporal`

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Evento {

    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaEvento;

    @Temporal(TemporalType.TIME)
    private Date horaEvento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    // Getters y Setters
}
```

### Desglose

- `@Entity`: Marca la clase `Evento` como una entidad JPA.
- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@Temporal(TemporalType.DATE)`: Especifica que `fechaEvento` solo almacenará la fecha (sin hora).
- `@Temporal(TemporalType.TIME)`: Indica que `horaEvento` almacenará solo la hora (sin fecha).
- `@Temporal(TemporalType.TIMESTAMP)`: Define que `fechaHoraCreacion` almacenará tanto la fecha como la hora.

## 3. Tipos de `TemporalType`

La anotación `@Temporal` acepta tres tipos de `TemporalType`:

### 3.1. DATE

- Almacena solo la parte de la fecha (año, mes, día).

```java
@Temporal(TemporalType.DATE)
```

### 3.2. TIME

- Almacena solo la parte de la hora (hora, minuto, segundo).

```java
@Temporal(TemporalType.TIME)
```

### 3.3. TIMESTAMP

- Almacena tanto la fecha como la hora, incluyendo la información de zona horaria.

```java
@Temporal(TemporalType.TIMESTAMP)
```

## 4. Ejemplo de Uso

### Ejemplo de Clase con Diferentes Tipos Temporales

```java
@Entity
public class Tarea {

    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    // Getters y Setters
}
```

### Desglose

En este ejemplo, `fechaLimite` almacena solo la fecha, mientras que `fechaCreacion` almacena tanto la fecha como la hora.

## 5. Conclusión

La anotación `@Temporal` es fundamental para el manejo adecuado de tipos de datos temporales en JPA. Permite a los desarrolladores especificar cómo se deben almacenar las fechas y horas en la base de datos, asegurando que la información temporal se maneje de manera efectiva y precisa.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Temporal.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
