# Guía de Migración: De Spotify a YouTube

## 🔄 Resumen de Cambios

Esta versión de Eris ha sido adaptada para usar **YouTube Data API v3** en lugar de Spotify API, debido a las limitaciones actuales para crear aplicaciones de Spotify.

## 📋 Principales Diferencias

### Antes (Spotify)
- ✅ Búsqueda directa de canciones
- ✅ Vista previa de 30 segundos
- ❌ Requiere registro de app en Spotify (actualmente no disponible)
- ✅ Reproducción directa en la app

### Ahora (YouTube)
- ✅ Búsqueda de videos musicales
- ✅ Enlaces a YouTube para reproducción completa
- ✅ API gratuita con 10,000 unidades/día
- ℹ️ Reproducción mediante YouTube o apps externas

## 🔧 Cambios Técnicos Realizados

### 1. Dependencias (build.gradle)
```kotlin
// ANTES: Spotify SDK
implementation 'com.spotify.android:auth:2.1.0'

// AHORA: YouTube Data API
implementation 'com.google.apis:google-api-services-youtube:v3-rev20230502-2.0.0'
implementation 'com.google.http-client:google-http-client-android:1.43.3'
implementation 'com.google.http-client:google-http-client-jackson2:1.43.3'
```

### 2. Nuevo Servicio API
- **Agregado**: `YouTubeApiService.kt` - Interfaz para YouTube Data API
- **Agregado**: `YouTubeConfig.kt` - Configuración de API Key
- **Modificado**: `ApiClient.kt` - Ahora usa googleapis.com

### 3. Modelo de Datos
- **Modificado**: `Models.kt` - Agregado campo `youtubeVideoId` al modelo Song

### 4. MainActivity
- **Modificado**: Función `searchSongs()` - Ahora busca en YouTube
- **Agregado**: Función `showYouTubeLinkDialog()` - Muestra enlace para descargar
- **Agregado**: Función `showYouTubePlaybackInfo()` - Muestra opciones de reproducción

### 5. Interfaz de Usuario
- **Modificado**: `activity_main.xml` - Texto de búsqueda actualizado

## 🚀 Cómo Configurar

### 1. Obtener YouTube API Key

1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un proyecto nuevo
3. Habilita "YouTube Data API v3"
4. Crea una API Key en Credenciales
5. Copia la API Key

### 2. Configurar en el Código

Edita `app/src/main/java/com/weryyy/eris/data/YouTubeConfig.kt`:

```kotlin
object YouTubeConfig {
    const val API_KEY = "TU_API_KEY_AQUI"  // ← Pega tu API Key aquí
}
```

### 3. Compilar y Ejecutar

```bash
# En Android Studio:
Build > Sync Project with Gradle Files
Build > Make Project
Run > Run 'app'
```

## 📱 Nuevos Flujos de Usuario

### Búsqueda
1. Escribe el nombre de una canción
2. Los resultados muestran videos de YouTube
3. Cada resultado muestra: título, canal, miniatura

### Reproducción
1. Toca el botón Play (▶️)
2. Se muestra un diálogo con opciones:
   - **Abrir en YouTube**: Lanza la app de YouTube
   - **Copiar URL**: Copia el enlace al portapapeles
   - **Cancelar**: Cierra el diálogo

### Descarga
1. Toca el botón Descarga (⬇️)
2. Se muestra un diálogo con el enlace de YouTube
3. Opciones:
   - **Copiar**: Para usar en herramientas externas
   - **Abrir en YouTube**: Abre directamente
   - **Cancelar**: Cierra el diálogo

## ⚠️ Limitaciones

1. **No reproducción directa**: Android MediaPlayer no soporta URLs de YouTube directamente
2. **Cuota de API**: 10,000 unidades/día (cada búsqueda ≈ 100 unidades)
3. **Sin duración exacta**: La API de búsqueda no incluye duración del video
4. **Dependencia externa**: Requiere app de YouTube o herramientas de terceros

## 🔮 Mejoras Futuras Posibles

1. **Integrar ExoPlayer**: Para reproducción web directa
2. **YouTube Android Player API**: Para reproducción embebida (si está disponible)
3. **Cache de búsquedas**: Reducir uso de cuota API
4. **Vista de video**: Mostrar video mientras reproduce

## 🔙 Volver a Spotify

Si en el futuro Spotify vuelve a permitir crear apps, puedes:

1. Cambiar a otra rama con el código de Spotify
2. O reversar estos cambios y restaurar:
   - `SpotifyApiService.kt`
   - Dependencias de Spotify en `build.gradle`
   - Lógica original en `MainActivity.kt`

## 📞 Soporte

Si tienes problemas:

1. Verifica que tu API Key de YouTube esté correctamente configurada
2. Asegúrate de que YouTube Data API v3 esté habilitada en tu proyecto de Google Cloud
3. Revisa los logs de Android Studio para errores específicos
4. Consulta la [documentación de YouTube Data API](https://developers.google.com/youtube/v3)

## ✅ Checklist de Implementación

- [x] Actualizar build.gradle con dependencias de YouTube
- [x] Crear YouTubeApiService y modelos de respuesta
- [x] Crear YouTubeConfig para la API Key
- [x] Actualizar ApiClient para usar googleapis.com
- [x] Modificar searchSongs() en MainActivity
- [x] Agregar diálogos para enlaces de YouTube
- [x] Actualizar UI (texto de búsqueda)
- [x] Actualizar README.md con instrucciones
- [x] Crear esta guía de migración

---

**¡Configuración completada! Ya puedes usar Eris con YouTube.** 🎵
