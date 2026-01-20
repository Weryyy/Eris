# Resumen Visual de la Interfaz de Eris

## Pantalla Principal

```
╔════════════════════════════════════════════╗
║  Eris                                  ☰   ║
╠════════════════════════════════════════════╣
║  ╭──────────────────────────────────────╮  ║
║  │  🔍  Buscar canciones en Spotify...  │  ║
║  ╰──────────────────────────────────────╯  ║
╠════════════════════════════════════════════╣
║                                            ║
║  ┌──────────────────────────────────────┐ ║
║  │ Shape of You                     ▶ ⬇│ ║
║  │ Ed Sheeran                          │ ║
║  │ ÷ (Divide)                          │ ║
║  └──────────────────────────────────────┘ ║
║                                            ║
║  ┌──────────────────────────────────────┐ ║
║  │ Blinding Lights                 ▶ ⬇│ ║
║  │ The Weeknd                          │ ║
║  │ After Hours                         │ ║
║  └──────────────────────────────────────┘ ║
║                                            ║
║  ┌──────────────────────────────────────┐ ║
║  │ Someone Like You                ▶ ⬇│ ║
║  │ Adele                               │ ║
║  │ 21                                  │ ║
║  └──────────────────────────────────────┘ ║
║                                            ║
║            (más resultados...)             ║
║                                            ║
╠════════════════════════════════════════════╣
║  ┌──────────────────────────────────────┐ ║
║  │  ♪ Shape of You                     │ ║
║  │     Ed Sheeran                       │ ║
║  │  ════════●══════════════              │ ║
║  │                                       │ ║
║  │        ⏹     ( ⏸ )                  │ ║
║  └──────────────────────────────────────┘ ║
╚════════════════════════════════════════════╝
```

## Elementos de la UI

### Barra de Búsqueda
- Campo de texto con icono de lupa
- Hint: "Buscar canciones en Spotify..."
- Se activa con Enter o botón de búsqueda

### Lista de Canciones (RecyclerView)
Cada item muestra:
- **Nombre de la canción** (texto grande, bold)
- **Artista** (texto mediano)
- **Álbum** (texto pequeño, gris)
- **Botón Play** (▶️) - Reproduce la canción
- **Botón Descargar** (⬇️) - Descarga la canción

### Reproductor (Parte Inferior)
Aparece solo cuando hay una canción reproduciéndose:
- **Icono musical** (♪)
- **Nombre de la canción** (texto grande, bold, blanco)
- **Nombre del artista** (texto mediano, gris claro)
- **Barra de progreso** (SeekBar)
  - Muestra posición actual
  - Permite saltar arrastrando
- **Controles**:
  - Botón Stop (⏹) - Pequeño, a la izquierda
  - Botón Play/Pausa (▶️/⏸) - Grande, en el centro

## Colores y Estilo

### Esquema de Colores (Tema Spotify)
```
┌─────────────────┬──────────┐
│ Primario        │ #1DB954  │  Verde Spotify
│ Fondo           │ #121212  │  Negro oscuro
│ Superficie      │ #181818  │  Gris muy oscuro
│ Reproductor     │ #282828  │  Gris oscuro
│ Texto Principal │ #FFFFFF  │  Blanco
│ Texto Secundario│ #B3B3B3  │  Gris claro
└─────────────────┴──────────┘
```

### Tipografía
- **Títulos de canción**: 18sp, Bold, Blanco
- **Artista**: 14sp, Regular, Gris claro
- **Álbum**: 12sp, Regular, Gris claro

### Espaciado
- Padding general: 16dp
- Margen entre cards: 8dp
- Padding interno de cards: 12dp

## Estados de la UI

### Estado Inicial
```
- Barra de búsqueda vacía
- Lista vacía (sin resultados)
- Reproductor oculto
```

### Estado Buscando
```
- Barra de búsqueda con texto
- Spinner/Progress bar visible
- Lista vacía
- Reproductor oculto (si no hay reproducción activa)
```

### Estado con Resultados
```
- Barra de búsqueda con texto
- Lista con canciones encontradas
- Cada canción con botones interactivos
- Reproductor visible si hay música sonando
```

### Estado Reproduciendo
```
- Lista con resultados (si hay)
- Reproductor visible en la parte inferior
- Botón Play/Pausa actualizado según estado
- Barra de progreso animándose
- SeekBar actualizado cada 100ms
```

### Estado Descargando
```
- Notificación en barra de estado
- Icono de descarga animado
- Texto: "Descargando [Nombre de la canción]..."
```

## Interacciones

### Búsqueda
```
1. Usuario escribe en barra de búsqueda
2. Usuario presiona Enter
3. Aparece spinner de carga
4. Se muestran resultados
5. Se oculta spinner
```

### Reproducción
```
1. Usuario toca botón Play en una canción
2. Aparece reproductor en parte inferior
3. Comienza la reproducción
4. Botón cambia a Pausa
5. Barra de progreso se actualiza
```

### Pausa
```
1. Usuario toca botón Pausa
2. Se pausa la música
3. Botón cambia a Play
4. Barra de progreso se detiene
```

### Stop
```
1. Usuario toca botón Stop
2. Se detiene la música
3. Reproductor se oculta
4. Estado se reinicia
```

### Seek
```
1. Usuario arrastra la barra de progreso
2. La música salta a la posición seleccionada
3. La reproducción continúa desde ahí
```

### Descarga
```
1. Usuario toca botón Descargar
2. Aparece notificación de descarga
3. Se descarga en segundo plano
4. Notificación se actualiza al completar
5. Icono cambia a ✓ (descargado)
```

## Notificaciones

### Notificación de Reproducción
```
┌──────────────────────────────┐
│ ♪ Eris                       │
│ Reproduciendo                │
│ Shape of You - Ed Sheeran    │
└──────────────────────────────┘
```

### Notificación de Descarga
```
┌──────────────────────────────┐
│ Eris                         │
│ Descargando Shape of You...  │
│ [████████░░] 80%            │
└──────────────────────────────┘
```

### Notificación de Descarga Completada
```
┌──────────────────────────────┐
│ Eris                         │
│ Descarga completada          │
│ Shape of You                 │
└──────────────────────────────┘
```

## Navegación

La app es de una sola pantalla (Single Activity):
- No hay navegación entre pantallas
- Todo sucede en MainActivity
- El reproductor es un componente que aparece/desaparece

## Permisos en Runtime

Cuando se inicia por primera vez:
```
┌──────────────────────────────────────┐
│  Eris necesita permisos              │
│                                      │
│  Almacenamiento                      │
│  Para guardar canciones descargadas  │
│                                      │
│  [Denegar]         [Permitir]       │
└──────────────────────────────────────┘
```

## Responsive Design

- Funciona en orientación portrait y landscape
- Se adapta a diferentes tamaños de pantalla
- Usa ConstraintLayout y LinearLayout
- Material Design Components responsivos

---

Esta es una representación visual simplificada de la interfaz de Eris.
Para ver la implementación real, compila y ejecuta el proyecto.
