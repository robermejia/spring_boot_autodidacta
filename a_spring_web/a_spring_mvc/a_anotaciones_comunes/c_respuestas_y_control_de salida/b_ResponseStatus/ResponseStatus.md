# Documentación sobre `@ResponseStatus` en Spring Boot

`@ResponseStatus` es una anotación en Spring que se utiliza para indicar el código de estado HTTP que debe ser devuelto con la respuesta de un controlador. Esto es útil para personalizar las respuestas de error o éxito de manera clara y concisa.

## 1. Introducción

`@ResponseStatus` permite a los desarrolladores especificar un código de estado HTTP para un método de controlador o para una excepción lanzada. Esto facilita la gestión de respuestas HTTP sin necesidad de definir explícitamente el código de estado en cada método.

## 2. Uso Básico

### Ejemplo de Método de Controlador

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.encontrarPorId(id);
    }
}
```

### Desglose

- `@ResponseStatus(HttpStatus.OK)`: Indica que el método devolverá un código de estado 200 (OK) al completarse.

## 3. Personalización de Códigos de Estado para Errores

Puedes utilizar `@ResponseStatus` para definir códigos de estado personalizados en caso de errores.

### Ejemplo de Excepción Personalizada

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
```

### Uso en el Controlador

```java
@GetMapping("/usuario/{id}")
public Usuario obtenerUsuario(@PathVariable Long id) {
    return usuarioService.encontrarPorId(id)
        .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con ID: " + id));
}
```

## 4. Uso en Métodos POST

También puedes aplicar `@ResponseStatus` en métodos que manejan solicitudes POST.

### Ejemplo

```java
@PostMapping("/usuario")
@ResponseStatus(HttpStatus.CREATED)
public Usuario crearUsuario(@RequestBody Usuario usuario) {
    return usuarioService.guardar(usuario);
}
```

## 5. Respuestas Personalizadas

Además de los códigos de estado, puedes combinar `@ResponseStatus` con otras anotaciones para personalizar aún más las respuestas.

### Ejemplo de Mensaje de Respuesta

```java
@ExceptionHandler(UsuarioNoEncontradoException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ResponseEntity<String> manejarError(UsuarioNoEncontradoException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
}
```

## 6. Conclusión

`@ResponseStatus` es una herramienta poderosa en Spring Boot que permite a los desarrolladores gestionar de manera eficaz los códigos de estado HTTP en las respuestas, mejorando la claridad y la semántica de las APIs.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
