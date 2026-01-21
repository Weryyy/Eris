package com.weryyy.eris.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.weryyy.eris.R
import com.weryyy.eris.data.ApiClient
import com.weryyy.eris.data.Song
import com.weryyy.eris.databinding.ActivityMainBinding
import com.weryyy.eris.service.DownloadService
import com.weryyy.eris.service.MusicPlayerService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var songAdapter: SongAdapter
    private val songs = mutableListOf<Song>()
    
    private var musicService: MusicPlayerService? = null
    private var isBound = false
    private var currentSong: Song? = null
    
    private val handler = Handler(Looper.getMainLooper())
    // Ya no necesitamos token de Spotify
    // private var accessToken: String = ""

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.MusicBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()
        setupRecyclerView()
        setupSearchView()
        setupPlayerControls()
        bindMusicService()
        
        // Mensaje informativo sobre YouTube
        Toast.makeText(this, "Configura tu API Key de YouTube en YouTubeConfig", Toast.LENGTH_LONG).show()
    }

    private fun checkPermissions() {
        val permissions = mutableListOf<String>()
        
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 100)
        }
    }

    private fun setupRecyclerView() {
        songAdapter = SongAdapter(
            onPlayClick = { song -> playSong(song) },
            onDownloadClick = { song -> downloadSong(song) }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = songAdapter
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchSongs(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupPlayerControls() {
        binding.btnPlayPause.setOnClickListener {
            if (musicService?.isPlaying() == true) {
                musicService?.pauseSong()
                binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            } else {
                musicService?.resumeSong()
                binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
            }
        }

        binding.btnStop.setOnClickListener {
            musicService?.stopSong()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            binding.playerLayout.visibility = View.GONE
            currentSong = null
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService?.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        startSeekBarUpdate()
    }

    private fun searchSongs(query: String) {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                
                // Buscar videos en YouTube
                val response = ApiClient.youtubeApi.searchVideos(
                    query = query,
                    apiKey = com.weryyy.eris.data.YouTubeConfig.API_KEY
                )
                
                songs.clear()
                songs.addAll(response.items.map { item ->
                    Song(
                        id = item.id.videoId,
                        name = item.snippet.title,
                        artist = item.snippet.channelTitle,
                        album = "", // YouTube no tiene álbum
                        previewUrl = "https://www.youtube.com/watch?v=${item.id.videoId}", // URL para referencia, no reproducible directamente
                        imageUrl = item.snippet.thumbnails.high?.url 
                            ?: item.snippet.thumbnails.medium?.url 
                            ?: item.snippet.thumbnails.default?.url,
                        duration = -1, // YouTube Data API búsqueda básica no proporciona duración
                        youtubeVideoId = item.id.videoId
                    )
                })
                
                songAdapter.submitList(songs)
                binding.progressBar.visibility = View.GONE
                
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playSong(song: Song) {
        // Si es un video de YouTube, mostramos información al usuario
        if (song.youtubeVideoId != null) {
            showYouTubePlaybackInfo(song)
            return
        }
        
        // Para otras fuentes con URLs reproducibles directamente
        currentSong = song
        musicService?.playSong(song)
        
        binding.playerLayout.visibility = View.VISIBLE
        binding.tvSongName.text = song.name
        binding.tvArtistName.text = song.artist
        binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
        
        binding.seekBar.max = musicService?.getDuration() ?: 0
    }
    
    private fun showYouTubePlaybackInfo(song: Song) {
        val youtubeUrl = "https://www.youtube.com/watch?v=${song.youtubeVideoId}"
        
        android.app.AlertDialog.Builder(this)
            .setTitle("Reproducir en YouTube")
            .setMessage("Esta canción es de YouTube. Puedes:\n\n" +
                    "1. Abrir en YouTube para reproducir\n" +
                    "2. Copiar el enlace para usar en otra app\n\n" +
                    "URL: $youtubeUrl")
            .setPositiveButton("Abrir en YouTube") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(youtubeUrl))
                startActivity(intent)
            }
            .setNeutralButton("Copiar URL") { _, _ ->
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("YouTube URL", youtubeUrl)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Enlace copiado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun downloadSong(song: Song) {
        if (song.previewUrl == null) {
            Toast.makeText(this, "No hay URL disponible", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Para YouTube, mostramos el enlace al usuario
        if (song.youtubeVideoId != null) {
            showYouTubeLinkDialog(song)
        } else {
            // Para otras fuentes (si las hubiera), intentar descargar
            val intent = Intent(this, DownloadService::class.java).apply {
                putExtra("song", song)
                putExtra("url", song.previewUrl)
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            
            Toast.makeText(this, "Descargando ${song.name}...", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showYouTubeLinkDialog(song: Song) {
        val youtubeUrl = "https://www.youtube.com/watch?v=${song.youtubeVideoId}"
        
        android.app.AlertDialog.Builder(this)
            .setTitle("Enlace de YouTube")
            .setMessage("Puedes copiar este enlace y usar tu herramienta preferida para descargar:\n\n$youtubeUrl")
            .setPositiveButton("Copiar") { _, _ ->
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("YouTube URL", youtubeUrl)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Enlace copiado al portapapeles", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Abrir en YouTube") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(youtubeUrl))
                startActivity(intent)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun bindMusicService() {
        val intent = Intent(this, MusicPlayerService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun startSeekBarUpdate() {
        handler.post(object : Runnable {
            override fun run() {
                if (isBound && musicService?.isPlaying() == true) {
                    binding.seekBar.progress = musicService?.getCurrentPosition() ?: 0
                    handler.postDelayed(this, 100)
                } else {
                    // Re-check after a longer delay when not playing
                    handler.postDelayed(this, 500)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
        handler.removeCallbacksAndMessages(null)
    }
}
