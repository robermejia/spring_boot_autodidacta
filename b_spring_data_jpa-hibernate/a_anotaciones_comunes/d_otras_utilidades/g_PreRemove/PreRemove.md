# Documentación Completa de @PreRemove en Spring Boot

## Introducción

La anotación `@PreRemove` es parte de la especificación JPA (Java Persistence API) y se utiliza para definir métodos callback que se ejecutan automáticamente antes de que una entidad sea eliminada de la base de datos. En el contexto de Spring Boot, esta anotación es especialmente útil para realizar tareas de limpieza, logging, validaciones o cualquier lógica de negocio necesaria antes de la eliminación de una entidad.

## ¿Qué es @PreRemove?

`@PreRemove` es una anotación de callback de JPA que marca un método para ser ejecutado antes de que el EntityManager elimine una entidad de la base de datos. Esta anotación pertenece al paquete `javax.persistence` (o `jakarta.persistence` en versiones más recientes).

### Características principales:

- Se ejecuta automáticamente antes de la operación de eliminación
- Permite realizar tareas de limpieza y validación
- Es parte del ciclo de vida de las entidades JPA
- No requiere invocación manual

## Sintaxis y Uso Básico

### Estructura básica

```java
@Entity
public class MiEntidad {
    
    @PreRemove
    private void antesDeLaEliminacion() {
        // Lógica a ejecutar antes de eliminar la entidad
    }
}
```

### Ejemplo práctico

```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email;
    
    @PreRemove
    private void antesDeEliminar() {
        System.out.println("Eliminando usuario: " + this.nombre);
        // Lógica adicional de limpieza
    }
}
```

## Casos de Uso Comunes

### 1. Logging y Auditoría

```java
@Entity
public class Producto {
    
    @Id
    private Long id;
    private String nombre;
    
    @Autowired
    @Transient
    private AuditoriaService auditoriaService;
    
    @PreRemove
    private void registrarEliminacion() {
        auditoriaService.registrar("Producto eliminado: " + this.nombre);
    }
}
```

### 2. Limpieza de Archivos

```java
@Entity
public class Documento {
    
    @Id
    private Long id;
    private String rutaArchivo;
    
    @PreRemove
    private void eliminarArchivo() {
        try {
            Files.deleteIfExists(Paths.get(this.rutaArchivo));
        } catch (IOException e) {
            // Manejar excepción
        }
    }
}
```

### 3. Validaciones de Negocio

```java
@Entity
public class Pedido {
    
    @Id
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    
    @PreRemove
    private void validarEliminacion() {
        if (estado == EstadoPedido.PROCESANDO) {
            throw new IllegalStateException("No se puede eliminar un pedido en proceso");
        }
    }
}
```

## Integración con Spring Boot

### Configuración en aplicación Spring Boot

```java
@SpringBootApplication
@EntityScan(basePackages = "com.ejemplo.entidades")
@EnableJpaRepositories(basePackages = "com.ejemplo.repositorios")
public class MiAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacion.class, args);
    }
}
```

### Uso con Repositorios Spring Data JPA

```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Los métodos delete() automáticamente dispararán @PreRemove
}

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public void eliminarUsuario(Long id) {
        // El método @PreRemove se ejecutará automáticamente
        usuarioRepository.deleteById(id);
    }
}
```

## Inyección de Dependencias en @PreRemove

### Problema común

```java
@Entity
public class MiEntidad {
    
    @Autowired // ¡ESTO NO FUNCIONA!
    private MiServicio miServicio;
    
    @PreRemove
    private void callback() {
        miServicio.hacerAlgo(); // NullPointerException
    }
}
```

### Solución con ApplicationContextAware

```java
@Component
public class SpringContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
    
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}

@Entity
public class MiEntidad {
    
    @PreRemove
    private void callback() {
        MiServicio servicio = SpringContextHolder.getBean(MiServicio.class);
        servicio.hacerAlgo();
    }
}
```

### Solución alternativa con EntityListeners

```java
@Component
public class MiEntidadListener {
    
    @Autowired
    private MiServicio miServicio;
    
    @PreRemove
    public void preRemove(MiEntidad entidad) {
        miServicio.procesarEliminacion(entidad);
    }
}

@Entity
@EntityListeners(MiEntidadListener.class)
public class MiEntidad {
    // Campos de la entidad
}
```

## Buenas Prácticas

### 1. Manejo de Excepciones

```java
@Entity
public class MiEntidad {
    
    @PreRemove
    private void callback() {
        try {
            // Operaciones que pueden fallar
            realizarLimpieza();
        } catch (Exception e) {
            // Log del error pero no propagar la excepción
            // para evitar cancelar la eliminación
            log.error("Error en PreRemove", e);
        }
    }
}
```

### 2. Operaciones Ligeras

```java
@Entity
public class MiEntidad {
    
    @PreRemove
    private void callback() {
        // Mantener operaciones simples y rápidas
        actualizarCache();
        registrarEvento();
        // Evitar operaciones pesadas o de larga duración
    }
}
```

### 3. Evitar Modificaciones de Base de Datos

```java
@Entity
public class MiEntidad {
    
    @PreRemove
    private void callback() {
        // ✅ BIEN: Operaciones que no modifican la BD
        limpiarCache();
        enviarNotificacion();
        
        // ❌ MAL: Evitar modificaciones a la BD
        // entityManager.persist(nuevaEntidad);
    }
}
```

## Diferencias con Otros Callbacks

### Comparación con @PrePersist y @PreUpdate

| Callback | Cuándo se ejecuta | Uso típico |
|----------|-------------------|------------|
| `@PrePersist` | Antes de insertar | Inicialización, timestamps |
| `@PreUpdate` | Antes de actualizar | Validaciones, timestamps |
| `@PreRemove` | Antes de eliminar | Limpieza, auditoría |

### Ejemplo comparativo

```java
@Entity
public class Entidad {
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    
    @PrePersist
    private void antesDeCrear() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    @PreUpdate
    private void antesDeActualizar() {
        this.fechaModificacion = LocalDateTime.now();
    }
    
    @PreRemove
    private void antesDeEliminar() {
        // Lógica de limpieza
    }
}
```

## Limitaciones y Consideraciones

### 1. Transaccionalidad

Los métodos `@PreRemove` se ejecutan dentro de la misma transacción que la operación de eliminación. Si el método falla, la transacción completa se revierte.

### 2. No funciona con eliminaciones masivas

```java
// @PreRemove NO se ejecuta con estas operaciones:
entityManager.createQuery("DELETE FROM Usuario").executeUpdate();
usuarioRepository.deleteAll(); // En algunos casos
```

### 3. Herencia de Entidades

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {
    
    @PreRemove
    protected void callbackPadre() {
        // Se ejecuta para todas las subclases
    }
}

@Entity
public class Perro extends Animal {
    
    @PreRemove
    private void callbackHijo() {
        // Se ejecuta además del callback del padre
    }
}
```

## Configuración Avanzada

### Habilitación de callbacks JPA

```yaml
# application.yml
spring:
  jpa:
    properties:
      hibernate:
        enable_lazy_load_no_trans: false
        event:
          merge:
            entity_copy_observer: allow
```

### Configuración de listeners globales

```java
@Configuration
public class JpaConfig {
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
```

## Ejemplos Avanzados

### 1. Sistema de Auditoría Completo

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EntidadAuditable {
    
    @Id
    private Long id;
    
    @CreatedDate
    private LocalDateTime fechaCreacion;
    
    @CreatedBy
    private String creadoPor;
    
    @PreRemove
    private void registrarEliminacion() {
        AuditoriaHelper.registrarEliminacion(this);
    }
}

@Component
public class AuditoriaHelper {
    
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    
    public static void registrarEliminacion(Object entidad) {
        AuditoriaHelper bean = SpringContextHolder.getBean(AuditoriaHelper.class);
        bean.guardarRegistroEliminacion(entidad);
    }
    
    private void guardarRegistroEliminacion(Object entidad) {
        RegistroAuditoria registro = new RegistroAuditoria();
        registro.setTipoOperacion("DELETE");
        registro.setEntidad(entidad.getClass().getSimpleName());
        registro.setFecha(LocalDateTime.now());
        auditoriaRepository.save(registro);
    }
}
```

### 2. Limpieza de Relaciones

```java
@Entity
public class Usuario {
    
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    
    @PreRemove
    private void limpiarRelaciones() {
        // Limpiar relaciones bidireccionales
        if (pedidos != null) {
            pedidos.forEach(pedido -> pedido.setUsuario(null));
        }
    }
}
```

## Troubleshooting

### Problemas Comunes

#### 1. @PreRemove no se ejecuta

**Causa:** Eliminación directa con SQL nativo
```java
// ❌ No ejecuta @PreRemove
@Query("DELETE FROM Usuario u WHERE u.id = :id", nativeQuery = true)
void eliminarPorId(@Param("id") Long id);
```

**Solución:** Usar métodos del repositorio
```java
// ✅ Ejecuta @PreRemove
usuarioRepository.deleteById(id);
```

#### 2. NullPointerException en dependencias

**Causa:** Inyección de dependencias no funciona en callbacks
```java
@Entity
public class MiEntidad {
    @Autowired // No funciona
    private MiServicio servicio;
}
```

**Solución:** Usar EntityListeners o ApplicationContextAware

#### 3. Transacciones anidadas

**Problema:** Crear nuevas transacciones en @PreRemove
```java
@PreRemove
private void callback() {
    miServicio.operacionTransaccional(); // Puede causar problemas
}
```

**Solución:** Usar propagación REQUIRES_NEW si es necesario
```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void operacionIndependiente() {
    // Lógica independiente
}
```

## Testing

### Testing de callbacks @PreRemove

```java
@DataJpaTest
class PreRemoveTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Test
    void testPreRemoveCallback() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        entityManager.persistAndFlush(usuario);
        
        // When
        usuarioRepository.delete(usuario);
        entityManager.flush();
        
        // Then
        // Verificar que se ejecutó la lógica de @PreRemove
        // (depende de la implementación específica)
    }
}
```

## Conclusión

La anotación `@PreRemove` es una herramienta poderosa en Spring Boot para implementar lógica de limpieza y validación antes de eliminar entidades. Su uso adecuado permite mantener la integridad de los datos y realizar tareas de mantenimiento de manera automática y transparente.

### Puntos clave a recordar:

- Se ejecuta automáticamente antes de la eliminación
- Útil para logging, limpieza y validaciones
- Requiere cuidado especial con la inyección de dependencias
- No se ejecuta con eliminaciones masivas SQL
- Debe mantener operaciones ligeras y manejo de excepciones

La implementación correcta de `@PreRemove` contribuye significativamente a la robustez y mantenibilidad de aplicaciones Spring Boot.