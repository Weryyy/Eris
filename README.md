# Eris - Reproductor de Música con YouTube

Eris es una aplicación Android de reproductor de música que se integra con la API de YouTube para buscar canciones. Los usuarios pueden buscar música en YouTube y obtener enlaces para reproducir o descargar usando sus herramientas preferidas.

## 🎵 Características

- **Búsqueda de canciones** mediante YouTube Data API v3
- **Obtención de enlaces de YouTube** para canciones encontradas
- **Copia de enlaces** al portapapeles para usar con herramientas externas
- **Apertura directa en YouTube** para reproducción
- **Interfaz moderna** con Material Design
- **Búsqueda rápida** de música por título, artista o álbum

## 📋 Requisitos

- Android Studio Arctic Fox o superior
- Android SDK 24 o superior (Android 7.0+)
- Cuenta de Google Cloud Platform (para YouTube Data API)
- Dispositivo Android o emulador

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/Weryyy/Eris.git
cd Eris
```

### 2. Configurar YouTube Data API

1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un nuevo proyecto o selecciona uno existente
3. Habilita la **YouTube Data API v3**:
   - En el menú, ve a "APIs y servicios" > "Biblioteca"
   - Busca "YouTube Data API v3"
   - Haz clic en "Habilitar"
4. Crea credenciales:
   - Ve a "APIs y servicios" > "Credenciales"
   - Haz clic en "Crear credenciales" > "Clave de API"
   - Copia la clave de API generada

5. Abre el archivo `app/src/main/java/com/weryyy/eris/data/YouTubeConfig.kt`
6. Reemplaza el valor:
```kotlin
const val API_KEY = "TU_API_KEY_DE_YOUTUBE_AQUI"
```

### 3. Compilar y ejecutar

1. Abre el proyecto en Android Studio
2. Sincroniza los archivos Gradle (Build > Sync Project with Gradle Files)
3. Conecta tu dispositivo Android o inicia un emulador
4. Haz clic en el botón "Run" (▶️) o presiona `Shift+F10`

## 📱 Cómo usar la app

### Buscar canciones
1. Abre la app
2. En la barra de búsqueda, escribe el nombre de una canción, artista o álbum
3. Presiona Enter o el botón de búsqueda
4. Se mostrará una lista de resultados de YouTube

### Reproducir música
1. En la lista de resultados, toca el botón de **Play** (▶️) junto a cualquier canción
2. Se abrirá un diálogo con opciones:
   - **Abrir en YouTube**: Abre el video en la app de YouTube
   - **Copiar URL**: Copia el enlace al portapapeles

### Obtener enlaces para descargar
1. Toca el botón de **Descarga** (⬇️) junto a cualquier canción
2. Se mostrará el enlace de YouTube
3. Opciones disponibles:
   - **Copiar**: Copia el enlace para usar en tu herramienta de descarga preferida
   - **Abrir en YouTube**: Abre directamente en YouTube
   - **Cancelar**: Cierra el diálogo

## 🛠️ Estructura del Proyecto

```
Eris/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/weryyy/eris/
│   │       │   ├── data/           # Modelos y API
│   │       │   │   ├── Models.kt
│   │       │   │   ├── YouTubeApiService.kt
│   │       │   │   ├── ApiClient.kt
│   │       │   │   └── YouTubeConfig.kt
│   │       │   ├── service/        # Servicios en segundo plano
│   │       │   │   ├── MusicPlayerService.kt
│   │       │   │   └── DownloadService.kt
│   │       │   └── ui/             # Interfaz de usuario
│   │       │       ├── MainActivity.kt
│   │       │       └── SongAdapter.kt
│   │       ├── res/                # Recursos (layouts, strings, etc.)
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## 📝 Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **Android SDK**: Framework de desarrollo Android
- **Material Design**: Componentes de UI modernos
- **Retrofit**: Cliente HTTP para consumir la API de YouTube
- **Coroutines**: Para operaciones asíncronas
- **YouTube Data API v3**: Para buscar videos musicales

## ⚠️ Notas Importantes

1. **YouTube Data API**: La API tiene una cuota gratuita de 10,000 unidades por día. Cada búsqueda consume aproximadamente 100 unidades.

2. **Permisos**: La app requiere permisos de:
   - Internet
   - Almacenamiento (opcional, para descargas futuras)

3. **Reproducción y Descarga**: Esta app proporciona enlaces de YouTube. Para reproducir o descargar, los usuarios deben:
   - Usar la app de YouTube para reproducir
   - Usar herramientas externas legales para descargar (respetando derechos de autor)

4. **Limitaciones**: 
   - No reproduce audio directamente desde YouTube (por limitaciones técnicas y legales)
   - Requiere apps externas para reproducción completa

## 🔧 Mejoras Futuras

- [ ] Integración con ExoPlayer para reproducción web
- [ ] Lista de favoritos
- [ ] Historial de búsquedas
- [ ] Compartir enlaces
- [ ] Modo oscuro/claro
- [ ] Soporte para playlists de YouTube

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👨‍💻 Autor

Desarrollado por Weryyy

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir los cambios que te gustaría hacer.

## 📚 Nota sobre Spotify

Esta versión de la app usa YouTube en lugar de Spotify debido a limitaciones actuales en la creación de apps de Spotify. El código original con Spotify sigue disponible en otras ramas del repositorio por si las políticas de Spotify cambian en el futuro.

---

**¡Disfruta buscando tu música con Eris! 🎵**
