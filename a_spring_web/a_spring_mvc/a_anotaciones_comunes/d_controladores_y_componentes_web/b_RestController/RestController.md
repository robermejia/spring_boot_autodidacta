# Documentación sobre `@RestController` en Spring Boot

`@RestController` es una anotación en Spring que combina la funcionalidad de `@Controller` y `@ResponseBody`. Se utiliza para definir un controlador que maneja solicitudes RESTful y automáticamente serializa los objetos devueltos en formato JSON o XML.

## 1. Introducción

La anotación `@RestController` simplifica la creación de APIs REST al eliminar la necesidad de usar `@ResponseBody` en cada método. Es ideal para aplicaciones que requieren intercambios de datos ligeros y eficientes, como aplicaciones móviles o servicios web.

## 2. Uso Básico

### Ejemplo de Controlador REST

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.encontrarPorId(id);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador REST y que los métodos devolverán datos en lugar de vistas.
- `@RequestMapping("/api")`: Define la ruta base para todas las solicitudes manejadas por este controlador.
- `@GetMapping("/usuario/{id}")`: Maneja solicitudes GET a `/api/usuario/{id}` y devuelve un objeto `Usuario`.

## 3. Serialización Automática

Spring maneja automáticamente la serialización de objetos a JSON o XML dependiendo de la configuración de contenido de la solicitud.

### Ejemplo de Respuesta JSON

```java
@GetMapping("/usuarios")
public List<Usuario> obtenerUsuarios() {
    return usuarioService.listarTodos();
}
```

## 4. Manejo de Métodos POST

`@RestController` también se puede utilizar para manejar solicitudes POST y devolver respuestas adecuadas.

### Ejemplo

```java
@PostMapping("/usuario")
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardar(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
}
```

### Desglose

- `@RequestBody`: Vincula el cuerpo de la solicitud a un objeto `Usuario`.
- `ResponseEntity<Usuario>`: Permite devolver un objeto `Usuario` junto con un código de estado HTTP.

## 5. Manejo de Errores

Puedes utilizar excepciones personalizadas para manejar errores y devolver respuestas adecuadas.

### Ejemplo de Manejo de Excepciones

```java
@ExceptionHandler(UsuarioNoEncontradoException.class)
public ResponseEntity<String> manejarError(UsuarioNoEncontradoException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
}
```

## 6. Conclusión

`@RestController` es una herramienta esencial en Spring Boot para construir APIs RESTful de manera rápida y eficiente, permitiendo a los desarrolladores gestionar solicitudes y respuestas de forma sencilla y clara.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
