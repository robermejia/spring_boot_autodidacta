# Documentación sobre `@DeleteMapping` en Spring Boot

`@DeleteMapping` es una anotación en Spring que se utiliza para manejar solicitudes HTTP DELETE. Se utiliza comúnmente para eliminar recursos en el servidor.

## 1. Introducción

`@DeleteMapping` permite a los desarrolladores definir métodos en los controladores que procesan solicitudes DELETE, facilitando la eliminación de datos en el servidor.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@DeleteMapping("/eliminarUsuario/{id}")`: Define la ruta que manejará las solicitudes DELETE en esta clase.

## 3. Parámetros de Ruta

Puedes capturar variables de ruta en tus métodos para identificar qué recurso se debe eliminar.

### Ejemplo

```java
@DeleteMapping("/usuario/{id}")
public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id) {
    usuarioService.eliminar(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

## 4. Respuestas Personalizadas

`@DeleteMapping` permite devolver respuestas personalizadas, incluyendo códigos de estado HTTP.

### Ejemplo

```java
@DeleteMapping("/usuarios")
public ResponseEntity<Void> eliminarUsuarios(@RequestBody List<Long> ids) {
    usuarioService.eliminarVarios(ids);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

## 5. Consumo y Producción

Aunque `@DeleteMapping` generalmente no requiere un cuerpo de solicitud, puedes especificar los tipos de contenido que tu método puede consumir o producir si es necesario.

### Ejemplo

```java
@DeleteMapping(value = "/borrar", produces = "application/json")
public ResponseEntity<String> borrarUsuario(@RequestParam Long id) {
    usuarioService.eliminar(id);
    return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
}
```

## 6. Otros Atributos

- **headers**: Filtra las solicitudes por encabezados.
- **params**: Filtra las solicitudes por parámetros de consulta.

### Ejemplo

```java
@DeleteMapping(value = "/filtrar", params = "tipo=admin")
public ResponseEntity<Void> eliminarAdmin(@RequestBody List<Long> ids) {
    usuarioService.eliminarAdministradores(ids);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

## 7. Conclusión

`@DeleteMapping` es una herramienta esencial en Spring Boot para manejar solicitudes DELETE, facilitando la eliminación de recursos en el servidor de manera eficiente.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/DeleteMapping.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
