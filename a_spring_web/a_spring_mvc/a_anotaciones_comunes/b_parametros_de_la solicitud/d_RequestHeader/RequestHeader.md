# Documentación sobre `@RequestHeader` en Spring Boot

`@RequestHeader` es una anotación en Spring que se utiliza para capturar valores de los encabezados HTTP en las solicitudes. Es útil para obtener información adicional sobre la solicitud, como tokens de autenticación o información del cliente.

## 1. Introducción

`@RequestHeader` permite a los desarrolladores acceder a los encabezados de las solicitudes HTTP y usarlos como parámetros en los métodos del controlador.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> obtenerUsuario(@RequestHeader("Authorization") String token) {
        Usuario usuario = usuarioService.validarToken(token);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuario")`: Define la ruta que manejará las solicitudes GET.
- `@RequestHeader("Authorization") String token`: Captura el valor del encabezado `Authorization` y lo pasa como argumento al método.

## 3. Parámetros Opcionales

Puedes definir encabezados opcionales y proporcionar un valor predeterminado.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuario(@RequestHeader(value = "X-Custom-Header", defaultValue = "default") String customHeader) {
    // Lógica para manejar el encabezado
    return new ResponseEntity<>(usuarioService.encontrarPorId(1L), HttpStatus.OK);
}
```

## 4. Múltiples Encabezados

Puedes capturar múltiples encabezados en un solo método.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuario(
    @RequestHeader("Authorization") String token,
    @RequestHeader("User-Agent") String userAgent) {
    // Lógica para manejar los encabezados
    return new ResponseEntity<>(usuarioService.validarToken(token), HttpStatus.OK);
}
```

## 5. Uso en Métodos POST

`@RequestHeader` también se puede utilizar en métodos que manejan solicitudes POST.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@RequestHeader("Authorization") String token, @RequestBody Usuario usuario) {
    // Lógica para crear un nuevo usuario
    return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
}
```

## 6. Conclusión

`@RequestHeader` es una herramienta útil en Spring Boot para acceder a los encabezados de las solicitudes HTTP, permitiendo a los desarrolladores implementar características como autenticación y personalización de respuestas.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestHeader.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
