# Documentación sobre `ResponseEntity` en Spring Boot

`ResponseEntity` es una clase en Spring que representa una respuesta HTTP, incluyendo el cuerpo de la respuesta, los encabezados y el código de estado. Es útil para construir respuestas más complejas y personalizadas en aplicaciones RESTful.

## 1. Introducción

`ResponseEntity` permite a los desarrolladores tener un control total sobre la respuesta HTTP que se envía al cliente, lo que facilita la gestión de diferentes aspectos de la respuesta, como el código de estado y los encabezados.

## 2. Uso Básico

### Ejemplo de Método de Controlador

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.encontrarPorId(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
```

### Desglose

- `ResponseEntity<Usuario>`: Indica que el método devolverá un objeto `ResponseEntity` que contiene un `Usuario`.
- `new ResponseEntity<>(usuario, HttpStatus.OK)`: Crea una respuesta con el cuerpo `usuario` y un código de estado 200 (OK).
- `new ResponseEntity<>(HttpStatus.NOT_FOUND)`: Crea una respuesta con un código de estado 404 (Not Found) sin cuerpo.

## 3. Personalización de Encabezados

Puedes añadir encabezados personalizados a la respuesta utilizando `ResponseEntity`.

### Ejemplo

```java
@GetMapping("/usuario/{id}")
public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
    Usuario usuario = usuarioService.encontrarPorId(id);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Custom-Header", "ValorPersonalizado");

    return new ResponseEntity<>(usuario, headers, HttpStatus.OK);
}
```

## 4. Uso en Métodos POST

`ResponseEntity` también se puede utilizar en métodos que manejan solicitudes POST para devolver respuestas personalizadas.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardar(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
}
```

## 5. Manejo de Errores

Puedes utilizar `ResponseEntity` para manejar errores de manera efectiva.

### Ejemplo de Manejo de Excepciones

```java
@ExceptionHandler(UsuarioNoEncontradoException.class)
public ResponseEntity<String> manejarError(UsuarioNoEncontradoException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
}
```

## 6. Conclusión

`ResponseEntity` es una clase versátil en Spring Boot que permite a los desarrolladores construir respuestas HTTP de manera flexible y controlada, facilitando la creación de APIs RESTful robustas y bien estructuradas.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
