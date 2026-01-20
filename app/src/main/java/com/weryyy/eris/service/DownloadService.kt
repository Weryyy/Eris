package com.weryyy.eris.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.weryyy.eris.R
import com.weryyy.eris.data.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class DownloadService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val CHANNEL_ID = "download_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song = intent?.getSerializableExtra("song") as? Song
        val url = intent?.getStringExtra("url")

        if (song != null && url != null) {
            startForeground(NOTIFICATION_ID, createNotification("Descargando ${song.name}"))
            downloadSong(song, url)
        }

        return START_NOT_STICKY
    }

    private fun downloadSong(song: Song, url: String) {
        serviceScope.launch {
            try {
                val musicDir = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
                    "Eris"
                )
                if (!musicDir.exists()) {
                    musicDir.mkdirs()
                }

                val fileName = "${song.artist} - ${song.name}.mp3"
                    .replace("[^a-zA-Z0-9.-]".toRegex(), "_")
                val file = File(musicDir, fileName)

                URL(url).openStream().use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }

                // Notificar descarga completada
                updateNotification("Descarga completada: ${song.name}")
                
            } catch (e: Exception) {
                e.printStackTrace()
                updateNotification("Error descargando: ${song.name}")
            } finally {
                stopSelf()
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Descargas de música",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(text: String) =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Eris")
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_download)
            .build()

    private fun updateNotification(text: String) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, createNotification(text))
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }
}
