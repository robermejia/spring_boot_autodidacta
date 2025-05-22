# Documentación sobre `@CrossOrigin` en Spring Boot

`@CrossOrigin` es una anotación en Spring que se utiliza para habilitar el intercambio de recursos de origen cruzado (CORS) en aplicaciones web. Esto es especialmente útil cuando una aplicación frontend (como una SPA) necesita comunicarse con un backend en un dominio diferente.

## 1. Introducción

CORS es un mecanismo de seguridad que permite restringir las solicitudes de recursos desde un origen diferente al que sirvió el recurso. La anotación `@CrossOrigin` permite a los desarrolladores especificar qué orígenes están permitidos para acceder a los recursos de la aplicación.

## 2. Uso Básico

### Ejemplo de Uso en un Controlador

```java
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MiControlador {

    @GetMapping("/usuario")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.listarTodos();
    }
}
```

### Desglose

- `@CrossOrigin(origins = "http://localhost:3000")`: Permite solicitudes CORS desde el origen especificado (en este caso, un servidor local que corre en el puerto 3000).

## 3. Configuración Global

Si deseas aplicar CORS a toda la aplicación, puedes configurarlo globalmente en una clase de configuración.

### Ejemplo de Configuración Global

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

## 4. Opciones Adicionales

La anotación `@CrossOrigin` ofrece varias opciones para personalizar el comportamiento de CORS.

### Ejemplo con Más Opciones

```java
@CrossOrigin(origins = "http://localhost:3000", 
             allowedHeaders = "*", 
             allowCredentials = "true", 
             maxAge = 3600)
```

### Desglose de Opciones

- `allowedHeaders`: Especifica qué encabezados pueden ser utilizados en la solicitud.
- `allowCredentials`: Permite el envío de cookies y credenciales en las solicitudes.
- `maxAge`: Define el tiempo en segundos que el navegador puede almacenar la respuesta CORS.

## 5. Conclusión

`@CrossOrigin` es una herramienta poderosa en Spring Boot que facilita la gestión de CORS, permitiendo a las aplicaciones frontend interactuar con APIs backend de manera segura y controlada.

## Referencias

- [Documentación oficial de Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CrossOrigin.html)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
