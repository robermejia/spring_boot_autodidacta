# Documentación sobre `@PathVariable` en Spring Boot

`@PathVariable` es una anotación en Spring que se utiliza para capturar valores de las variables de ruta en las solicitudes HTTP. Es especialmente útil para obtener información dinámica de la URL.

## 1. Introducción

`@PathVariable` permite a los desarrolladores extraer valores de la URL y usarlos como parámetros en los métodos del controlador.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.encontrarPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuario/{id}")`: Define la ruta que manejará las solicitudes GET y captura el valor de `id` como variable de ruta.
- `@PathVariable Long id`: Captura el valor de `id` de la URL y lo pasa como argumento al método.

## 3. Múltiples Variables de Ruta

Puedes capturar múltiples variables de ruta en un solo método.

### Ejemplo

```java
@GetMapping("/usuario/{id}/detalles/{detalleId}")
public ResponseEntity<DetalleUsuario> obtenerDetallesUsuario(@PathVariable Long id, @PathVariable Long detalleId) {
    DetalleUsuario detalle = usuarioService.obtenerDetalles(id, detalleId);
    return new ResponseEntity<>(detalle, HttpStatus.OK);
}
```

## 4. Nombres Personalizados

Puedes especificar un nombre personalizado para la variable de ruta al usar `@PathVariable`.

### Ejemplo

```java
@GetMapping("/producto/{productoId}")
public ResponseEntity<Producto> obtenerProducto(@PathVariable("productoId") Long id) {
    Producto producto = productoService.encontrarPorId(id);
    return new ResponseEntity<>(producto, HttpStatus.OK);
}
```

## 5. Uso en Métodos POST y PUT

`@PathVariable` también se puede utilizar en métodos que manejan solicitudes POST o PUT.

### Ejemplo

```java
@PutMapping("/usuario/{id}")
public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
    return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
}
```

## 6. Conclusión

`@PathVariable` es una herramienta fundamental en Spring Boot para manejar variables de ruta, permitiendo a los desarrolladores crear aplicaciones web dinámicas y flexibles.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PathVariable.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
