# Documentación sobre `@Controller` en Spring Boot

`@Controller` es una anotación en Spring que se utiliza para definir una clase como un controlador en el contexto de una aplicación web. Los controladores son responsables de manejar las solicitudes HTTP y devolver respuestas adecuadas.

## 1. Introducción

La anotación `@Controller` se utiliza principalmente en aplicaciones MVC (Modelo-Vista-Controlador) para gestionar la lógica de negocio y la interacción con las vistas. En combinación con otras anotaciones, permite una fácil creación de aplicaciones web.

## 2. Uso Básico

### Ejemplo de Controlador

```java
@Controller
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario")
    public String mostrarUsuario(Model model) {
        Usuario usuario = usuarioService.encontrarPorId(1L);
        model.addAttribute("usuario", usuario);
        return "usuarioVista"; // Nombre de la vista a renderizar
    }
}
```

### Desglose

- `@Controller`: Indica que la clase es un controlador que maneja las solicitudes web.
- `@RequestMapping("/api")`: Define la ruta base para todas las solicitudes manejadas por este controlador.
- `@GetMapping("/usuario")`: Define la ruta específica para manejar solicitudes GET a `/api/usuario`.
- `Model model`: Permite agregar atributos que se enviarán a la vista.
- `return "usuarioVista"`: Especifica el nombre de la vista que se debe renderizar.

## 3. Manejo de Formulario

Los controladores también pueden manejar formularios y solicitudes POST.

### Ejemplo de Manejo de Formulario

```java
@PostMapping("/usuario")
public String crearUsuario(@ModelAttribute Usuario usuario) {
    usuarioService.guardar(usuario);
    return "redirect:/api/usuario"; // Redirige después de crear
}
```

### Desglose

- `@PostMapping("/usuario")`: Maneja solicitudes POST a `/api/usuario`.
- `@ModelAttribute Usuario usuario`: Vincula los datos del formulario a un objeto `Usuario`.
- `return "redirect:/api/usuario"`: Redirige a la vista después de la creación.

## 4. Uso de `@ResponseBody`

Si deseas devolver datos en lugar de vistas, puedes combinar `@Controller` con `@ResponseBody`, o simplemente usar `@RestController`.

### Ejemplo

```java
@Controller
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.encontrarPorId(id);
    }
}
```

## 5. Conclusión

`@Controller` es fundamental en Spring Boot para gestionar la lógica de las aplicaciones web, permitiendo a los desarrolladores manejar solicitudes y respuestas de manera efectiva. Es parte integral de la arquitectura MVC de Spring.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
