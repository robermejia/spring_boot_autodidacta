# Documentación sobre `@EntityListeners` en JPA

La anotación `@EntityListeners` en JPA se utiliza para definir una clase (o clases) que serán notificadas sobre eventos relacionados con la entidad, como la creación, actualización y eliminación. Esta funcionalidad permite ejecutar lógica adicional cuando se producen estos eventos en las entidades.

## 1. Introducción

`@EntityListeners` es útil para implementar lógica de negocio que debe ejecutarse automáticamente en respuesta a eventos de ciclo de vida de la entidad. Esto puede incluir tareas como la auditoría, la validación o el establecimiento de valores predeterminados.

## 2. Uso Básico de `@EntityListeners`

### Ejemplo de Clase de Entidad

Supongamos que tenemos una entidad `Libro` que queremos auditar cada vez que se crea o actualiza.

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
    private LocalDateTime fechaActualizacion;

    // Getters y Setters
}
```

### 3. Definición de la Clase de Listener

A continuación, definimos la clase `LibroListener` que manejará los eventos de ciclo de vida de la entidad `Libro`.

```java
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class LibroListener {

    @PrePersist
    public void antesDePersistir(Libro libro) {
        libro.setFechaCreacion(LocalDateTime.now());
    }

    @PreUpdate
    public void antesDeActualizar(Libro libro) {
        libro.setFechaActualizacion(LocalDateTime.now());
    }
}
```

### Desglose

- **`@PrePersist`**: Este método se ejecuta antes de que la entidad `Libro` sea persistida. Aquí, se establece la fecha de creación.
- **`@PreUpdate`**: Este método se ejecuta antes de que la entidad sea actualizada, estableciendo la fecha de actualización.

## 4. Otros Eventos de Ciclo de Vida

Además de `@PrePersist` y `@PreUpdate`, hay otros eventos que se pueden manejar:

- **`@PostPersist`**: Se ejecuta después de que la entidad ha sido persistida.
- **`@PostUpdate`**: Se ejecuta después de que la entidad ha sido actualizada.
- **`@PreRemove`**: Se ejecuta antes de que la entidad sea eliminada.
- **`@PostRemove`**: Se ejecuta después de que la entidad ha sido eliminada.

### Ejemplo de Uso de Otros Eventos

```java
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public class LibroListener {

    @PrePersist
    public void antesDePersistir(Libro libro) {
        libro.setFechaCreacion(LocalDateTime.now());
    }

    @PostPersist
    public void despuesDePersistir(Libro libro) {
        System.out.println("Libro creado: " + libro.getTitulo());
    }

    @PreRemove
    public void antesDeEliminar(Libro libro) {
        System.out.println("Eliminando libro: " + libro.getTitulo());
    }
}
```

## 5. Consideraciones

- **Separación de Responsabilidades**: Utilizar `@EntityListeners` ayuda a mantener la lógica de negocio separada de la lógica de persistencia, mejorando la mantenibilidad del código.
- **Rendimiento**: Ten en cuenta que la lógica adicional en los listeners puede afectar el rendimiento, especialmente si ejecuta operaciones costosas.

## 6. Conclusión

La anotación `@EntityListeners` es una herramienta poderosa en JPA para manejar eventos de ciclo de vida de las entidades. Facilita la implementación de lógica adicional y mejora la organización del código, permitiendo una mejor separación de responsabilidades.

## Referencias

- [Documentación oficial de JPA - @EntityListeners](https://docs.oracle.com/javaee/7/api/javax/persistence/EntityListeners.html)
- [Java Persistence API (JPA) Specification](https://download.oracle.com/otn-pub/jcp/jpa-2_2-fr-spec/jpa_2_2_fr.pdf)
