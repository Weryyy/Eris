package com.weryyy.eris.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.weryyy.eris.R
import com.weryyy.eris.data.Song
import java.io.IOException

class MusicPlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentSong: Song? = null
    private val binder = MusicBinder()
    private val CHANNEL_ID = "music_player_channel"
    private val NOTIFICATION_ID = 2

    inner class MusicBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        mediaPlayer = MediaPlayer()
    }

    override fun onBind(intent: Intent?): IBinder = binder

    fun playSong(song: Song) {
        try {
            mediaPlayer?.reset()
            
            val source = song.localPath ?: song.previewUrl
            if (source != null) {
                mediaPlayer?.setDataSource(source)
                mediaPlayer?.prepare()
                mediaPlayer?.start()
                currentSong = song
                
                startForeground(NOTIFICATION_ID, createNotification())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun pauseSong() {
        mediaPlayer?.pause()
    }

    fun resumeSong() {
        mediaPlayer?.start()
    }

    fun stopSong() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        currentSong = null
        stopForeground(true)
    }

    fun isPlaying(): Boolean = mediaPlayer?.isPlaying ?: false

    fun getCurrentPosition(): Int = mediaPlayer?.currentPosition ?: 0

    fun getDuration(): Int = mediaPlayer?.duration ?: 0

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Reproductor de música",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification() =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Reproduciendo")
            .setContentText(currentSong?.let { "${it.name} - ${it.artist}" } ?: "")
            .setSmallIcon(R.drawable.ic_music_note)
            .build()

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}
