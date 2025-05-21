# 📚 Spring Web - Definición y Anotaciones

## 🔹 ¿Qué es Spring Web?

**Spring Web** es un módulo del ecosistema Spring Framework que proporciona herramientas para el desarrollo de aplicaciones web. Incluye soporte para **servlets, controladores, peticiones HTTP**, y es la base de **Spring MVC**. También ofrece clases utilitarias para manejar el enrutamiento, el procesamiento de peticiones y respuestas, y la configuración de controladores RESTful.

> 🌍 Spring Web sirve como la base de aplicaciones web tradicionales y RESTful APIs, integrando fácilmente con otros módulos como Spring Security, Spring Boot, Spring Data, etc.

---


## 🧩 ¿Qué es Spring MVC?

**Spring MVC** (Model-View-Controller) es una **parte dentro de Spring Web** que implementa el patrón MVC para estructurar aplicaciones web. Proporciona anotaciones y mecanismos para separar la lógica del negocio, la presentación y el control de flujo.

---

## 📊 Comparación entre Spring Web y Spring MVC

| Aspecto              | Spring Web                             | Spring MVC                                 |
|----------------------|----------------------------------------|--------------------------------------------|
| 🔹 Nivel             | Módulo base                            | Submódulo de Spring Web                    |
| 🔹 Funcionalidad     | Manejo general de aplicaciones web     | Implementación del patrón MVC             |
| 🔹 Incluye           | Soporte para servlets, filtros, etc.   | Controladores, mapeo de URLs, vistas, etc.|
| 🔹 Uso común         | Aplicaciones REST y web en general     | Aplicaciones web estructuradas con MVC    |

---

## 🧠 Conclusión

> ✅ **Spring MVC depende de Spring Web**, ya que utiliza sus funciones para manejar HTTP.  
> ✅ **Spring Web puede existir sin usar Spring MVC**, por ejemplo, para exponer APIs REST simples o manejar solicitudes sin estructura MVC.



## ✅ Anotaciones Comunes en Spring Web

### 🌐 Mapeo de Solicitudes HTTP

| Anotación           | Descripción                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `@RequestMapping`   | Mapea una URL o patrón de URL a un método o clase.                          |
| `@GetMapping`       | Maneja solicitudes HTTP GET.                                                |
| `@PostMapping`      | Maneja solicitudes HTTP POST.                                               |
| `@PutMapping`       | Maneja solicitudes HTTP PUT.                                                |
| `@DeleteMapping`    | Maneja solicitudes HTTP DELETE.                                             |
| `@PatchMapping`     | Maneja solicitudes HTTP PATCH.                                              |

---

### 📥 Parámetros de la Solicitud

| Anotación           | Descripción                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `@RequestParam`     | Extrae parámetros de la URL (query string).                                |
| `@PathVariable`     | Extrae valores dinámicos del path de la URL.                              |
| `@RequestBody`      | Mapea el cuerpo de la solicitud a un objeto Java.                         |
| `@RequestHeader`    | Extrae valores de los encabezados HTTP.                                   |
| `@CookieValue`      | Extrae datos desde cookies.                                                |
| `@RequestAttribute` | Extrae atributos de la solicitud (request scope).                          |

---

### 📤 Respuestas y Control de Salida

| Anotación           | Descripción                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `@ResponseBody`     | Indica que el valor retornado se escribe directamente en la respuesta HTTP.|
| `@ResponseStatus`   | Define el código HTTP que se devolverá con la respuesta.                   |

---

### 🧩 Controladores y Componentes Web

| Anotación           | Descripción                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `@Controller`       | Declara una clase como controlador que devuelve vistas (usado con JSP, etc).|
| `@RestController`   | Versión especializada de `@Controller` para REST APIs, devuelve JSON/XML.  |
| `@CrossOrigin`      | Habilita solicitudes CORS desde otros orígenes.                            |

---

### 🛠️ Otras Utilidades

| Anotación             | Descripción                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `@ExceptionHandler`   | Maneja excepciones específicas dentro de un controlador.                   |
| `@InitBinder`         | Permite personalizar la vinculación de datos del formulario.               |
| `@ModelAttribute`     | Vincula atributos del modelo a la vista y los pasa al controlador.         |
| `@SessionAttributes`  | Guarda atributos del modelo en la sesión HTTP.                             |
| `@SessionAttribute`   | Accede directamente a atributos guardados en la sesión.                    |
| `@Valid` / `@Validated`| Activa la validación de los objetos recibidos en peticiones.              |

---

### ⚙️ Configuración

| Anotación            | Descripción                                                                  |
|----------------------|------------------------------------------------------------------------------|
| `@EnableWebMvc`      | Habilita Spring MVC; se usa en clases de configuración.                     |
| `@Configuration`     | Declara una clase como fuente de configuración basada en Java.              |
| `@ComponentScan`     | Habilita el escaneo automático de componentes en paquetes específicos.      |

---

📌 **Nota**: Spring Web es el módulo base que proporciona las capacidades de Spring MVC. Todas las anotaciones de Spring MVC están disponibles dentro de Spring Web, pero se utilizan comúnmente junto con otros módulos como Spring Boot para facilitar la configuración.
