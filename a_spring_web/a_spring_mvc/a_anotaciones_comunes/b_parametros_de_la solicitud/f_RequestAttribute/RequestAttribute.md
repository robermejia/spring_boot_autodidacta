# Documentación sobre `@RequestAttribute` en Spring Boot

`@RequestAttribute` es una anotación en Spring que se utiliza para acceder a atributos que han sido añadidos al objeto `HttpServletRequest`. Esta anotación es útil para compartir información entre diferentes componentes dentro de una misma solicitud.

## 1. Introducción

`@RequestAttribute` permite a los desarrolladores recuperar atributos que se han establecido en la solicitud, lo que facilita la comunicación entre distintos componentes durante el procesamiento de una solicitud.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> obtenerUsuario(@RequestAttribute("usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.encontrarPorId(usuarioId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuario")`: Define la ruta que manejará las solicitudes GET.
- `@RequestAttribute("usuarioId") Long usuarioId`: Captura el valor del atributo `usuarioId` y lo pasa como argumento al método.

## 3. Establecer Atributos en la Solicitud

Para que `@RequestAttribute` funcione, primero debes establecer el atributo en el `HttpServletRequest`.

### Ejemplo

```java
@GetMapping("/iniciar")
public String iniciarSesion(HttpServletRequest request) {
    request.setAttribute("usuarioId", 123L);
    return "redirect:/api/usuario";
}
```

## 4. Uso en Métodos POST

`@RequestAttribute` también se puede utilizar en métodos que manejan solicitudes POST.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@RequestAttribute("sessionId") String sessionId, @RequestBody Usuario usuario) {
    // Lógica para crear un nuevo usuario
    return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
}
```

## 5. Manejo de Atributos Opcionales

Puedes definir atributos opcionales y proporcionar un valor predeterminado utilizando un enfoque diferente, ya que `@RequestAttribute` no permite valores predeterminados directamente.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuario(@RequestAttribute(value = "usuarioId", required = false) Long usuarioId) {
    if (usuarioId == null) {
        // Manejo de la ausencia del atributo
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Usuario usuario = usuarioService.encontrarPorId(usuarioId);
    return new ResponseEntity<>(usuario, HttpStatus.OK);
}
```

## 6. Conclusión

`@RequestAttribute` es una herramienta útil en Spring Boot para acceder a atributos del objeto `HttpServletRequest`, facilitando la comunicación entre componentes en el flujo de procesamiento de solicitudes.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestAttribute.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
