# Documentación sobre `@GetMapping` en Spring Boot

`@GetMapping` es una anotación específica de Spring que simplifica el manejo de solicitudes HTTP GET. Es una forma más concisa de usar `@RequestMapping` para este tipo de solicitudes.

## 1. Introducción

`@GetMapping` se utiliza en los controladores para manejar las solicitudes GET, permitiendo a los desarrolladores definir rutas específicas de manera más clara y legible.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/saludo")
    public String saludo() {
        return "¡Hola, mundo!";
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/saludo")`: Define la ruta que manejará las solicitudes GET en esta clase.

## 3. Parámetros de Consulta

Puedes utilizar parámetros de consulta en tus métodos manejados por `@GetMapping`.

### Ejemplo

```java
@GetMapping("/buscar")
public List<Usuario> buscarUsuarios(@RequestParam String nombre) {
    return usuarioService.buscarPorNombre(nombre);
}
```

## 4. Variables de Ruta

También puedes capturar variables de ruta en tus métodos.

### Ejemplo

```java
@GetMapping("/usuario/{id}")
public Usuario obtenerUsuarioPorId(@PathVariable String id) {
    return usuarioService.encontrarPorId(id);
}
```

## 5. Consumo y Producción

Aunque `@GetMapping` se utiliza principalmente para obtener datos, puedes especificar tipos de contenido que el método puede producir.

### Ejemplo

```java
@GetMapping(value = "/usuarios", produces = "application/json")
public List<Usuario> listarUsuarios() {
    return usuarioService.listarTodos();
}
```

## 6. Otros Atributos

- **headers**: Filtra las solicitudes por encabezados.
- **params**: Filtra las solicitudes por parámetros de consulta.

### Ejemplo

```java
@GetMapping(value = "/filtrar", params = "tipo=admin")
public List<Usuario> filtrarAdmin() {
    return usuarioService.obtenerAdmins();
}
```

## 7. Conclusión

`@GetMapping` es una forma conveniente y legible de manejar solicitudes GET en Spring Boot. Facilita la definición de rutas y mejora la claridad del código.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
