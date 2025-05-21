# 📚 Lombok - Definición y Anotaciones

## 🔹 ¿Qué es Lombok?

**Lombok** es una biblioteca Java que permite reducir el código repetitivo (boilerplate) en clases Java, como los métodos `get`, `set`, constructores, `toString`, `equals`, `hashCode`, y más, mediante el uso de **anotaciones** que se procesan en tiempo de compilación.

> ⚙️ Lombok ayuda a que tu código Java sea más limpio, legible y corto, generando automáticamente código común sin que tú tengas que escribirlo manualmente.

---

## ✅ Anotaciones de Lombok

### 🧱 Constructores

| Anotación                  | Descripción                                                                  |
|----------------------------|------------------------------------------------------------------------------|
| `@NoArgsConstructor`       | Genera un constructor sin argumentos.                                        |
| `@AllArgsConstructor`      | Genera un constructor con todos los campos.                                  |
| `@RequiredArgsConstructor` | Genera un constructor con los campos `final` o anotados con `@NonNull`.      |

---

### 🔁 Getters, Setters y otros métodos

| Anotación              | Descripción                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `@Getter`              | Genera métodos `get` para todos los campos o para uno específico.           |
| `@Setter`              | Genera métodos `set`.                                                       |
| `@ToString`            | Genera el método `toString()`.                                              |
| `@EqualsAndHashCode`   | Genera los métodos `equals()` y `hashCode()`.                               |
| `@Data`                | Combina `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, y `@RequiredArgsConstructor`. |
| `@Value`               | Versión inmutable de `@Data` (campos `final`, sin setters).                 |
| `@Builder`             | Implementa el patrón Builder.                                               |
| `@Singular`            | Se usa con `@Builder` para listas/sets/maps agregando elementos uno por uno.|

---

### 🛡️ Control de valores nulos

| Anotación    | Descripción                                                 |
|--------------|-------------------------------------------------------------|
| `@NonNull`   | Lanza una excepción si el valor es `null` al asignarlo.     |
| `@Nullable`  | Indica que un valor puede ser `null` (solo documental).     |

---

### 🧪 Logs

| Anotación     | Logger usado                          |
|---------------|---------------------------------------|
| `@Slf4j`      | Usa `org.slf4j.Logger`                |
| `@Log4j`      | Usa `org.apache.log4j.Logger`         |
| `@Log4j2`     | Usa `org.apache.logging.log4j.Logger` |
| `@CommonsLog` | Usa `org.apache.commons.logging.Log`  |
| `@Log`        | Usa `java.util.logging.Logger`        |

---

### 🔧 Utilidades avanzadas

| Anotación        | Descripción                                                                           |
|------------------|---------------------------------------------------------------------------------------|
| `@Cleanup`       | Cierra automáticamente recursos como streams (`try-with-resources`).                 |
| `@SneakyThrows`  | Permite lanzar excepciones sin declararlas.                                          |
| `@Synchronized`  | Alternativa segura a `synchronized` para sincronizar métodos.                        |
| `@With`          | Crea una copia del objeto cambiando un solo campo (inmutabilidad).                   |
| `@FieldDefaults` | Define modificadores por defecto para los campos (`private`, `final`, etc.).         |
| `@Accessors`     | Personaliza los nombres de métodos generados (`fluent`, `chain`, etc.).              |
| `@SuperBuilder`  | Variante de `@Builder` compatible con herencia.                                      |
