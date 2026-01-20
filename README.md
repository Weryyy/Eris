# Eris - Reproductor de Música con Spotify

Eris es una aplicación Android de reproductor de música que se integra con la API de Spotify para buscar, descargar y reproducir canciones directamente desde tu teléfono móvil.

## 🎵 Características

- **Búsqueda de canciones** mediante la API de Spotify
- **Descarga de canciones** para reproducción offline
- **Reproductor de música completo** con controles:
  - Play/Pausa
  - Stop
  - Barra de progreso con seek
  - Información de la canción (nombre, artista, álbum)
- **Soporte para Bluetooth** (automático a través del sistema Android)
- **Control de volumen** (mediante los botones del dispositivo)
- **Interfaz moderna** con Material Design
- **Notificaciones** para reproducción y descargas

## 📋 Requisitos

- Android Studio Arctic Fox o superior
- Android SDK 24 o superior (Android 7.0+)
- Cuenta de desarrollador de Spotify
- Dispositivo Android o emulador

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/Weryyy/Eris.git
cd Eris
```

### 2. Configurar credenciales de Spotify

1. Ve a [Spotify Developer Dashboard](https://developer.spotify.com/dashboard)
2. Inicia sesión o crea una cuenta
3. Haz clic en "Create an App"
4. Completa los detalles:
   - **App name**: Eris Music Player
   - **App description**: Reproductor de música con integración Spotify
   - **Redirect URI**: `eris://callback`
5. Una vez creada, copia el **Client ID** y **Client Secret**

6. Abre el archivo `app/src/main/java/com/weryyy/eris/data/SpotifyConfig.kt`
7. Reemplaza los valores:
```kotlin
const val CLIENT_ID = "tu_client_id_aqui"
const val CLIENT_SECRET = "tu_client_secret_aqui"
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
4. Se mostrará una lista de resultados de Spotify

### Reproducir música
1. En la lista de resultados, toca el botón de **Play** (▶️) junto a cualquier canción
2. El reproductor aparecerá en la parte inferior con:
   - Nombre de la canción y artista
   - Barra de progreso (puedes arrastrarla para saltar a cualquier posición)
   - Botones de Pausa y Stop

### Descargar canciones
1. Toca el botón de **Descarga** (⬇️) junto a cualquier canción
2. La canción se descargará en la carpeta `Music/Eris` de tu dispositivo
3. Recibirás una notificación cuando se complete la descarga

### Controles de reproducción
- **Play/Pausa**: Toca el botón grande del centro
- **Stop**: Detiene la reproducción y cierra el reproductor
- **Volumen**: Usa los botones de volumen de tu dispositivo
- **Bluetooth**: Conecta tus auriculares Bluetooth y el audio se reproducirá automáticamente

## 🛠️ Estructura del Proyecto

```
Eris/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/weryyy/eris/
│   │       │   ├── data/           # Modelos y API
│   │       │   │   ├── Models.kt
│   │       │   │   ├── SpotifyApiService.kt
│   │       │   │   ├── ApiClient.kt
│   │       │   │   ├── SpotifyConfig.kt
│   │       │   │   └── AuthManager.kt
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
- **Retrofit**: Cliente HTTP para consumir la API de Spotify
- **Coroutines**: Para operaciones asíncronas
- **MediaPlayer**: Para reproducción de audio
- **Spotify Web API**: Para buscar y obtener información de canciones

## ⚠️ Notas Importantes

1. **Spotify Preview URLs**: La API de Spotify proporciona URLs de vista previa de 30 segundos. Para reproducción completa, necesitarías implementar el Spotify SDK completo con streaming.

2. **Permisos**: La app requiere permisos de:
   - Internet
   - Almacenamiento (para guardar descargas)
   - Servicios en primer plano (para reproducción)

3. **Limitaciones de la API**: Spotify tiene límites de tasa para las peticiones API. Para uso en producción, implementa manejo de tokens OAuth adecuado.

## 🔧 Mejoras Futuras

- [ ] Autenticación completa con OAuth de Spotify
- [ ] Streaming de canciones completas usando Spotify SDK
- [ ] Lista de reproducción personalizada
- [ ] Historial de reproducción
- [ ] Modo shuffle y repeat
- [ ] Ecualizador
- [ ] Compartir canciones
- [ ] Widget de reproductor para la pantalla de inicio
- [ ] Modo oscuro/claro

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👨‍💻 Autor

Desarrollado por Weryyy

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir los cambios que te gustaría hacer.

---

**¡Disfruta de tu música con Eris! 🎵**
