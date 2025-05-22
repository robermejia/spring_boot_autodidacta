# Documentación sobre `@RequestBody` en Spring Boot

`@RequestBody` es una anotación en Spring que se utiliza para vincular el cuerpo de una solicitud HTTP a un objeto Java. Es particularmente útil en solicitudes POST y PUT donde se envían datos en formato JSON o XML.

## 1. Introducción

`@RequestBody` permite a los desarrolladores recibir datos complejos en el cuerpo de la solicitud y automáticamente deserializarlos en objetos Java.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@PostMapping("/usuario")`: Define la ruta que manejará las solicitudes POST.
- `@RequestBody Usuario usuario`: Captura el cuerpo de la solicitud y lo convierte en un objeto `Usuario`.

## 3. Validación de Datos

Puedes usar anotaciones de validación junto con `@RequestBody` para validar los datos entrantes.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardar(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
}
```

## 4. Manejo de Errores

Es importante manejar errores de deserialización al usar `@RequestBody`.

### Ejemplo de manejo de excepciones

```java
@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<String> manejarError(HttpMessageNotReadableException e) {
    return new ResponseEntity<>("Error en el formato de la solicitud", HttpStatus.BAD_REQUEST);
}
```

## 5. Uso en Métodos PUT

`@RequestBody` también se puede utilizar en métodos que manejan solicitudes PUT para actualizar recursos existentes.

### Ejemplo

```java
@PutMapping("/usuario/{id}")
public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
    return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
}
```

## 6. Conclusión

`@RequestBody` es una herramienta poderosa en Spring Boot para manejar datos complejos en el cuerpo de las solicitudes, permitiendo a los desarrolladores crear APIs RESTful efectivas y eficientes.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
