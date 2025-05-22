# Documentación sobre `@Enumerated` en Spring Boot

`@Enumerated` es una anotación de JPA (Java Persistence API) que se utiliza para especificar cómo se debe mapear un campo de tipo enumerado (enum) en una entidad a una columna en la base de datos. Esta anotación permite definir cómo se almacenarán los valores del enum, ya sea como enteros o como cadenas de texto.

## 1. Introducción

Los enums son útiles para representar un conjunto fijo de constantes en Java. Al utilizar `@Enumerated`, los desarrolladores pueden controlar cómo se almacenan estos valores en la base de datos, lo que facilita la gestión y la legibilidad del código.

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Enumerated`

```java
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Usuario {

    public enum Rol {
        ADMIN,
        USER,
        GUEST
    }

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // Getters y Setters
}
```

### Desglose

- `@Entity`: Marca la clase `Usuario` como una entidad JPA.
- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@Enumerated(EnumType.STRING)`: Indica que el campo `rol` debe ser almacenado como una cadena de texto en la base de datos.

## 3. Tipos de `EnumType`

La anotación `@Enumerated` acepta dos tipos de `EnumType`:

### 3.1. STRING

- Almacena el nombre del enum como una cadena de texto en la base de datos.

```java
@Enumerated(EnumType.STRING)
```

### 3.2. ORDINAL

- Almacena el índice del enum (su posición en la declaración) como un entero. Por ejemplo, `ADMIN` sería 0, `USER` sería 1, y `GUEST` sería 2.

```java
@Enumerated(EnumType.ORDINAL)
```

## 4. Ejemplo de Uso

### Ejemplo de Clase con Enum y Diferentes Tipos

```java
@Entity
public class Producto {

    public enum Categoria {
        ELECTRONICA,
        ROPA,
        ALIMENTOS
    }

    @Id
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    // Getters y Setters
}
```

### Desglose

En este ejemplo, `categoria` se almacena como un número entero en la base de datos, representando su posición en la enumeración.

## 5. Consideraciones

- **Compatibilidad**: Al usar `EnumType.ORDINAL`, si se reordenan los valores del enum, puede haber problemas de compatibilidad con datos existentes en la base de datos. Es recomendable usar `EnumType.STRING` para evitar estos problemas.
- **Legibilidad**: Almacenar enums como cadenas de texto mejora la legibilidad de los datos en la base de datos, lo que facilita la depuración y el mantenimiento.

## 6. Conclusión

La anotación `@Enumerated` es una herramienta valiosa en JPA para manejar tipos enumerados en entidades. Permite a los desarrolladores definir cómo se deben almacenar estos valores en la base de datos, asegurando un manejo adecuado y eficiente de los enums.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Enumerated.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
