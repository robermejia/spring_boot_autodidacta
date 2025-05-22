# Documentación sobre `@PutMapping` en Spring Boot

`@PutMapping` es una anotación en Spring que se utiliza para manejar solicitudes HTTP PUT. Se utiliza comúnmente para actualizar recursos existentes en el servidor.

## 1. Introducción

`@PutMapping` permite a los desarrolladores definir métodos en los controladores que procesan solicitudes PUT, facilitando la actualización de datos en el servidor.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @PutMapping("/actualizarUsuario/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@PutMapping("/actualizarUsuario/{id}")`: Define la ruta que manejará las solicitudes PUT en esta clase.

## 3. Cuerpo de la Solicitud

Puedes recibir datos en el cuerpo de la solicitud utilizando `@RequestBody`, que se utiliza para obtener el nuevo estado del recurso.

### Ejemplo

```java
@PutMapping("/usuario/{id}")
public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario usuarioModificado = usuarioService.modificar(id, usuario);
    return new ResponseEntity<>(usuarioModificado, HttpStatus.OK);
}
```

## 4. Respuestas Personalizadas

`@PutMapping` permite devolver respuestas personalizadas, incluyendo códigos de estado HTTP.

### Ejemplo

```java
@PutMapping("/cambiarContraseña/{id}")
public ResponseEntity<Void> cambiarContraseña(@PathVariable Long id, @RequestBody String nuevaContraseña) {
    usuarioService.cambiarContraseña(id, nuevaContraseña);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

## 5. Consumo y Producción

Puedes especificar los tipos de contenido que tu método puede consumir o producir.

### Ejemplo

```java
@PutMapping(value = "/actualizar", consumes = "application/json", produces = "application/json")
public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
    Usuario usuarioActualizado = usuarioService.actualizar(usuario);
    return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
}
```

## 6. Otros Atributos

- **headers**: Filtra las solicitudes por encabezados.
- **params**: Filtra las solicitudes por parámetros de consulta.

### Ejemplo

```java
@PutMapping(value = "/filtrar", params = "tipo=admin")
public List<Usuario> filtrarAdmin(@RequestBody List<Usuario> usuarios) {
    return usuarioService.filtrarPorTipo(usuarios, "admin");
}
```

## 7. Conclusión

`@PutMapping` es una herramienta fundamental en Spring Boot para manejar solicitudes PUT, facilitando la actualización de recursos en el servidor de manera eficiente.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PutMapping.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
