# Documentación sobre `@PostMapping` en Spring Boot

`@PostMapping` es una anotación en Spring que se utiliza para manejar solicitudes HTTP POST. Proporciona una forma más clara y concisa de definir métodos que procesan datos enviados desde el cliente.

## 1. Introducción

`@PostMapping` se utiliza en los controladores para manejar las solicitudes POST, permitiendo a los desarrolladores recibir y procesar datos enviados en el cuerpo de la solicitud.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @PostMapping("/crearUsuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@PostMapping("/crearUsuario")`: Define la ruta que manejará las solicitudes POST en esta clase.

## 3. Cuerpo de la Solicitud

Puedes recibir datos en el cuerpo de la solicitud utilizando `@RequestBody`.

### Ejemplo

```java
@PostMapping("/actualizarUsuario")
public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
    Usuario usuarioActualizado = usuarioService.actualizar(usuario);
    return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
}
```

## 4. Respuestas Personalizadas

`@PostMapping` permite devolver respuestas personalizadas, incluyendo códigos de estado HTTP.

### Ejemplo

```java
@PostMapping("/eliminarUsuario")
public ResponseEntity<Void> eliminarUsuario(@RequestParam Long id) {
    usuarioService.eliminar(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

## 5. Consumo y Producción

Puedes especificar los tipos de contenido que tu método puede consumir o producir.

### Ejemplo

```java
@PostMapping(value = "/crear", consumes = "application/json", produces = "application/json")
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardar(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
}
```

## 6. Otros Atributos

- **headers**: Filtra las solicitudes por encabezados.
- **params**: Filtra las solicitudes por parámetros de consulta.

### Ejemplo

```java
@PostMapping(value = "/filtrar", params = "tipo=admin")
public List<Usuario> filtrarAdmin(@RequestBody List<Usuario> usuarios) {
    return usuarioService.filtrarPorTipo(usuarios, "admin");
}
```

## 7. Conclusión

`@PostMapping` es una herramienta esencial en Spring Boot para manejar solicitudes POST, facilitando la recepción y procesamiento de datos enviados desde el cliente.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
