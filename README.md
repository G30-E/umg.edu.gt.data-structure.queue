# Spotify Queue Simulation (FIFO) — Java + Maven

Simulación tipo Spotify usando una estructura de datos **Cola (FIFO)** implementada **desde cero** en Java, separada en una **librería Maven** y consumida desde un proyecto handler.

---

##  Estructura del repositorio

```
/umg.edu.gt.data-structure.queue
/queueHandler
/README.md
/evidencias
```

---

##  Requisitos

* Java 8 o superior
* Maven
* Ejecución desde consola
* No usar estructuras del JDK como:

  * Queue
  * LinkedList
  * ArrayDeque
  * PriorityQueue

---

#  Parte A — Librería de Cola Propia

**Proyecto:** `umg.edu.gt.data-structure.queue`

Se implementó una cola genérica basada en nodos enlazados.

### Implementación

* Clase `Queue<T>`
* Clase `Node<T>`
* Referencias privadas `head` y `tail`
* Variable interna `size`
* Encapsulamiento (no se exponen nodos)

### Métodos

* `enqueue(T item)` → O(1)
* `dequeue()` → O(1)
* `peek()`
* `isEmpty()`
* `size()`

Si se intenta hacer `dequeue()` en una cola vacía, se lanza una excepción controlada.

---

##  Cómo compilar e instalar la librería

```
cd umg.edu.gt.data-structure.queue
mvn clean install
```

Evidencia: `evidencias/01_mvn_clean_install_libreria.png`

---

#  Parte B — Simulación de Reproducción

**Proyecto:** `queueHandler`

Consume la librería anterior como dependencia Maven.

## Modelo Song

Cada canción contiene:

* artist
* title
* duration (entre 5 y 30 segundos)
* priority (1 = alta, 2 = normal)

Las duraciones varían entre canciones.

---

##  Simulación realista

La reproducción se realiza segundo a segundo usando:

```
Thread.sleep(1000)
```

Logs mostrados:

* Starting playlist
* Now playing
* Playing progreso por segundo
* Finished
* Playlist finished

Se incluye barra visual de progreso.

Evidencias:

* `evidencias/03_ejecucion_consola_inicio.png`
* `evidencias/04_logs_reproduccion_segundo_a_segundo.png`
* `evidencias/06_playlist_finished_resumen.png`

---

#  Parte C — Sistema de Prioridad

Reglas:

* Prioridad 1 se reproduce antes que prioridad 2
* Dentro de cada prioridad se mantiene FIFO
* No se usa PriorityQueue

### Estrategia

Se utilizaron dos colas internas:

* highPriorityQueue
* normalPriorityQueue

Además se implementó una cola **upNext** para la extensión addNext.

Evidencia: `evidencias/05_sistema_prioridad_funcionando.png`

---

#  Parte D — Extensiones implementadas

Se implementaron varias mejoras:

* addNext(Song song) para insertar canciones al frente
* Historial de canciones reproducidas usando estructura propia tipo pila
* Validación para evitar canciones duplicadas
* Barra de progreso visual
* Contador de canciones reproducidas y tiempo total

---

#  Cómo compilar el handler

```
cd queueHandler
mvn clean package
```

Evidencia: `evidencias/02_mvn_clean_package_handler.png`

---

#  Cómo ejecutar desde consola

```
cd queueHandler
java -jar target/queueHandler-1.0.0.jar
```

Evidencias:

* `evidencias/03_ejecucion_consola_inicio.png`
* `evidencias/04_logs_reproduccion_segundo_a_segundo.png`
* `evidencias/06_playlist_finished_resumen.png`

---

#  Decisiones técnicas

* Implementación manual para garantizar O(1) en enqueue y dequeue
* Arquitectura modular con Maven para separar la estructura de datos
* Prioridad implementada sin estructuras del JDK
* Simulación de tiempo real para comportamiento realista

---

#  Conclusión

El proyecto demuestra la implementación de una estructura FIFO desde cero, su integración en una arquitectura modular y una simulación realista de reproducción musical con prioridad, historial y validaciones adicionales.
