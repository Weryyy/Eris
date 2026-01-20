# Contribuir a Eris

¡Gracias por tu interés en contribuir a Eris! Este documento proporciona pautas para contribuir al proyecto.

## 📋 Cómo Contribuir

### Reportar Bugs

Si encuentras un bug, por favor:

1. **Verifica** que no exista ya un issue para ese bug
2. **Crea un issue** con la siguiente información:
   - Descripción clara del problema
   - Pasos para reproducir el bug
   - Comportamiento esperado vs comportamiento actual
   - Versión de Android y dispositivo
   - Logs relevantes (Logcat)
   - Screenshots si es aplicable

### Sugerir Mejoras

Para sugerir nuevas características:

1. **Abre un issue** describiendo:
   - La funcionalidad deseada
   - Por qué sería útil
   - Cómo debería funcionar
   - Ejemplos de uso

### Enviar Pull Requests

1. **Fork** el repositorio
2. **Crea una rama** para tu feature (`git checkout -b feature/MiNuevaCaracteristica`)
3. **Realiza tus cambios**
4. **Prueba** tus cambios exhaustivamente
5. **Commit** con mensajes descriptivos
6. **Push** a tu fork
7. **Abre un Pull Request** describiendo tus cambios

### Convenciones de Código

#### Kotlin Style Guide

- Sigue las convenciones de Kotlin estándar
- Usa nombres descriptivos para variables y funciones
- Documenta funciones públicas
- Mantén funciones cortas y enfocadas

```kotlin
// ✅ Bueno
fun downloadSong(song: Song, url: String) {
    // Implementación clara
}

// ❌ Malo
fun d(s: Song, u: String) {
    // Nombres confusos
}
```

#### Estructura de Commits

```
tipo: descripción breve

Descripción más detallada si es necesario

Fixes #123
```

Tipos:
- `feat`: Nueva característica
- `fix`: Corrección de bug
- `docs`: Cambios en documentación
- `style`: Formato, espacios, etc.
- `refactor`: Refactorización de código
- `test`: Agregar tests
- `chore`: Tareas de mantenimiento

## 🐛 Problemas Conocidos

### Limitaciones Actuales

1. **Preview URLs de 30 segundos**
   - **Problema**: Spotify API solo proporciona clips de 30 segundos
   - **Solución**: Implementar Spotify SDK completo con autenticación Premium
   - **Prioridad**: Alta

2. **Sin persistencia de canciones descargadas**
   - **Problema**: No hay base de datos local para trackear descargas
   - **Solución**: Implementar Room database
   - **Prioridad**: Media

3. **Token de acceso manual**
   - **Problema**: El token debe ser configurado manualmente y expira
   - **Solución**: Implementar flujo OAuth 2.0 completo
   - **Prioridad**: Alta

4. **Sin cola de reproducción**
   - **Problema**: Solo se puede reproducir una canción a la vez
   - **Solución**: Implementar sistema de cola con siguiente/anterior
   - **Prioridad**: Media

5. **Sin caché de búsquedas**
   - **Problema**: Cada búsqueda hace una llamada API nueva
   - **Solución**: Implementar caché con tiempo de expiración
   - **Prioridad**: Baja

### Bugs Conocidos

#### Android 13+
- **Problema**: Permisos de notificación no solicitados automáticamente
- **Solución temporal**: Solicitar permisos manualmente en configuración
- **Issue**: #TBD

#### Algunos dispositivos Samsung
- **Problema**: SeekBar puede no actualizar correctamente
- **Solución temporal**: Reiniciar la app
- **Issue**: #TBD

## 🎯 Roadmap

### v1.1 (Corto Plazo)
- [ ] Implementar OAuth 2.0 completo
- [ ] Base de datos Room para canciones
- [ ] Caché de búsquedas
- [ ] Mejora de permisos para Android 13+

### v1.2 (Mediano Plazo)
- [ ] Cola de reproducción
- [ ] Controles siguiente/anterior
- [ ] Shuffle y repeat
- [ ] Historial de reproducción
- [ ] Búsqueda por voz

### v1.3 (Largo Plazo)
- [ ] Widget de reproductor
- [ ] Integración con Spotify SDK completo
- [ ] Ecualizador
- [ ] Compartir canciones
- [ ] Modo coche
- [ ] Sincronización entre dispositivos

### v2.0 (Futuro)
- [ ] Playlists personalizadas
- [ ] Recomendaciones basadas en IA
- [ ] Integración con otros servicios (Apple Music, YouTube Music)
- [ ] Modo social (compartir con amigos)
- [ ] Letras de canciones
- [ ] Visualizador de audio

## 📚 Recursos para Contribuidores

### Documentación Técnica
- [Spotify Web API Reference](https://developer.spotify.com/documentation/web-api/)
- [Android Developers - Media](https://developer.android.com/guide/topics/media)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Material Design Guidelines](https://material.io/design)

### Setup de Desarrollo

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/Weryyy/Eris.git
   cd Eris
   ```

2. **Abrir en Android Studio**:
   - File > Open > Seleccionar carpeta Eris

3. **Configurar credenciales**:
   - Editar `app/src/main/java/com/weryyy/eris/data/SpotifyConfig.kt`
   - Agregar CLIENT_ID y CLIENT_SECRET

4. **Sync Gradle**:
   - Build > Sync Project with Gradle Files

5. **Ejecutar tests** (cuando estén disponibles):
   ```bash
   ./gradlew test
   ```

### Herramientas Recomendadas

- **Android Studio**: IDE oficial para Android
- **Postman**: Para probar endpoints de Spotify API
- **LeakCanary**: Para detectar memory leaks (en desarrollo)
- **Firebase Crashlytics**: Para reportar crashes en producción

## 🧪 Testing

### Tests Necesarios

Actualmente el proyecto no tiene tests. Contribuciones de tests son muy bienvenidas:

#### Unit Tests
- [ ] Tests de modelos de datos
- [ ] Tests de AuthManager
- [ ] Tests de ApiClient

#### Integration Tests
- [ ] Tests de SpotifyApiService
- [ ] Tests de DownloadService
- [ ] Tests de MusicPlayerService

#### UI Tests
- [ ] Tests de MainActivity
- [ ] Tests de SongAdapter
- [ ] Tests de controles de reproducción

## 🤝 Código de Conducta

### Nuestros Valores

- **Respeto**: Trata a todos con respeto y dignidad
- **Colaboración**: Trabaja en equipo y ayuda a otros
- **Inclusividad**: Acoge a personas de todos los backgrounds
- **Profesionalismo**: Mantén un tono profesional en todas las interacciones

### Comportamiento Esperado

✅ **Hacer**:
- Ser respetuoso y considerado
- Proporcionar feedback constructivo
- Aceptar críticas constructivas
- Enfocarse en lo mejor para el proyecto

❌ **No Hacer**:
- Usar lenguaje ofensivo o inapropiado
- Hacer ataques personales
- Trollear o hacer comentarios destructivos
- Compartir información privada sin permiso

## 📞 Contacto

- **GitHub Issues**: Para bugs y features
- **Pull Requests**: Para contribuir código
- **Discussions**: Para preguntas y discusiones generales

## 📄 Licencia

Al contribuir a Eris, aceptas que tus contribuciones serán licenciadas bajo la Licencia MIT del proyecto.

---

¡Gracias por contribuir a Eris! 🎵

Juntos podemos crear el mejor reproductor de música para Android.
