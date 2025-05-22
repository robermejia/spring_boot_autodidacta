# Documentación sobre `@RequestParam` en Spring Boot

`@RequestParam` es una anotación en Spring que se utiliza para extraer parámetros de consulta de las solicitudes HTTP. Es útil para recibir datos adicionales en la URL, como filtros o criterios de búsqueda.

## 1. Introducción

`@RequestParam` permite a los desarrolladores capturar parámetros de consulta en las solicitudes HTTP, facilitando la personalización de las respuestas basadas en esos parámetros.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuarios(@RequestParam String rol) {
        List<Usuario> usuarios = usuarioService.encontrarPorRol(rol);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@GetMapping("/usuarios")`: Define la ruta que manejará las solicitudes GET.
- `@RequestParam String rol`: Captura el valor del parámetro de consulta `rol` y lo pasa como argumento al método.

## 3. Parámetros Opcionales

Puedes definir parámetros opcionales con un valor predeterminado.

### Ejemplo

```java
@GetMapping("/usuarios")
public ResponseEntity<List<Usuario>> obtenerUsuarios(@RequestParam(defaultValue = "usuario") String rol) {
    List<Usuario> usuarios = usuarioService.encontrarPorRol(rol);
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
}
```

## 4. Múltiples Parámetros

Puedes capturar múltiples parámetros de consulta en un solo método.

### Ejemplo

```java
@GetMapping("/buscar")
public ResponseEntity<List<Usuario>> buscarUsuarios(@RequestParam String nombre, @RequestParam(required = false) String rol) {
    List<Usuario> usuarios = usuarioService.buscar(nombre, rol);
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
}
```

## 5. Tipo de Datos

`@RequestParam` puede manejar diferentes tipos de datos, como `Integer`, `Long`, `Boolean`, etc.

### Ejemplo

```java
@GetMapping("/usuario")
public ResponseEntity<Usuario> obtenerUsuarioPorId(@RequestParam Long id) {
    Usuario usuario = usuarioService.encontrarPorId(id);
    return new ResponseEntity<>(usuario, HttpStatus.OK);
}
```

## 6. Conclusión

`@RequestParam` es una herramienta esencial en Spring Boot para manejar parámetros de consulta, permitiendo a los desarrolladores crear aplicaciones web más flexibles y personalizables.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestParam.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
