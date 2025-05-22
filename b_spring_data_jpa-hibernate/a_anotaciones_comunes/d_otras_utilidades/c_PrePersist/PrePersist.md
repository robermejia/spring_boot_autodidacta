# Documentación sobre `@PrePersist` en JPA

La anotación `@PrePersist` es parte de la especificación de JPA (Java Persistence API) y se utiliza para definir un método que se ejecuta justo antes de que una entidad sea persistida en la base de datos. Esto permite realizar operaciones adicionales, como la inicialización de valores o la validación, antes de que se complete la operación de persistencia.

## 1. Introducción

`@PrePersist` es útil para establecer valores predeterminados, realizar auditorías o ejecutar cualquier lógica necesaria antes de que la entidad sea guardada. Se utiliza comúnmente en combinación con la anotación `@EntityListeners` para organizar la lógica de ciclo de vida de las entidades.

## 2. Uso Básico de `@PrePersist`

### Ejemplo de Clase de Entidad

A continuación, se muestra un ejemplo de cómo utilizar `@PrePersist` en una entidad `Libro`.

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Entity
@EntityListeners(LibroListener.class)
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private String autor;
    private LocalDateTime fechaCreacion;

    // Getters y Setters
}
```

### Definición de la Clase Listener

Ahora definimos la clase `LibroListener` que implementará el método que se ejecutará antes de la persistencia.

```java
import javax.persistence.PrePersist;

public class LibroListener {

    @PrePersist
    public void antesDePersistir(Libro libro) {
        libro.setFechaCreacion(LocalDateTime.now());
    }
}
```

### Desglose

- **`@PrePersist`**: Este método se ejecuta justo antes de que la entidad `Libro` sea persistida en la base de datos. Aquí, se establece la fecha de creación del libro.

## 3. Otros Eventos Relacionados

Además de `@PrePersist`, hay otros eventos que se pueden manejar en el ciclo de vida de las entidades:

- **`@PostPersist`**: Se ejecuta después de que la entidad ha sido persistida.
- **`@PreUpdate`**: Se ejecuta antes de que la entidad sea actualizada.
- **`@PostUpdate`**: Se ejecuta después de que la entidad ha sido actualizada.
- **`@PreRemove`**: Se ejecuta antes de que la entidad sea eliminada.
- **`@PostRemove`**: Se ejecuta después de que la entidad ha sido eliminada.

## 4. Ejemplo Completo de Uso

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Entity
@EntityListeners(LibroListener.class)
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private String autor;
    private LocalDateTime fechaCreacion;

    // Getters y Setters
}

import javax.persistence.PrePersist;

public class LibroListener {

    @PrePersist
    public void antesDePersistir(Libro libro) {
        libro.setFechaCreacion(LocalDateTime.now());
    }
}
```

## 5. Consideraciones

- **Transacciones**: Asegúrate de que el método que contiene `@PrePersist` esté dentro de una transacción para que se aplique correctamente.
- **Rendimiento**: La lógica dentro de `@PrePersist` debe ser eficiente, ya que se ejecuta cada vez que se persiste la entidad.

## 6. Conclusión

La anotación `@PrePersist` es una herramienta valiosa en JPA para manejar la lógica que debe ejecutarse antes de que una entidad sea persistida. Facilita la inicialización de valores y mejora la organización del código, permitiendo una mejor separación de responsabilidades.

## Referencias

- [Documentación oficial de JPA - @PrePersist](https://docs.oracle.com/javaee/7/api/javax/persistence/PrePersist.html)
- [Java Persistence API (JPA) Specification](https://download.oracle.com/otn-pub/jcp/jpa-2_2-fr-spec/jpa_2_2_fr.pdf)
