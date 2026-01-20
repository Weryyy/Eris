# Ejemplos de Uso de Eris

## 1. Buscar Canciones

### Buscar por nombre de canción:
```
Búsqueda: "Shape of You"
Resultados: Lista de canciones que coinciden
```

### Buscar por artista:
```
Búsqueda: "Ed Sheeran"
Resultados: Canciones del artista
```

### Buscar por álbum:
```
Búsqueda: "Divide album"
Resultados: Canciones del álbum
```

## 2. Reproducir Música

### Reproducir desde lista de búsqueda:
1. Busca una canción
2. Toca el botón ▶️ en el resultado
3. El reproductor aparecerá en la parte inferior
4. Controles disponibles:
   - **Botón central grande**: Play/Pausa
   - **Barra de progreso**: Arrastra para adelantar/retroceder
   - **Botón Stop**: Detiene y cierra el reproductor

### Reproducir música descargada:
1. Las canciones descargadas se guardan en `Music/Eris/`
2. Puedes reproducirlas tocando Play en la lista
3. Se reproducen desde el almacenamiento local (sin Internet)

## 3. Descargar Canciones

### Descargar una canción:
1. Busca la canción que quieres
2. Toca el botón ⬇️ (descarga)
3. Aparecerá una notificación de descarga
4. Cuando termine, verás "Descarga completada"

### Verificar descargas:
```
Ubicación: /storage/emulated/0/Music/Eris/
Formato: [Artista] - [Nombre de la canción].mp3
```

## 4. Control de Volumen

El volumen se controla usando los **botones físicos** del dispositivo:
- **Volumen +**: Sube el volumen
- **Volumen -**: Baja el volumen

Android gestiona automáticamente el volumen de medios.

## 5. Reproducción por Bluetooth

### Conectar auriculares/altavoces Bluetooth:
1. Ve a Configuración > Bluetooth en tu dispositivo
2. Conecta tu dispositivo Bluetooth
3. La música se reproducirá automáticamente por Bluetooth
4. Los controles funcionan igual

Android gestiona automáticamente la salida de audio por Bluetooth.

## 6. Notificaciones de Reproducción

Cuando estás reproduciendo música:
- Aparece una notificación persistente
- Muestra: Nombre de la canción y artista
- Puedes minimizar la app y la música sigue sonando
- Desliza para cerrar la notificación y detener la música

## Casos de Uso Comunes

### Caso 1: Escuchar música mientras navegas en otras apps
```
1. Abre Eris
2. Busca y reproduce una canción
3. Presiona el botón Home
4. La música sigue sonando
5. La notificación te permite volver a Eris
```

### Caso 2: Descargar un álbum completo
```
1. Busca el nombre del álbum
2. Para cada canción del resultado:
   - Toca el botón de descarga
3. Espera a que completen todas las descargas
4. Ahora puedes reproducirlas sin Internet
```

### Caso 3: Hacer ejercicio con música
```
1. Descarga tus canciones favoritas con anticipación
2. Desconecta el WiFi/datos móviles (opcional)
3. Conecta tus auriculares Bluetooth
4. Reproduce las canciones descargadas
5. Usa los controles en la notificación sin abrir la app
```

## Atajos y Tips

### Tips de búsqueda:
- Usa comillas para búsquedas exactas: `"Bohemian Rhapsody"`
- Combina artista y canción: `Queen Bohemian Rhapsody`
- Busca por género: `rock 2020`

### Tips de reproducción:
- Toca en cualquier parte de la barra de progreso para saltar a ese punto
- El botón Stop cierra el reproductor completamente
- Play/Pausa mantiene la posición actual

### Tips de descargas:
- Las descargas continúan aunque cierres la app
- Puedes descargar múltiples canciones a la vez
- El ícono cambia a ✓ cuando la descarga está completa

## Limitaciones Conocidas

1. **Preview URLs de 30 segundos**: 
   - Spotify API solo proporciona clips de 30 segundos
   - Para canciones completas, necesitas Spotify Premium + SDK completo

2. **No todas las canciones tienen preview**:
   - Algunas canciones no tienen preview_url disponible
   - En ese caso, no se puede descargar ni reproducir

3. **Token de acceso expira**:
   - Los tokens de prueba expiran en 1 hora
   - Implementa OAuth para tokens automáticos

## Troubleshooting Común

### "No se puede reproducir la canción"
**Solución**: Verifica que la canción tenga un preview_url válido

### "Error en la búsqueda"
**Solución**: 
- Verifica tu conexión a Internet
- Confirma que el token de Spotify sea válido
- Revisa las credenciales en SpotifyConfig.kt

### "Descarga fallida"
**Solución**:
- Verifica permisos de almacenamiento
- Asegúrate de tener espacio suficiente
- Confirma que la URL de descarga sea válida

### "No se escucha nada"
**Solución**:
- Verifica el volumen del dispositivo
- Revisa si hay auriculares conectados
- Desconecta y reconecta Bluetooth si aplica

## Próximos Pasos

Para aprovechar al máximo Eris:
1. Configura correctamente las credenciales de Spotify
2. Implementa OAuth para tokens automáticos
3. Descarga tus canciones favoritas
4. Explora las características de reproducción
5. Reporta bugs o sugiere mejoras en GitHub

¡Disfruta de tu música! 🎵
