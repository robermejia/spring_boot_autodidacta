# Documentación sobre `@Transient` en Spring Boot

`@Transient` es una anotación de JPA (Java Persistence API) que se utiliza para indicar que un atributo de una entidad no debe ser persistido en la base de datos. Esto significa que, aunque el atributo puede ser parte de la lógica de la aplicación, no se almacenará en la tabla correspondiente.

## 1. Introducción

La anotación `@Transient` es útil en situaciones donde se necesita un campo en la entidad para cálculos, lógica de negocio o almacenamiento temporal, pero no se desea que ese campo sea parte del modelo de datos persistente. 

## 2. Uso Básico

### Ejemplo de Clase de Entidad con `@Transient`

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Usuario {

    @Id
    private Long id;
    private String nombre;
    private String email;

    @Transient
    private String tokenAutenticacion;

    // Getters y Setters
}
```

### Desglose

- `@Entity`: Marca la clase `Usuario` como una entidad JPA.
- `@Id`: Indica que el campo `id` es la clave primaria de la entidad.
- `@Transient`: Indica que el campo `tokenAutenticacion` no debe ser persistido en la base de datos.

## 3. Propósitos Comunes de `@Transient`

Hay varias razones para utilizar `@Transient` en una entidad:

### 3.1. Cálculos Temporales

Se pueden usar atributos transitorios para almacenar resultados de cálculos que no necesitan ser persistidos.

### 3.2. Datos Derivados

Atributos que son derivados de otros campos y no necesitan ser almacenados, como el nombre completo de un usuario (combinando nombre y apellido).

### 3.3. Información de Sesión

Almacenar datos temporales que son relevantes solo durante la sesión del usuario, como un token de autenticación.

## 4. Ejemplo de Uso

### Ejemplo de Clase con Cálculo Transitorio

```java
@Entity
public class Producto {

    @Id
    private Long id;
    private double precio;
    private int cantidad;

    @Transient
    private double precioTotal;

    public double getPrecioTotal() {
        return precio * cantidad;
    }

    // Getters y Setters
}
```

### Desglose

En este ejemplo, `precioTotal` es un atributo transitorio que calcula el precio total basado en el precio y la cantidad, pero no se almacena en la base de datos.

## 5. Conclusión

La anotación `@Transient` es una herramienta útil en JPA para definir atributos que no deben ser persistidos en la base de datos. Permite a los desarrolladores manejar datos temporales y cálculos sin interferir con el modelo de datos persistente.

## Referencias

- [Documentación oficial de JPA](https://docs.oracle.com/javaee/7/api/javax/persistence/Transient.html)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
