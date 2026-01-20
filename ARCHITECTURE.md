# Arquitectura y Características de Eris

## 🏗️ Arquitectura de la Aplicación

### Capas de la Aplicación

```
┌─────────────────────────────────────┐
│         UI Layer (Activities)        │
│   - MainActivity                     │
│   - SongAdapter                      │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│      Service Layer (Background)      │
│   - MusicPlayerService               │
│   - DownloadService                  │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│       Data Layer (API & Storage)     │
│   - SpotifyApiService                │
│   - ApiClient                        │
│   - AuthManager                      │
│   - Models                           │
└─────────────────────────────────────┘
```

## 📦 Componentes Principales

### 1. UI Components

#### MainActivity
- **Responsabilidad**: Pantalla principal de la aplicación
- **Características**:
  - Barra de búsqueda para canciones
  - Lista de resultados con RecyclerView
  - Reproductor integrado en la parte inferior
  - Gestión de permisos
  - Coordinación de servicios

#### SongAdapter
- **Responsabilidad**: Adaptador para mostrar canciones en lista
- **Características**:
  - Muestra información de cada canción
  - Botones de acción (Play, Descargar)
  - Actualización dinámica de estado

### 2. Service Components

#### MusicPlayerService
- **Tipo**: Bound Service con Foreground Service
- **Responsabilidad**: Gestión de reproducción de audio
- **Características**:
  - Control de MediaPlayer
  - Play, Pause, Stop, Seek
  - Notificación persistente durante reproducción
  - Soporte automático para Bluetooth
  - Gestión del ciclo de vida del audio

**Métodos principales**:
```kotlin
playSong(song: Song)        // Reproduce una canción
pauseSong()                  // Pausa la reproducción
resumeSong()                 // Reanuda la reproducción
stopSong()                   // Detiene completamente
isPlaying(): Boolean         // Estado de reproducción
getCurrentPosition(): Int    // Posición actual en ms
getDuration(): Int           // Duración total en ms
seekTo(position: Int)        // Saltar a posición
```

#### DownloadService
- **Tipo**: Foreground Service
- **Responsabilidad**: Descarga de canciones en segundo plano
- **Características**:
  - Descarga asíncrona usando Coroutines
  - Notificación de progreso
  - Almacenamiento en carpeta Music/Eris
  - Gestión de errores

**Flujo de descarga**:
```
1. Recibe Intent con Song y URL
2. Crea notificación de descarga
3. Descarga archivo en segundo plano
4. Guarda en almacenamiento local
5. Actualiza notificación (éxito/error)
6. Se autodestruye cuando termina
```

### 3. Data Components

#### Models
- **Song**: Modelo principal de una canción
  ```kotlin
  data class Song(
      val id: String,
      val name: String,
      val artist: String,
      val album: String,
      val previewUrl: String?,
      val imageUrl: String?,
      val duration: Int,
      var isDownloaded: Boolean,
      var localPath: String?
  )
  ```

- **SearchResponse**: Respuesta de búsqueda de Spotify
- **TrackItem**: Item individual de canción de Spotify

#### SpotifyApiService
- **Tipo**: Retrofit Interface
- **Responsabilidad**: Definición de endpoints de Spotify API
- **Endpoints**:
  - `searchTracks()`: Buscar canciones en Spotify

#### ApiClient
- **Responsabilidad**: Configuración de Retrofit y OkHttp
- **Características**:
  - Logging de peticiones HTTP
  - Timeouts configurados (30 segundos)
  - Conversión JSON con Gson

#### AuthManager
- **Responsabilidad**: Gestión de tokens de autenticación
- **Características**:
  - Almacenamiento seguro en SharedPreferences
  - Métodos para guardar/obtener/limpiar tokens

#### SpotifyConfig
- **Responsabilidad**: Configuración de credenciales
- **Contiene**:
  - CLIENT_ID
  - CLIENT_SECRET
  - REDIRECT_URI
  - SCOPES

## 🎨 Recursos de UI

### Layouts

#### activity_main.xml
- **Estructura**:
  - SearchView para búsquedas
  - ProgressBar para loading
  - RecyclerView para lista de canciones
  - Card del reproductor (collapsible)

#### item_song.xml
- **Estructura**:
  - Información de la canción (nombre, artista, álbum)
  - Botón de reproducción
  - Botón de descarga

### Drawables

- `ic_play.xml`: Icono de reproducir (▶️)
- `ic_pause.xml`: Icono de pausa (⏸️)
- `ic_stop.xml`: Icono de detener (⏹️)
- `ic_download.xml`: Icono de descarga (⬇️)
- `ic_downloaded.xml`: Icono de descargado (✓)
- `ic_music_note.xml`: Nota musical para notificaciones
- `button_round.xml`: Fondo circular para botones

### Colores

```kotlin
primary: #1DB954         // Verde de Spotify
background: #121212      // Negro oscuro
surface: #181818         // Gris oscuro
player_background: #282828  // Gris medio
text_primary: #FFFFFF    // Blanco
text_secondary: #B3B3B3  // Gris claro
```

## 🔐 Permisos

### Permisos Declarados

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
```

### Permisos en Runtime

- **WRITE_EXTERNAL_STORAGE**: Para guardar descargas
- **READ_EXTERNAL_STORAGE**: Para leer canciones descargadas

## 📊 Flujos Principales

### Flujo de Búsqueda

```
Usuario escribe query
    ↓
MainActivity.searchSongs()
    ↓
SpotifyApiService.searchTracks()
    ↓
Convertir TrackItem a Song
    ↓
Actualizar RecyclerView con resultados
```

### Flujo de Reproducción

```
Usuario toca Play
    ↓
MainActivity.playSong()
    ↓
Bind a MusicPlayerService
    ↓
MusicPlayerService.playSong()
    ↓
MediaPlayer prepara y reproduce
    ↓
Mostrar notificación
    ↓
Actualizar UI con información de canción
    ↓
Actualizar barra de progreso cada 100ms
```

### Flujo de Descarga

```
Usuario toca Descargar
    ↓
MainActivity.downloadSong()
    ↓
Start DownloadService
    ↓
Crear notificación de descarga
    ↓
Descargar archivo (Coroutine)
    ↓
Guardar en Music/Eris/
    ↓
Actualizar notificación (completado)
    ↓
Service se autodestruye
```

## 🔄 Ciclo de Vida

### MainActivity
```
onCreate()
    ├── checkPermissions()
    ├── setupRecyclerView()
    ├── setupSearchView()
    ├── setupPlayerControls()
    └── bindMusicService()

onDestroy()
    ├── unbindService()
    └── handler.removeCallbacks()
```

### MusicPlayerService
```
onCreate()
    ├── createNotificationChannel()
    └── mediaPlayer = MediaPlayer()

onBind()
    └── return binder

onDestroy()
    ├── mediaPlayer.release()
    └── stopForeground()
```

### DownloadService
```
onStartCommand()
    ├── startForeground()
    └── downloadSong() [Coroutine]
        ├── crear directorio
        ├── descargar archivo
        ├── guardar archivo
        └── actualizar notificación

onDestroy()
    └── serviceScope.cancel()
```

## 🚀 Optimizaciones

### Performance
- **Coroutines**: Para operaciones asíncronas sin bloquear UI
- **ViewBinding**: Acceso eficiente a vistas
- **RecyclerView**: Lista eficiente con reutilización de vistas
- **Handler**: Actualización suave de barra de progreso

### Memoria
- **Servicios**: Se destruyen cuando no están en uso
- **MediaPlayer**: Se libera en onDestroy()
- **Coroutines**: Se cancelan apropiadamente

### Batería
- **Foreground Services**: Solo cuando es necesario
- **Wake Locks**: Gestionados por MediaPlayer automáticamente

## 🧪 Testing

### Casos de Prueba Recomendados

1. **Búsqueda**:
   - Búsqueda exitosa con resultados
   - Búsqueda sin resultados
   - Error de red durante búsqueda

2. **Reproducción**:
   - Play/Pause funcionan correctamente
   - Seek bar actualiza posición
   - Stop cierra el reproductor
   - Reproducción con auriculares Bluetooth

3. **Descargas**:
   - Descarga exitosa
   - Error de red durante descarga
   - Sin espacio de almacenamiento
   - Sin permisos de almacenamiento

## 📝 Notas de Implementación

### Limitaciones Actuales
- Solo preview URLs de Spotify (30 segundos)
- Token manual (sin OAuth automático)
- Sin persistencia de canciones descargadas en base de datos
- Sin cola de reproducción

### Mejoras Futuras
- Implementar OAuth completo
- Base de datos Room para canciones
- Cola de reproducción con siguiente/anterior
- Widget de reproductor
- Ecualizador
- Compartir canciones

---

**Última actualización**: Enero 2026
