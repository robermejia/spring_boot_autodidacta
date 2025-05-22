# Documentación sobre `@RequestMapping` en Spring Boot

`@RequestMapping` es una de las anotaciones más importantes en Spring Boot, utilizada para manejar solicitudes web. Permite definir cómo se deben mapear las solicitudes HTTP a los métodos de los controladores.

## 1. Introducción

`@RequestMapping` se utiliza en las clases de controladores para especificar la URL que se debe manejar, así como el método HTTP (GET, POST, etc.) que debe asociarse con esa URL.

## 2. Uso Básico

```java
@RestController
@RequestMapping("/api")
public class MiControlador {

    @RequestMapping("/saludo")
    public String saludo() {
        return "¡Hola, mundo!";
    }
}
```

### Desglose

- `@RestController`: Indica que la clase es un controlador y que los métodos devolverán datos en lugar de vistas.
- `@RequestMapping("/api")`: Define la ruta base para todas las solicitudes en esta clase.

## 3. Métodos HTTP

Puedes especificar el método HTTP que deseas manejar usando el atributo `method`.

### Ejemplo

```java
@RequestMapping(value = "/usuario", method = RequestMethod.GET)
public Usuario obtenerUsuario() {
    return new Usuario("Juan", "Pérez");
}
```

### Métodos Comunes

- `RequestMethod.GET`
- `RequestMethod.POST`
- `RequestMethod.PUT`
- `RequestMethod.DELETE`

## 4. Parámetros y Variables

### Parámetros de Consulta

```java
@RequestMapping("/buscar")
public List<Usuario> buscarUsuarios(@RequestParam String nombre) {
    return usuarioService.buscarPorNombre(nombre);
}
```

### Variables de Ruta

```java
@RequestMapping("/usuario/{id}")
public Usuario obtenerUsuarioPorId(@PathVariable String id) {
    return usuarioService.encontrarPorId(id);
}
```

## 5. Consumo y Producción

Puedes especificar los tipos de contenido que tu método puede consumir o producir.

### Ejemplo

```java
@RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
}
```

## 6. Otros Atributos

- **headers**: Filtra las solicitudes por encabezados.
- **params**: Filtra las solicitudes por parámetros de consulta.

### Ejemplo

```java
@RequestMapping(value = "/filtrar", params = "tipo=admin")
public List<Usuario> filtrarAdmin() {
    return usuarioService.obtenerAdmins();
}
```

## 7. Conclusión

`@RequestMapping` es una herramienta poderosa en Spring Boot que permite a los desarrolladores definir rutas y manejar solicitudes HTTP de manera efectiva. Con su flexibilidad, puedes crear aplicaciones web robustas y escalables.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
