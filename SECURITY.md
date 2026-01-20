# Seguridad en Eris

## 🔒 Resumen de Seguridad

Este documento describe las consideraciones de seguridad en la aplicación Eris.

## ✅ Medidas de Seguridad Implementadas

### 1. Permisos
- **Principio de mínimos privilegios**: Solo solicita permisos necesarios
- **Permisos en runtime**: Solicita permisos solo cuando se necesitan
- **Compatibilidad Android 13+**: Manejo correcto de permisos modernos

### 2. Almacenamiento
- **Datos de usuario**: SharedPreferences para tokens (no sensibles)
- **Archivos descargados**: Almacenamiento público (Music/Eris/)
- **Sin almacenamiento de contraseñas**: Usa tokens OAuth

### 3. Red
- **HTTPS**: Todas las comunicaciones con Spotify usan HTTPS
- **Logging**: HttpLoggingInterceptor solo para desarrollo
- **Timeouts**: Configurados para evitar conexiones colgadas

### 4. Código
- **Serialization**: Uso correcto de Serializable
- **Input validation**: Sanitización de nombres de archivo
- **Resource management**: Liberación correcta de MediaPlayer

## ⚠️ Consideraciones de Seguridad

### 1. Credenciales de Spotify
**Riesgo**: CLIENT_SECRET expuesto en código fuente
**Mitigación recomendada**: 
- Mover credenciales a archivo local no versionado
- Usar variables de entorno o BuildConfig
- Para producción, implementar backend con OAuth

### 2. Tokens de Acceso
**Riesgo**: Tokens almacenados en SharedPreferences sin cifrar
**Mitigación recomendada**:
- Usar EncryptedSharedPreferences para tokens
- Implementar refresh token automático
- Expiración de tokens

### 3. Preview URLs
**Limitación**: Solo acceso a clips de 30 segundos
**Beneficio**: Reduce riesgo de piratería

## 🔐 Mejoras Recomendadas para Producción

### Alta Prioridad
1. **Cifrar tokens**: Usar EncryptedSharedPreferences
```kotlin
val masterKey = MasterKey.Builder(context)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
    .build()

val sharedPreferences = EncryptedSharedPreferences.create(
    context,
    "eris_secure_prefs",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)
```

2. **Mover credenciales fuera del código**:
```kotlin
// En local.properties (no versionado)
spotify.clientId=TU_CLIENT_ID
spotify.clientSecret=TU_CLIENT_SECRET

// En build.gradle
def localProperties = new Properties()
localProperties.load(new FileInputStream(rootProject.file("local.properties")))

android {
    defaultConfig {
        buildConfigField "String", "SPOTIFY_CLIENT_ID", localProperties['spotify.clientId']
        buildConfigField "String", "SPOTIFY_CLIENT_SECRET", localProperties['spotify.clientSecret']
    }
}
```

3. **Implementar Certificate Pinning**:
```kotlin
val certificatePinner = CertificatePinner.Builder()
    .add("api.spotify.com", "sha256/AAAAAAAAAAAAA...")
    .build()

val client = OkHttpClient.Builder()
    .certificatePinner(certificatePinner)
    .build()
```

### Media Prioridad
1. **Validación de entrada**: Sanitizar URLs antes de descargar
2. **Rate limiting**: Limitar número de peticiones a la API
3. **Crash reporting**: Firebase Crashlytics sin datos sensibles

### Baja Prioridad
1. **Code obfuscation**: ProGuard/R8 en release builds
2. **Root detection**: Detectar dispositivos rooteados
3. **SSL Pinning**: Para mayor seguridad en comunicaciones

## 🐛 Reportar Vulnerabilidades

Si encuentras una vulnerabilidad de seguridad:

1. **NO** abras un issue público
2. Envía un email privado al mantenedor
3. Incluye:
   - Descripción de la vulnerabilidad
   - Pasos para reproducir
   - Impacto potencial
   - Sugerencias de mitigación si las tienes

## 📋 Checklist de Seguridad

Antes de lanzar a producción:

- [ ] Cifrar SharedPreferences con EncryptedSharedPreferences
- [ ] Mover credenciales a BuildConfig o backend
- [ ] Implementar OAuth 2.0 completo con refresh tokens
- [ ] Habilitar ProGuard/R8
- [ ] Remover o desactivar logging en producción
- [ ] Implementar certificate pinning
- [ ] Auditoría de dependencias (vulnerabilidades conocidas)
- [ ] Testing de seguridad (OWASP Mobile Top 10)
- [ ] Revisar permisos solicitados
- [ ] Implementar manejo seguro de errores

## 📚 Referencias

- [OWASP Mobile Security](https://owasp.org/www-project-mobile-security/)
- [Android Security Best Practices](https://developer.android.com/topic/security/best-practices)
- [Spotify API Security](https://developer.spotify.com/documentation/general/guides/authorization/)

## 🔄 Última Revisión

- **Fecha**: Enero 2026
- **Estado**: Desarrollo (no apto para producción sin mejoras de seguridad)
- **Nivel de riesgo actual**: Medio (para desarrollo/pruebas)

---

**Nota**: Esta aplicación está diseñada para propósitos educativos y de desarrollo. 
Para uso en producción, implementa todas las mejoras de seguridad recomendadas.
