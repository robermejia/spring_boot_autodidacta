# Documentación sobre `Repository` en Spring Boot

El patrón `Repository` en Spring Data JPA es una parte fundamental para la implementación de la capa de acceso a datos. Proporciona una forma de encapsular la lógica necesaria para acceder a los datos de la base de datos y permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de manera sencilla.

## 1. Introducción

El patrón `Repository` se basa en la idea de que las operaciones de acceso a datos deben estar separadas de la lógica de negocio. Spring Data JPA proporciona una interfaz `Repository` que puede ser extendida para crear repositorios personalizados.

## 2. Interfaces de Repository

### 2.1. `CrudRepository`

La interfaz `CrudRepository` proporciona métodos para realizar operaciones CRUD básicas. Se utiliza como base para crear repositorios.

```java
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {
    // Aquí se pueden definir métodos personalizados si es necesario
}
```

### 2.2. `JpaRepository`

La interfaz `JpaRepository` extiende `CrudRepository` y proporciona métodos adicionales para trabajar con JPA, como paginación y ordenación.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Métodos personalizados
    List<Libro> findByTitulo(String titulo);
}
```

## 3. Uso de Repository

### 3.1. Definición de la Entidad

Supongamos que tenemos una entidad `Libro`.

```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;

    // Getters y Setters
}
```

### 3.2. Creación del Repositorio

A continuación, creamos un repositorio para la entidad `Libro`.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTitulo(String titulo);
}
```

### 3.3. Uso del Repositorio en un Servicio

El repositorio se puede inyectar en un servicio para realizar operaciones sobre la base de datos.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public void guardarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
```

## 4. Métodos Personalizados

Spring Data JPA permite definir métodos personalizados basados en convenciones de nomenclatura. Por ejemplo:

- `findByTitulo(String titulo)`: Busca libros por su título.
- `deleteById(Long id)`: Elimina un libro por su ID (ya proporcionado por `CrudRepository`).

## 5. Consideraciones

- **Transacciones**: Las operaciones de acceso a datos deben estar dentro de un contexto transaccional. Se puede usar `@Transactional` en los métodos del servicio.
- **Manejo de Excepciones**: Es importante manejar excepciones adecuadamente al acceder a los datos, para evitar problemas en la aplicación.

## 6. Conclusión

El patrón `Repository` en Spring Data JPA simplifica el acceso a datos y permite realizar operaciones CRUD de manera eficiente. Al separar la lógica de acceso a datos de la lógica de negocio, se mejora la mantenibilidad y escalabilidad de la aplicación.

## Referencias

- [Documentación oficial de Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Data JPA Reference Guide](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories)
