package com.weryyy.eris.data

/**
 * Configuración de Spotify API
 * 
 * Para obtener tus credenciales:
 * 1. Ve a https://developer.spotify.com/dashboard
 * 2. Crea una nueva aplicación
 * 3. Copia el Client ID y Client Secret
 * 4. Agrega tu Redirect URI en la configuración de la app
 */
object SpotifyConfig {
    // TODO: Reemplaza estos valores con tus credenciales de Spotify
    const val CLIENT_ID = "TU_CLIENT_ID_AQUI"
    const val CLIENT_SECRET = "TU_CLIENT_SECRET_AQUI"
    const val REDIRECT_URI = "eris://callback"
    
    // Scopes necesarios para la app
    const val SCOPES = "user-read-private streaming user-read-email"
}
