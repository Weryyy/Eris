# Guía de Configuración de Eris

## Paso 1: Configurar Spotify Developer Account

### Crear una aplicación en Spotify:

1. Ve a [Spotify Developer Dashboard](https://developer.spotify.com/dashboard)
2. Haz clic en "Create an App"
3. Completa el formulario:
   - **App name**: Eris Music Player
   - **App description**: Reproductor de música Android
   - **Website**: (opcional)
   - **Redirect URIs**: `eris://callback`
   
4. Acepta los términos y crea la app
5. En el dashboard de tu app, encontrarás:
   - **Client ID**: Una cadena larga (ej: `a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6`)
   - **Client Secret**: Haz clic en "Show Client Secret" para verlo

### Configurar las credenciales en el proyecto:

Abre el archivo `app/src/main/java/com/weryyy/eris/data/SpotifyConfig.kt` y reemplaza:

```kotlin
const val CLIENT_ID = "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6"  // Tu Client ID
const val CLIENT_SECRET = "q1w2e3r4t5y6u7i8o9p0a1s2d3f4g5h6"  // Tu Client Secret
```

## Paso 2: Obtener un Access Token de Spotify

Spotify requiere autenticación OAuth 2.0. Para simplificar, aquí hay dos opciones:

### Opción A: Usar Spotify OAuth (Recomendado para producción)

La app ya tiene el esqueleto para OAuth. Para implementarlo completamente:

1. Agrega el Spotify Auth SDK (ya incluido en dependencias)
2. Implementa el flujo de login en MainActivity
3. Guarda el token usando AuthManager

### Opción B: Obtener token manualmente (Para pruebas rápidas)

1. Ve a [Spotify Web Console](https://developer.spotify.com/console/get-search-item/)
2. Haz clic en "Get Token"
3. Selecciona los scopes necesarios
4. Copia el token generado
5. Úsalo temporalmente en MainActivity (línea donde dice `accessToken`)

**Nota**: Los tokens manuales expiran en 1 hora.

## Paso 3: Compilar el Proyecto

```bash
# En la terminal de Android Studio o tu terminal:
cd Eris
./gradlew build

# O directamente desde Android Studio:
# Build > Make Project (Ctrl+F9)
```

## Paso 4: Ejecutar en Dispositivo/Emulador

1. Conecta tu dispositivo Android con USB debugging activado, o
2. Inicia un emulador desde Android Studio (AVD Manager)
3. Click en Run (▶️) o presiona Shift+F10

## Permisos Necesarios

La app solicitará los siguientes permisos en runtime:
- **Almacenamiento**: Para guardar las canciones descargadas
- **Internet**: Para buscar canciones en Spotify

## Estructura de Archivos Descargados

Las canciones se guardan en:
```
/storage/emulated/0/Music/Eris/
```

Puedes acceder a ellas con cualquier gestor de archivos.

## Troubleshooting

### Error: "401 Unauthorized"
- Verifica que tu token de acceso sea válido
- Asegúrate de haber configurado correctamente CLIENT_ID y CLIENT_SECRET

### Error: "No preview URL available"
- Algunas canciones en Spotify no tienen preview URL
- Solo se pueden descargar canciones con preview_url (clips de 30 segundos)

### Error: "Permission Denied"
- Ve a Configuración > Apps > Eris > Permisos
- Activa "Almacenamiento"

### La descarga no inicia
- Verifica que la canción tenga un preview_url válido
- Revisa los logs de Android (Logcat) para más detalles

## Mejoras Sugeridas

Para convertir esto en una app completa de producción:

1. **Implementar OAuth completo**:
   ```kotlin
   val request = AuthenticationRequest.Builder(
       CLIENT_ID,
       AuthenticationResponse.Type.TOKEN,
       REDIRECT_URI
   ).setScopes(arrayOf("streaming", "user-read-email"))
       .build()
   
   AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
   ```

2. **Agregar base de datos local**:
   - Usa Room para guardar canciones descargadas
   - Mantén un caché de búsquedas

3. **Mejorar el player**:
   - Agregar controles de siguiente/anterior
   - Implementar cola de reproducción
   - Agregar shuffle y repeat

4. **Widget de pantalla de inicio**:
   - Crear un widget para controlar la reproducción sin abrir la app

## Recursos Adicionales

- [Spotify Web API Docs](https://developer.spotify.com/documentation/web-api/)
- [Android MediaPlayer Guide](https://developer.android.com/guide/topics/media/mediaplayer)
- [Material Design Components](https://material.io/develop/android)

## Soporte

Si encuentras problemas o tienes preguntas:
1. Revisa los logs de Android (Logcat en Android Studio)
2. Verifica que todas las dependencias se hayan descargado correctamente
3. Asegúrate de usar Android SDK 24 o superior

¡Disfruta desarrollando con Eris! 🎵
