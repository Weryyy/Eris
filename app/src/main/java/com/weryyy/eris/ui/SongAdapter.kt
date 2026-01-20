package com.weryyy.eris.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weryyy.eris.R
import com.weryyy.eris.data.Song
import com.weryyy.eris.databinding.ItemSongBinding

class SongAdapter(
    private val onPlayClick: (Song) -> Unit,
    private val onDownloadClick: (Song) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val songs = mutableListOf<Song>()

    fun submitList(newSongs: List<Song>) {
        val diffCallback = SongDiffCallback(songs, newSongs)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        
        songs.clear()
        songs.addAll(newSongs)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int = songs.size

    inner class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.tvSongName.text = song.name
            binding.tvArtistName.text = song.artist
            binding.tvAlbumName.text = song.album

            binding.btnPlay.setOnClickListener {
                onPlayClick(song)
            }

            binding.btnDownload.setOnClickListener {
                onDownloadClick(song)
            }
            
            if (song.isDownloaded) {
                binding.btnDownload.setImageResource(R.drawable.ic_downloaded)
            } else {
                binding.btnDownload.setImageResource(R.drawable.ic_download)
            }
        }
    }
    
    private class SongDiffCallback(
        private val oldList: List<Song>,
        private val newList: List<Song>
    ) : DiffUtil.Callback() {
        
        override fun getOldListSize(): Int = oldList.size
        
        override fun getNewListSize(): Int = newList.size
        
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
