# Guía de Configuración de Eris

## 🎯 Versión Actual: YouTube

Esta versión de Eris usa **YouTube Data API v3** para buscar música. No requiere cuenta de Spotify.

## ⚡ Configuración Rápida (5 minutos)

### 1. Obtener API Key de YouTube

1. **Ir a Google Cloud Console**
   - Abre https://console.cloud.google.com/
   - Inicia sesión con tu cuenta de Google

2. **Crear Proyecto**
   - Haz clic en el selector de proyectos (arriba)
   - Clic en "Nuevo Proyecto"
   - Nombre: `Eris Music App`
   - Clic en "Crear"

3. **Habilitar YouTube Data API**
   - En el menú lateral: APIs y servicios > Biblioteca
   - Busca: "YouTube Data API v3"
   - Clic en "YouTube Data API v3"
   - Clic en "Habilitar"

4. **Crear API Key**
   - Ve a: APIs y servicios > Credenciales
   - Clic en "Crear credenciales"
   - Selecciona "Clave de API"
   - Copia la API Key generada

### 2. Configurar en el Proyecto

1. **Abrir el archivo de configuración**
   ```
   app/src/main/java/com/weryyy/eris/data/YouTubeConfig.kt
   ```

2. **Pegar tu API Key**
   ```kotlin
   object YouTubeConfig {
       const val API_KEY = "AIzaSy..." // ← Pega tu API Key aquí
   }
   ```

3. **Guardar el archivo**

### 3. Compilar y Ejecutar

1. **Sincronizar Gradle**
   - En Android Studio: File > Sync Project with Gradle Files
   - Espera a que termine la sincronización

2. **Conectar dispositivo o emulador**
   - Dispositivo físico: Conecta por USB con depuración habilitada
   - Emulador: Inicia un emulador Android desde AVD Manager

3. **Ejecutar la app**
   - Clic en el botón Run (▶️) o presiona Shift+F10
   - Selecciona tu dispositivo/emulador
   - Espera a que se instale

## 📱 Primer Uso

1. **Conceder Permisos**
   - La app pedirá permisos de almacenamiento (si es Android < 13)
   - Clic en "Permitir"

2. **Buscar Música**
   - Escribe en la barra de búsqueda: "nombre de canción + artista"
   - Presiona Enter
   - Aparecerán resultados de YouTube

3. **Reproducir**
   - Toca el botón Play (▶️) en cualquier resultado
   - Se abrirá un diálogo con opciones
   - Elige "Abrir en YouTube" para reproducir

4. **Obtener Enlace**
   - Toca el botón Descarga (⬇️)
   - El enlace de YouTube se copiará al portapapeles
   - Úsalo en tu app de descarga preferida

## ⚠️ Solución de Problemas

### Error: "API Key inválida"
- ✅ Verifica que pegaste la API Key correctamente
- ✅ Asegúrate de que YouTube Data API v3 esté habilitada
- ✅ Revisa que no haya espacios extra en la API Key

### Error: "Cuota excedida"
- ℹ️ Has usado las 10,000 unidades gratuitas del día
- ⏰ Espera hasta mañana o aumenta tu cuota en Google Cloud Console

### No aparecen resultados
- ✅ Verifica tu conexión a Internet
- ✅ Intenta con otro término de búsqueda
- ✅ Revisa los logs de Android Studio para ver el error exacto

### La app no compila
- ✅ Sincroniza Gradle: File > Sync Project with Gradle Files
- ✅ Limpia el proyecto: Build > Clean Project
- ✅ Reconstruye: Build > Rebuild Project

## 🔒 Seguridad de la API Key

### ⚠️ No Subas tu API Key a GitHub

Para proyectos públicos:

1. **Crear archivo local**
   ```
   local.properties
   ```

2. **Agregar tu API Key**
   ```properties
   youtube.apikey=TU_API_KEY_AQUI
   ```

3. **Leer en build.gradle**
   ```gradle
   def localProperties = new Properties()
   localProperties.load(new FileInputStream(rootProject.file("local.properties")))
   
   buildConfigField "String", "YOUTUBE_API_KEY", localProperties['youtube.apikey']
   ```

4. **Usar en código**
   ```kotlin
   const val API_KEY = BuildConfig.YOUTUBE_API_KEY
   ```

## 📚 Recursos Adicionales

- **Documentación YouTube API**: https://developers.google.com/youtube/v3
- **Cuotas y límites**: https://developers.google.com/youtube/v3/getting-started#quota
- **Google Cloud Console**: https://console.cloud.google.com/
- **MIGRACION_YOUTUBE.md**: Detalles técnicos de los cambios realizados

## ✅ Verificación Final

Antes de usar la app, verifica que:

- [ ] Tienes una API Key de YouTube válida
- [ ] La API Key está configurada en YouTubeConfig.kt
- [ ] YouTube Data API v3 está habilitada en tu proyecto de Google Cloud
- [ ] El proyecto compila sin errores
- [ ] La app se ejecuta en tu dispositivo/emulador

---

**¡Listo! Ya puedes buscar música en YouTube con Eris.** 🎵

Para más información, consulta el README.md principal.
