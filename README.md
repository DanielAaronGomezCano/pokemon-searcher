# Pokémon Searcher - Java Spring Boot API

Este proyecto forma parte de una prueba técnica para desarrollador Java Jr. Consiste en una API web construida con Spring Boot que consume datos de [PokéAPI](https://pokeapi.co) y permite consultar información de Pokémon.

👨‍💻 Autor
Daniel Aarón Gómez Cano 
Candidato a Desarrollador Java Jr.
Proyecto realizado como parte de evaluación para Global Payments Inc.

## 🚀 Funcionalidad

La aplicación expone tres endpoints principales:

### 1. `GET /pokemon/list`
Devuelve una lista de 100 Pokémon con la siguiente información:
- Nombre
- Altura
- Imagen
- Habilidades
- Formas

### 2. `GET /pokemon/{name}`
Busca un Pokémon por nombre exacto (ejemplo: `pikachu`) y devuelve su información detallada.

### 3. `GET /pokemon/search?query={text}`
Busca Pokémon por coincidencia parcial en el nombre.  
Ejemplo: `/pokemon/search?query=char` podría devolver `charmander`, `charizard`, etc.

---

## 🛠️ Tecnologías usadas

- Java 17
- Spring Boot 3.3.11
- Spring WebFlux (WebClient)
- Maven
- PokéAPI (fuente de datos)
- IntelliJ IDEA (recomendado para desarrollo)

---

## ✅ Requisitos

- JDK 17 (preferentemente [Adoptium Temurin](https://adoptium.net/en-GB/temurin/releases/?version=17))
- Maven
- IDE compatible con Java (IntelliJ IDEA recomendado)

---

## 📦 Instalación y ejecución

## --bash
# 1. Clona el repositorio
git clone https://github.com/tu_usuario/pokemon-searcher.git
cd pokemon-searcher

# 2. Ejecuta con Maven desde línea de comandos
./mvnw spring-boot:run

# O desde IntelliJ
# Abre el proyecto y ejecuta PokemonSearcherApplication

Una vez iniciado, accede a:
http://localhost:8080/pokemon/list
http://localhost:8080/pokemon/pikachu
http://localhost:8080/pokemon/search?query=bulb

🔒 Manejo de errores
404 si el Pokémon no existe

500 si hay un problema con la API externa

Respuestas claras y limpias en formato JSON

🔁 Mejoras implementadas
WebClient con aumento de límite de buffer (para respuestas grandes)

Uso de caché en memoria para evitar llamadas repetidas a la PokéAPI

Código desacoplado y reutilizable (buildPokemonFromData)

Logs de progreso para seguimiento en consola

📌 Notas
La API depende de https://pokeapi.co y puede tener ligeros retrasos si se consulta una gran cantidad de Pokémon.

Para mejorar el rendimiento, las búsquedas por coincidencia están limitadas a 200 Pokémon.
