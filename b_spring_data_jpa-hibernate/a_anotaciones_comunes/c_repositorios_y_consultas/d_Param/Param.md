# Documentación sobre `@Param` en Spring Boot

La anotación `@Param` en Spring Data JPA se utiliza para vincular parámetros en las consultas definidas con `@Query`. Permite pasar argumentos a las consultas de manera clara y legible, facilitando la construcción de métodos en los repositorios.

## 1. Introducción

`@Param` se utiliza junto con métodos en repositorios que tienen consultas personalizadas. Esta anotación permite que los parámetros de la consulta se vinculen con los argumentos del método, lo que mejora la legibilidad y el mantenimiento del código.

## 2. Uso Básico de `@Param`

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

### Definición del Repositorio con `@Param`

A continuación, creamos un repositorio para la entidad `Libro` y utilizamos `@Param` en las consultas personalizadas.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findByAutor(@Param("autor") String autor);

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    List<Libro> findByTitulo(@Param("titulo") String titulo);
}
```

### Desglose

- **Consulta por Autor**:
  - `@Query("SELECT l FROM Libro l WHERE l.autor = :autor")`: Aquí, `:autor` es un parámetro nombrado.
  - `@Param("autor")`: Vincula el argumento del método `findByAutor` al parámetro de la consulta.

- **Consulta por Título**:
  - Similar a la consulta anterior, se utiliza un parámetro nombrado `:titulo` vinculado con `@Param`.

## 3. Parámetros Posicionales vs. Nombrados

### 3.1. Parámetros Posicionales

Se pueden utilizar parámetros posicionales en las consultas utilizando el símbolo `?`. Por ejemplo:

```java
@Query("SELECT l FROM Libro l WHERE l.titulo = ?1")
List<Libro> findByTitulo(String titulo);
```

En este caso, `?1` se refiere al primer argumento del método.

### 3.2. Parámetros Nombrados

Los parámetros nombrados son más legibles y se utilizan con `@Param`. Son útiles cuando se tienen múltiples parámetros, ya que permiten identificar claramente cada uno.

## 4. Ejemplo Completo de Uso

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerLibrosPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
}
```

### Desglose

En este ejemplo, el servicio `LibroService` utiliza el repositorio para buscar libros por autor y título, utilizando `@Param` para vincular los parámetros de entrada a las consultas.

## 5. Consideraciones

- **Legibilidad**: Usar `@Param` mejora la legibilidad del código, especialmente en consultas complejas con múltiples parámetros.
- **Mantenimiento**: Facilita el mantenimiento del código, ya que los parámetros se pueden identificar claramente.

## 6. Conclusión

La anotación `@Param` es una herramienta fundamental en Spring Data JPA para vincular parámetros en consultas personalizadas. Mejora la legibilidad y el mantenimiento del código, permitiendo construir métodos de repositorio más claros y eficientes.

## Referencias

- [Documentación oficial de Spring Data JPA - @Param](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
