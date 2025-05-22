# Documentación sobre `@ResponseBody` en Spring Boot

`@ResponseBody` es una anotación en Spring que se utiliza para indicar que el valor de retorno de un método debe ser escrito directamente en el cuerpo de la respuesta HTTP. Esto es particularmente útil para construir APIs RESTful que devuelven datos en formatos como JSON o XML.

## 1. Introducción

`@ResponseBody` permite a los desarrolladores devolver datos de forma directa desde un controlador sin necesidad de utilizar vistas.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    public @ResponseBody Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.encontrarPorId(id);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuario/{id}")`: Define la ruta que manejará las solicitudes GET.
- `@ResponseBody`: Indica que el objeto devuelto por el método debe ser serializado y escrito en el cuerpo de la respuesta.

## 3. Serialización Automática

Spring maneja automáticamente la serialización de objetos a JSON o XML dependiendo de las configuraciones de contenido de la solicitud.

### Ejemplo de respuesta JSON

```java
@GetMapping("/usuarios")
public @ResponseBody List<Usuario> obtenerUsuarios() {
    return usuarioService.listarTodos();
}
```

## 4. Uso en Métodos POST

`@ResponseBody` también se puede utilizar en métodos que manejan solicitudes POST para devolver respuestas.

### Ejemplo

```java
@PostMapping("/usuario")
public @ResponseBody ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardar(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
}
```

## 5. Manejo de Errores

Puedes utilizar `@ResponseBody` junto con manejo de excepciones para devolver respuestas adecuadas en caso de errores.

### Ejemplo de manejo de excepciones

```java
@ExceptionHandler(UsuarioNoEncontradoException.class)
@ResponseBody
public ResponseEntity<String> manejarError(UsuarioNoEncontradoException e) {
    return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
}
```

## 6. Conclusión

`@ResponseBody` es una herramienta esencial en Spring Boot para construir APIs RESTful, permitiendo a los desarrolladores devolver datos directamente en el cuerpo de la respuesta HTTP de manera eficiente.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
