# Documentación sobre `@Modifying` en Spring Boot

La anotación `@Modifying` en Spring Data JPA se utiliza junto con `@Query` para indicar que una consulta modifica el estado de la base de datos. Esto es especialmente útil para operaciones de actualización y eliminación, ya que permite ejecutar consultas que no devuelven resultados, sino que afectan directamente los datos.

## 1. Introducción

Cuando se realizan operaciones que modifican datos (como `INSERT`, `UPDATE` o `DELETE`), es importante que Spring Data JPA sepa que se está llevando a cabo una operación de modificación. La anotación `@Modifying` se utiliza para marcar estos métodos en el repositorio.

## 2. Uso Básico de `@Modifying`

### Ejemplo de Clase de Entidad

Supongamos que tenemos una entidad `Libro`.

```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private String autor;

    // Getters y Setters
}
```

### Definición del Repositorio con `@Modifying`

A continuación, creamos un repositorio para la entidad `Libro` y utilizamos `@Modifying` junto con `@Query` para definir una operación de actualización.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Modifying
    @Transactional // Necesario para operaciones que modifican la base de datos
    @Query("UPDATE Libro l SET l.titulo = :nuevoTitulo WHERE l.id = :id")
    int actualizarTitulo(@Param("id") Long id, @Param("nuevoTitulo") String nuevoTitulo);

    @Modifying
    @Transactional
    @Query("DELETE FROM Libro l WHERE l.autor = :autor")
    void eliminarPorAutor(@Param("autor") String autor);
}
```

### Desglose

- **Actualizar Título**:
  - `@Modifying`: Indica que esta consulta modifica el estado de la base de datos.
  - `@Transactional`: Necesaria para asegurar que la operación se ejecute dentro de una transacción.
  - La consulta actualiza el título de un libro basado en su ID.

- **Eliminar por Autor**:
  - También utiliza `@Modifying` para indicar que se eliminarán registros de la base de datos en función del autor.

## 3. Consideraciones sobre `@Modifying`

### 3.1. Transacciones

- **Uso de `@Transactional`**: Es importante incluir `@Transactional` en los métodos que tienen anotaciones `@Modifying` para garantizar que la operación se ejecute correctamente y se pueda revertir en caso de error.

### 3.2. Retorno de Resultados

- Los métodos anotados con `@Modifying` pueden devolver un entero que representa el número de filas afectadas por la operación. Esto es útil para verificar si la operación se realizó correctamente.

## 4. Ejemplo Completo de Uso

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public void actualizarTituloLibro(Long id, String nuevoTitulo) {
        libroRepository.actualizarTitulo(id, nuevoTitulo);
    }

    @Transactional
    public void eliminarLibrosPorAutor(String autor) {
        libroRepository.eliminarPorAutor(autor);
    }
}
```

### Desglose

En este ejemplo, el servicio `LibroService` utiliza el repositorio para actualizar el título de un libro y eliminar libros por autor, asegurando que ambas operaciones se realicen dentro de transacciones.

## 5. Conclusión

La anotación `@Modifying` es esencial para realizar operaciones de modificación en Spring Data JPA. Permite ejecutar consultas que cambian el estado de la base de datos de manera clara y efectiva, asegurando que se manejen correctamente las transacciones.

## Referencias

- [Documentación oficial de Spring Data JPA - @Modifying](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
