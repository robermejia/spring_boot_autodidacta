# Documentación Completa de @PreUpdate en Spring Boot

## Índice

- [Introducción](#introducción)
- [¿Qué es @PreUpdate?](#qué-es-preupdate)
- [Configuración y Dependencias](#configuración-y-dependencias)
- [Sintaxis y Uso Básico](#sintaxis-y-uso-básico)
- [Ejemplos Prácticos](#ejemplos-prácticos)
- [Casos de Uso Comunes](#casos-de-uso-comunes)
- [Diferencias con Otros Callbacks](#diferencias-con-otros-callbacks)
- [Mejores Prácticas](#mejores-prácticas)
- [Limitaciones y Consideraciones](#limitaciones-y-consideraciones)
- [Ejemplos Avanzados](#ejemplos-avanzados)
- [Troubleshooting](#troubleshooting)

## Introducción

La anotación `@PreUpdate` forma parte del conjunto de callbacks del ciclo de vida de entidades en JPA (Java Persistence API) y es ampliamente utilizada en aplicaciones Spring Boot que trabajan con Spring Data JPA. Esta anotación permite ejecutar lógica personalizada antes de que una entidad sea actualizada en la base de datos.

## ¿Qué es @PreUpdate?

`@PreUpdate` es una anotación de callback de JPA que marca un método para ser ejecutado automáticamente antes de que se realice una operación de actualización (`UPDATE`) en la base de datos. Este callback se dispara justo antes de que la entidad sea persistida con sus cambios.

### Características principales

- **Timing**: Se ejecuta antes de la operación UPDATE en la base de datos
- **Automático**: Se invoca automáticamente por el proveedor JPA
- **Contexto**: Tiene acceso al estado actual de la entidad
- **Transaccional**: Se ejecuta dentro del contexto transaccional

## Configuración y Dependencias

Para utilizar `@PreUpdate` en Spring Boot, necesitas las siguientes dependencias:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Imports necesarios

```java
import javax.persistence.PreUpdate;
// o para Jakarta EE
import jakarta.persistence.PreUpdate;
```

## Sintaxis y Uso Básico

### Sintaxis básica

```java
@Entity
public class MiEntidad {
    
    @PreUpdate
    public void antesDeActualizar() {
        // Lógica a ejecutar antes de la actualización
    }
}
```

### Reglas de sintaxis

- El método anotado con `@PreUpdate` debe ser `void`
- No debe recibir parámetros
- Puede ser público, protegido o privado
- No debe ser estático
- No debe ser final

## Ejemplos Prácticos

### Ejemplo 1: Actualización de timestamp

```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PreUpdate
    public void actualizarFechaModificacion() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // Getters y setters...
}
```

### Ejemplo 2: Validación antes de actualizar

```java
@Entity
public class Producto {
    
    @Id
    private Long id;
    
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    
    @PreUpdate
    public void validarAntesDeActualizar() {
        if (precio != null && precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        
        if (stock != null && stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
    
    // Getters y setters...
}
```

### Ejemplo 3: Auditoría con información del usuario

```java
@Entity
public class Documento {
    
    @Id
    private Long id;
    
    private String titulo;
    private String contenido;
    
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    
    @PreUpdate
    public void configurarAuditoria() {
        this.fechaModificacion = LocalDateTime.now();
        
        // Obtener usuario actual del contexto de seguridad
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            this.usuarioModificacion = auth.getName();
        }
    }
    
    // Getters y setters...
}
```

## Casos de Uso Comunes

### 1. Auditoría automática

Registrar automáticamente quién y cuándo modificó una entidad:

```java
@MappedSuperclass
public abstract class EntidadAuditable {
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    
    @PreUpdate
    protected void configurarAuditoriaActualizacion() {
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioModificacion = obtenerUsuarioActual();
    }
    
    private String obtenerUsuarioActual() {
        // Lógica para obtener el usuario actual
        return "sistema"; // Simplificado
    }
}
```

### 2. Validaciones complejas

```java
@Entity
public class Pedido {
    
    @Id
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    
    private BigDecimal total;
    
    @PreUpdate
    public void validarCambioDeEstado() {
        // Validar transiciones de estado válidas
        if (estado == EstadoPedido.CANCELADO && total.compareTo(BigDecimal.ZERO) > 0) {
            // Lógica para manejar reembolsos
            procesarReembolso();
        }
    }
    
    private void procesarReembolso() {
        // Lógica de reembolso
    }
}
```

### 3. Normalización de datos

```java
@Entity
public class Cliente {
    
    @Id
    private Long id;
    
    private String email;
    private String telefono;
    private String nombre;
    
    @PreUpdate
    public void normalizarDatos() {
        if (email != null) {
            this.email = email.toLowerCase().trim();
        }
        
        if (telefono != null) {
            this.telefono = telefono.replaceAll("[^0-9]", "");
        }
        
        if (nombre != null) {
            this.nombre = WordUtils.capitalizeFully(nombre.trim());
        }
    }
}
```

## Diferencias con Otros Callbacks

### Comparación con @PrePersist

| Aspecto | @PreUpdate | @PrePersist |
|---------|------------|-------------|
| **Cuándo se ejecuta** | Antes de UPDATE | Antes de INSERT |
| **Contexto** | Entidad existente | Nueva entidad |
| **ID disponible** | Siempre | Generalmente no |

### Comparación con @PostUpdate

| Aspecto | @PreUpdate | @PostUpdate |
|---------|------------|-------------|
| **Timing** | Antes de UPDATE | Después de UPDATE |
| **Modificación** | Puede modificar entidad | No debe modificar entidad |
| **Rollback** | Puede causar rollback | No puede prevenir la operación |

## Mejores Prácticas

### 1. Manejo de excepciones

```java
@Entity
public class Transaccion {
    
    @PreUpdate
    public void validarTransaccion() {
        try {
            // Validaciones
            validarMonto();
            validarCuenta();
        } catch (Exception e) {
            // Log del error
            log.error("Error en validación pre-update: {}", e.getMessage());
            throw new RuntimeException("Validación fallida", e);
        }
    }
}
```

### 2. Separación de responsabilidades

```java
@Entity
public class Empleado {
    
    @PreUpdate
    public void ejecutarCallbacksPreUpdate() {
        actualizarTimestamp();
        validarDatos();
        calcularCamposDerivados();
    }
    
    private void actualizarTimestamp() {
        this.fechaModificacion = LocalDateTime.now();
    }
    
    private void validarDatos() {
        if (salario != null && salario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salario inválido");
        }
    }
    
    private void calcularCamposDerivados() {
        // Cálculos automáticos
    }
}
```

### 3. Uso de servicios mediante ApplicationContext

```java
@Entity
public class Factura {
    
    @PreUpdate
    public void procesarAntesDeActualizar() {
        if (ApplicationContextProvider.getContext() != null) {
            FacturaService facturaService = ApplicationContextProvider
                .getContext().getBean(FacturaService.class);
            facturaService.validarFactura(this);
        }
    }
}

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    
    private static ApplicationContext context;
    
    public static ApplicationContext getContext() {
        return context;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
}
```

## Limitaciones y Consideraciones

### 1. Rendimiento

Los callbacks `@PreUpdate` se ejecutan en cada actualización, lo que puede impactar el rendimiento:

```java
@Entity
public class LogEntry {
    
    @PreUpdate
    public void optimizarCallback() {
        // Evitar operaciones costosas
        if (debeEjecutarValidacionCompleta()) {
            validacionCompleta();
        } else {
            validacionRapida();
        }
    }
    
    private boolean debeEjecutarValidacionCompleta() {
        // Lógica para determinar si es necesaria validación completa
        return camposImportantesCambiaron();
    }
}
```

### 2. Acceso a EntityManager

Dentro de `@PreUpdate` no se debe usar EntityManager para operaciones de persistencia:

```java
@Entity
public class NotificacionConfig {
    
    // ❌ INCORRECTO
    @PreUpdate
    public void callbackIncorrecto() {
        // No hacer esto - puede causar problemas
        // entityManager.persist(nuevaEntidad);
    }
    
    // ✅ CORRECTO
    @PreUpdate
    public void callbackCorrecto() {
        // Solo modificar la entidad actual
        this.fechaModificacion = LocalDateTime.now();
        // Marcar para procesamiento posterior
        this.requiereProcesamiento = true;
    }
}
```

### 3. Transaccionalidad

Los callbacks se ejecutan dentro de la transacción principal:

```java
@Entity
public class ContabilidadEntry {
    
    @PreUpdate
    public void manejarTransaccion() {
        try {
            // Operaciones que pueden fallar
            validarIntegridadContable();
        } catch (Exception e) {
            // El fallo aquí causará rollback de toda la transacción
            throw new RuntimeException("Validación contable fallida", e);
        }
    }
}
```

## Ejemplos Avanzados

### Ejemplo 1: Implementación con Spring Events

```java
@Entity
public class Inventario {
    
    private Integer stockAnterior;
    
    @PreUpdate
    public void prepararEventoStock() {
        // Guardar valor anterior para comparación
        this.stockAnterior = this.stock;
    }
    
    @PostUpdate
    public void publicarEventoStock() {
        if (stockAnterior != null && !stockAnterior.equals(this.stock)) {
            StockChangeEvent event = new StockChangeEvent(
                this.id, stockAnterior, this.stock
            );
            
            ApplicationContextProvider.getContext()
                .publishEvent(event);
        }
    }
}

@Component
@EventListener
public class StockEventHandler {
    
    public void handleStockChange(StockChangeEvent event) {
        // Manejar cambio de stock
        log.info("Stock cambió de {} a {} para producto {}", 
                event.getStockAnterior(), 
                event.getStockNuevo(), 
                event.getProductoId());
    }
}
```

### Ejemplo 2: Versionado automático

```java
@Entity
public class DocumentoVersionado {
    
    @Id
    private Long id;
    
    private String contenido;
    
    @Version
    private Long version;
    
    @Column(name = "hash_contenido")
    private String hashContenido;
    
    @PreUpdate
    public void calcularHashVersion() {
        if (contenido != null) {
            this.hashContenido = DigestUtils.sha256Hex(contenido);
        }
    }
    
    @PreUpdate
    public void validarCambiosSimultaneos() {
        // Validación adicional de concurrencia
        if (version == null) {
            throw new OptimisticLockException(
                "Versión no disponible para validación"
            );
        }
    }
}
```

## Troubleshooting

### Problema 1: Callback no se ejecuta

**Síntomas**: El método anotado con `@PreUpdate` no se ejecuta.

**Soluciones**:

```java
// ✅ Verificar que la entidad esté correctamente anotada
@Entity
public class MiEntidad {
    
    // ✅ Método debe ser no-estático
    @PreUpdate
    public void miCallback() {
        // ...
    }
    
    // ❌ No funcionará
    @PreUpdate
    public static void callbackEstatico() {
        // ...
    }
}
```

### Problema 2: Excepciones en callbacks

**Síntomas**: Las excepciones en callbacks causan rollback completo.

**Solución**:

```java
@Entity
public class EntidadConManejadorErrores {
    
    @PreUpdate
    public void callbackConManejoErrores() {
        try {
            operacionQuePuedeFallar();
        } catch (NonCriticalException e) {
            // Log pero no propagar
            log.warn("Operación no crítica falló: {}", e.getMessage());
        } catch (CriticalException e) {
            // Propagar para causar rollback
            throw new RuntimeException("Error crítico", e);
        }
    }
}
```

### Problema 3: Rendimiento degradado

**Síntomas**: Las operaciones de actualización son lentas.

**Optimización**:

```java
@Entity
public class EntidadOptimizada {
    
    @Transient
    private boolean cambiosRelevantes = false;
    
    public void setCampoImportante(String valor) {
        if (!Objects.equals(this.campoImportante, valor)) {
            this.campoImportante = valor;
            this.cambiosRelevantes = true;
        }
    }
    
    @PreUpdate
    public void callbackOptimizado() {
        if (cambiosRelevantes) {
            // Solo ejecutar lógica costosa si hay cambios relevantes
            procesarCambiosImportantes();
        }
        
        // Siempre actualizar timestamp (operación barata)
        this.fechaModificacion = LocalDateTime.now();
    }
}
```

## Conclusión

La anotación `@PreUpdate` es una herramienta poderosa para implementar lógica automatizada antes de las actualizaciones de entidades. Su uso adecuado permite mantener la integridad de datos, implementar auditorías automáticas y ejecutar validaciones complejas de manera transparente para el código de negocio.

Las mejores prácticas incluyen mantener los callbacks simples y eficientes, manejar adecuadamente las excepciones, y considerar el impacto en el rendimiento de las operaciones ejecutadas en cada actualización.