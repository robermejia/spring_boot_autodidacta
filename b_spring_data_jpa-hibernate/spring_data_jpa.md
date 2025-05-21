# 📚 Spring Data JPA - Definición y Anotaciones

## 🔹 ¿Qué es Spring Data JPA?

**Spring Data JPA** es un módulo de Spring que facilita el acceso a bases de datos usando **JPA (Java Persistence API)**. Proporciona una abstracción sobre JPA simplificando la implementación de repositorios, consultas y operaciones CRUD sin necesidad de escribir código SQL o JPQL manual.

> 🚀 Spring Data JPA automatiza las operaciones con bases de datos usando interfaces y anotaciones, permitiendo crear consultas mediante nombres de métodos o con anotaciones como `@Query`.

---

## ✅ Anotaciones Comunes en Spring Data JPA

### 🏷️ Anotaciones de Mapeo de Entidades

| Anotación        | Descripción                                                                 |
|------------------|-----------------------------------------------------------------------------|
| `@Entity`        | Marca una clase como una entidad JPA.                                       |
| `@Table`         | Especifica el nombre de la tabla en la base de datos.                      |
| `@Id`            | Marca el campo como clave primaria.                                         |
| `@GeneratedValue`| Indica que el valor del campo se generará automáticamente.                 |
| `@Column`        | Configura detalles de la columna (nombre, longitud, nulabilidad, etc.).    |
| `@Transient`     | Marca un campo que no debe persistirse en la base de datos.                |
| `@Lob`           | Para campos grandes (texto o binarios).                                     |
| `@Temporal`      | Define el tipo temporal para fechas (`DATE`, `TIME`, `TIMESTAMP`).         |
| `@Enumerated`    | Permite mapear enumeraciones.                                               |

---

### 🔗 Relaciones entre Entidades

| Anotación         | Descripción                                                                   |
|-------------------|-------------------------------------------------------------------------------|
| `@OneToOne`        | Relación uno a uno.                                                           |
| `@OneToMany`       | Relación uno a muchos.                                                        |
| `@ManyToOne`       | Relación muchos a uno.                                                        |
| `@ManyToMany`      | Relación muchos a muchos.                                                     |
| `@JoinColumn`      | Especifica la columna que une dos entidades (clave foránea).                 |
| `@JoinTable`       | Define una tabla intermedia para relaciones muchos a muchos.                 |
| `@MappedBy`        | Indica el campo propietario en relaciones bidireccionales.                   |
| `@Cascade`         | Controla operaciones en cascada (se usa con `javax.persistence.CascadeType`).|

---

### 📦 Repositorios y Consultas

| Anotación    | Descripción                                                                 |
|--------------|-----------------------------------------------------------------------------|
| `@Repository`| Marca una interfaz/clase como componente de acceso a datos.                 |
| `@Query`     | Define una consulta JPQL personalizada.                                     |
| `@Modifying` | Se usa junto con `@Query` para indicar que la consulta modifica datos.      |
| `@Param`     | Asocia parámetros con los nombres en la consulta JPQL.                     |
| `@EnableJpaRepositories` | Activa los repositorios JPA en un paquete específico.            |

---

### 🛠️ Otras Utilidades

| Anotación             | Descripción                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `@PersistenceContext` | Inyecta una instancia de `EntityManager`.                                  |
| `@EntityListeners`    | Asocia listeners para eventos del ciclo de vida de la entidad.             |
| `@PrePersist`         | Ejecuta un método antes de persistir la entidad.                           |
| `@PostPersist`        | Ejecuta un método después de persistir la entidad.                         |
| `@PreUpdate`          | Antes de actualizar.                                                        |
| `@PostUpdate`         | Después de actualizar.                                                      |
| `@PreRemove`          | Antes de eliminar.                                                          |
| `@PostRemove`         | Después de eliminar.                                                        |
| `@Access`             | Define cómo JPA accede a los campos (por propiedad o por campo directamente).|

