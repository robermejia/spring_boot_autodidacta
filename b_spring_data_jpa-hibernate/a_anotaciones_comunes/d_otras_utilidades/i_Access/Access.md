# Documentación de @Access en Spring Boot

## Índice

1. [Introducción](#introducción)
2. [¿Qué es @Access?](#qué-es-access)
3. [Ubicación y dependencias](#ubicación-y-dependencias)
4. [Sintaxis básica](#sintaxis-básica)
5. [Atributos y configuración](#atributos-y-configuración)
6. [Casos de uso principales](#casos-de-uso-principales)
7. [Ejemplos prácticos](#ejemplos-prácticos)
8. [Integración con Spring Security](#integración-con-spring-security)
9. [Buenas prácticas](#buenas-prácticas)
10. [Solución de problemas comunes](#solución-de-problemas-comunes)

## Introducción

La anotación `@Access` en Spring Boot es una funcionalidad fundamental de JPA (Java Persistence API) que permite controlar cómo Hibernate accede a los campos y propiedades de una entidad. Esta anotación define la estrategia de acceso que el proveedor de persistencia utilizará para leer y escribir datos en los objetos de entidad.

## ¿Qué es @Access?

`@Access` es una anotación de JPA que especifica el tipo de acceso utilizado por el proveedor de persistencia para acceder a las propiedades de una entidad. Determina si Hibernate debe usar acceso directo a campos (field access) o acceso a través de métodos getter/setter (property access).

### Tipos de acceso disponibles

- **FIELD**: Acceso directo a los campos
- **PROPERTY**: Acceso a través de métodos getter y setter

## Ubicación y dependencias

### Dependencias necesarias

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Import requerido

```java
import javax.persistence.Access;
import javax.persistence.AccessType;
```

**Nota**: En versiones más recientes de Spring Boot (3.x+), usar:
```java
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
```

## Sintaxis básica

### Declaración de la anotación

```java
@Access(AccessType.FIELD)
public class MiEntidad {
    // implementación
}
```

### Valores posibles

- `AccessType.FIELD`
- `AccessType.PROPERTY`

## Atributos y configuración

### Atributo value

El único atributo de `@Access` es `value`, que acepta un valor del enum `AccessType`.

```java
@Access(value = AccessType.FIELD)
// o simplemente
@Access(AccessType.FIELD)
```

### Niveles de aplicación

La anotación `@Access` puede aplicarse en:

1. **Nivel de clase**: Define el acceso por defecto para toda la entidad
2. **Nivel de campo**: Sobrescribe el acceso para un campo específico
3. **Nivel de propiedad**: Sobrescribe el acceso para una propiedad específica

## Casos de uso principales

### 1. Control de acceso a nivel de entidad

Define cómo Hibernate accederá a todos los campos de la entidad por defecto.

### 2. Acceso mixto

Permite combinar diferentes tipos de acceso dentro de la misma entidad.

### 3. Optimización de rendimiento

Puede mejorar el rendimiento al evitar llamadas innecesarias a métodos getter/setter.

### 4. Compatibilidad con herencia

Facilita el manejo de jerarquías de entidades con diferentes estrategias de acceso.

## Ejemplos prácticos

### Ejemplo 1: Acceso por campo (FIELD)

```java
@Entity
@Access(AccessType.FIELD)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email;
    
    // Los getters y setters son opcionales para la persistencia
    // pero recomendados para el uso en la aplicación
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

### Ejemplo 2: Acceso por propiedad (PROPERTY)

```java
@Entity
@Access(AccessType.PROPERTY)
public class Producto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
        this.nombre = nombre != null ? nombre.trim() : null; 
    }
    
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { 
        if (precio != null && precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio; 
    }
}
```

### Ejemplo 3: Acceso mixto

```java
@Entity
@Access(AccessType.FIELD)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero;
    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    
    // Este campo usa acceso por propiedad para validación
    @Access(AccessType.PROPERTY)
    private String estado;
    
    // Getters y setters normales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    // Getter y setter con lógica especial para estado
    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        List<String> estadosValidos = Arrays.asList("PENDIENTE", "PROCESANDO", "ENVIADO", "ENTREGADO");
        if (!estadosValidos.contains(estado)) {
            throw new IllegalArgumentException("Estado no válido: " + estado);
        }
        this.estado = estado;
    }
    
    // Otros getters y setters...
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
```

### Ejemplo 4: Con campos calculados

```java
@Entity
@Access(AccessType.FIELD)
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private BigDecimal salarioBase;
    private BigDecimal bonificacion;
    
    // Campo calculado que no se persiste
    @Transient
    @Access(AccessType.PROPERTY)
    private BigDecimal salarioTotal;
    
    // Getters normales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Getter calculado para salario total
    public BigDecimal getSalarioTotal() {
        if (salarioBase == null) return BigDecimal.ZERO;
        BigDecimal bono = bonificacion != null ? bonificacion : BigDecimal.ZERO;
        return salarioBase.add(bono);
    }
    
    // Setter vacío para salario total (no debe modificarse directamente)
    public void setSalarioTotal(BigDecimal salarioTotal) {
        // No hacer nada - es un campo calculado
    }
    
    // Otros getters y setters...
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public BigDecimal getSalarioBase() { return salarioBase; }
    public void setSalarioBase(BigDecimal salarioBase) { this.salarioBase = salarioBase; }
    
    public BigDecimal getBonificacion() { return bonificacion; }
    public void setBonificacion(BigDecimal bonificacion) { this.bonificacion = bonificacion; }
}
```

## Integración con Spring Security

### Ejemplo con auditoría

```java
@Entity
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class DocumentoSeguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String contenido;
    
    @CreatedBy
    private String creadoPor;
    
    @LastModifiedBy
    private String modificadoPor;
    
    @CreatedDate
    private LocalDateTime fechaCreacion;
    
    @LastModifiedDate
    private LocalDateTime fechaModificacion;
    
    // Campo con acceso especial para logging de seguridad
    @Access(AccessType.PROPERTY)
    private String nivelAcceso;
    
    public String getNivelAcceso() { return nivelAcceso; }
    
    public void setNivelAcceso(String nivelAcceso) {
        // Log de cambio de nivel de acceso
        if (this.nivelAcceso != null && !this.nivelAcceso.equals(nivelAcceso)) {
            log.info("Cambio de nivel de acceso para documento {}: {} -> {}", 
                    id, this.nivelAcceso, nivelAcceso);
        }
        this.nivelAcceso = nivelAcceso;
    }
    
    // Getters y setters restantes...
}
```

## Buenas prácticas

### 1. Consistencia en el proyecto

```java
// Definir una estrategia consistente para todo el proyecto
@Entity
@Access(AccessType.FIELD) // Preferible para la mayoría de casos
public class EntidadBase {
    // implementación base
}
```

### 2. Uso de acceso por propiedad para validación

```java
@Entity
@Access(AccessType.FIELD)
public class Cliente {
    private String email;
    
    @Access(AccessType.PROPERTY)
    private String telefono;
    
    // Validación en el setter
    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("Formato de teléfono inválido");
        }
        this.telefono = telefono;
    }
    
    public String getTelefono() { return telefono; }
}
```

### 3. Documentación clara

```java
@Entity
@Access(AccessType.FIELD)
public class Configuracion {
    /**
     * Usa acceso por propiedad para permitir validación
     * y transformación de datos antes de la persistencia
     */
    @Access(AccessType.PROPERTY)
    private String valorConfiguracion;
    
    public String getValorConfiguracion() {
        return valorConfiguracion;
    }
    
    public void setValorConfiguracion(String valor) {
        // Validación y transformación
        this.valorConfiguracion = valor != null ? valor.toUpperCase() : null;
    }
}
```

### 4. Manejo de herencia

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Access(AccessType.FIELD)
public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected String marca;
    protected String modelo;
}

@Entity
@Access(AccessType.FIELD) // Mantener consistencia
public class Automovil extends Vehiculo {
    private Integer numeroPuertas;
    
    // Acceso especial para campo específico
    @Access(AccessType.PROPERTY)
    private String tipoTransmision;
    
    public String getTipoTransmision() { return tipoTransmision; }
    
    public void setTipoTransmision(String tipo) {
        List<String> tiposValidos = Arrays.asList("MANUAL", "AUTOMATICA", "CVT");
        if (!tiposValidos.contains(tipo)) {
            throw new IllegalArgumentException("Tipo de transmisión no válido");
        }
        this.tipoTransmision = tipo;
    }
}
```

## Solución de problemas comunes

### Problema 1: Campos no inicializados

**Síntoma**: Los campos aparecen como null después de cargar desde la base de datos.

**Solución**:
```java
@Entity
@Access(AccessType.FIELD)
public class Solucion1 {
    @Id
    private Long id;
    
    // Asegurar que las anotaciones JPA estén en los campos
    @Column(name = "nombre")
    private String nombre;
    
    // No en los getters cuando se usa FIELD access
    public String getNombre() { return nombre; }
}
```

### Problema 2: Validaciones no ejecutándose

**Síntoma**: Las validaciones en setters no se ejecutan durante la carga de datos.

**Solución**:
```java
@Entity
@Access(AccessType.FIELD)
public class Solucion2 {
    private String email;
    
    // Usar PROPERTY access para campos que requieren validación
    @Access(AccessType.PROPERTY)
    private String estado;
    
    public String getEstado() { return estado; }
    
    public void setEstado(String estado) {
        // Esta validación se ejecutará
        if (estado != null && !Arrays.asList("ACTIVO", "INACTIVO").contains(estado)) {
            throw new IllegalArgumentException("Estado inválido");
        }
        this.estado = estado;
    }
}
```

### Problema 3: Rendimiento con PROPERTY access

**Síntoma**: Lentitud al cargar entidades con muchos campos.

**Solución**:
```java
@Entity
@Access(AccessType.FIELD) // Más eficiente para carga masiva
public class Solucion3 {
    private String campo1;
    private String campo2;
    private String campo3;
    
    // Solo usar PROPERTY donde sea necesario
    @Access(AccessType.PROPERTY)
    private String campoConValidacion;
    
    public String getCampoConValidacion() { return campoConValidacion; }
    
    public void setCampoConValidacion(String valor) {
        // Lógica especial solo donde se necesita
        this.campoConValidacion = valor;
    }
}
```

### Problema 4: Anotaciones en lugares incorrectos

**Síntoma**: Anotaciones JPA no funcionan correctamente.

**Solución**:
```java
// INCORRECTO - Mezclando ubicaciones de anotaciones
@Entity
@Access(AccessType.FIELD)
public class Incorrecto {
    private Long id;
    private String nombre;
    
    @Id // ❌ Anotación en getter con FIELD access
    public Long getId() { return id; }
}

// CORRECTO - Anotaciones consistentes con el tipo de acceso
@Entity
@Access(AccessType.FIELD)
public class Correcto {
    @Id // ✅ Anotación en campo con FIELD access
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre_completo")
    private String nombre;
    
    // Getters sin anotaciones
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
```

### Configuración de logging para debugging

```yaml
# application.yml
logging:
  level:
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.orm.jpa: DEBUG
```

Esta configuración ayuda a identificar problemas relacionados con el acceso a campos y propiedades durante el desarrollo.