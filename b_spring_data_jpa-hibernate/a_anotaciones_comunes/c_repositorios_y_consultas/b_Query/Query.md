# Documentación sobre `@Query` en Spring Boot

La anotación `@Query` en Spring Data JPA permite definir consultas personalizadas en los repositorios. Esta anotación es útil cuando las consultas generadas automáticamente no son suficientes o cuando se desea optimizar el rendimiento de las operaciones de acceso a datos.

## 1. Introducción

`@Query` permite escribir consultas JPQL (Java Persistence Query Language) o SQL nativo directamente en los métodos del repositorio. Esto proporciona flexibilidad para definir consultas complejas y optimizadas.

## 2. Uso Básico de `@Query`

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

### Definición del Repositorio con `@Query`

A continuación, creamos un repositorio para la entidad `Libro` y utilizamos `@Query` para definir una consulta personalizada.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor") // Consulta JPQL
    List<Libro> findByAutor(@Param("autor") String autor);

    @Query(value = "SELECT * FROM libro WHERE titulo = ?1", nativeQuery = true) // Consulta SQL nativa
    List<Libro> findByTituloNative(String titulo);
}
```

### Desglose

- **Consulta JPQL**: La primera consulta utiliza JPQL para buscar libros por autor. Se usa `@Param` para vincular el parámetro de entrada.
  
- **Consulta SQL Nativa**: La segunda consulta utiliza SQL nativo para buscar libros por título. Se especifica `nativeQuery = true` para indicar que se está usando SQL en lugar de JPQL.

## 3. Parámetros en Consultas

### 3.1. Parámetros Posicionales

Se pueden usar parámetros posicionales en las consultas utilizando el símbolo `?`. Por ejemplo:

```java
@Query("SELECT l FROM Libro l WHERE l.titulo = ?1")
List<Libro> findByTitulo(String titulo);
```

### 3.2. Parámetros Nombrados

Los parámetros nombrados se definen con `@Param`. Por ejemplo:

```java
@Query("SELECT l FROM Libro l WHERE l.autor = :autor")
List<Libro> findByAutor(@Param("autor") String autor);
```

## 4. Otras Opciones de `@Query`

### 4.1. Consultas con `JOIN`

Se pueden realizar consultas que involucren relaciones entre entidades:

```java
@Query("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre = :nombreAutor")
List<Libro> findLibrosByNombreAutor(@Param("nombreAutor") String nombreAutor);
```

### 4.2. Consultas con Paginación y Ordenación

Se pueden combinar consultas con paginación y ordenación:

```java
@Query("SELECT l FROM Libro l ORDER BY l.titulo")
Page<Libro> findAllLibros(Pageable pageable);
```

## 5. Consideraciones

- **Mantenimiento**: Las consultas personalizadas pueden ser más difíciles de mantener que las generadas automáticamente. Es importante documentar bien las consultas complejas.
- **Rendimiento**: Optimizar las consultas puede ser crucial para el rendimiento de la aplicación. Utilizar índices y revisar el plan de ejecución de las consultas puede ayudar.

## 6. Conclusión

La anotación `@Query` en Spring Data JPA proporciona una forma poderosa y flexible de definir consultas personalizadas en los repositorios. Permite optimizar el acceso a datos y realizar operaciones complejas de manera eficiente.

## Referencias

- [Documentación oficial de Spring Data JPA - @Query](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
