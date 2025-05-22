# Documentación sobre `@CookieValue` en Spring Boot

`@CookieValue` es una anotación en Spring que se utiliza para capturar valores de las cookies en las solicitudes HTTP. Es útil para manejar información de sesión y preferencias del usuario.

## 1. Introducción

`@CookieValue` permite a los desarrolladores acceder a los valores de las cookies enviadas con la solicitud y usarlos como parámetros en los métodos del controlador.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> obtenerUsuario(@CookieValue("sessionId") String sessionId) {
        Usuario usuario = usuarioService.validarSesion(sessionId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuario")`: Define la ruta que manejará las solicitudes GET.
- `@CookieValue("sessionId") String sessionId`: Captura el valor de la cookie `sessionId` y lo pasa como argumento al método.

## 3. Parámetros Opcionales

Puedes definir cookies opcionales y proporcionar un valor predeterminado.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuario(@CookieValue(value = "userId", defaultValue = "0") String userId) {
    // Lógica para manejar el valor de la cookie
    return new ResponseEntity<>(usuarioService.encontrarPorId(Long.parseLong(userId)), HttpStatus.OK);
}
```

## 4. Múltiples Cookies

Puedes capturar múltiples cookies en un solo método.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuario(
    @CookieValue("sessionId") String sessionId,
    @CookieValue("userId") String userId) {
    // Lógica para manejar las cookies
    return new ResponseEntity<>(usuarioService.validarSesion(sessionId), HttpStatus.OK);
}
```

## 5. Uso en Métodos POST

`@CookieValue` también se puede utilizar en métodos que manejan solicitudes POST.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@CookieValue("sessionId") String sessionId, @RequestBody Usuario usuario) {
    // Lógica para crear un nuevo usuario
    return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
}
```

## 6. Conclusión

`@CookieValue` es una herramienta útil en Spring Boot para acceder a los valores de las cookies en las solicitudes HTTP, permitiendo a los desarrolladores gestionar la sesión del usuario y personalizar la experiencia.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CookieValue.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
