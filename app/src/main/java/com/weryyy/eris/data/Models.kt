package com.weryyy.eris.data

import java.io.Serializable

data class Song(
    val id: String,
    val name: String,
    val artist: String,
    val album: String,
    val previewUrl: String?,
    val imageUrl: String?,
    val duration: Int,
    val youtubeVideoId: String? = null, // ID del video de YouTube
    var isDownloaded: Boolean = false,
    var localPath: String? = null
) : Serializable

data class SearchResponse(
    val tracks: Tracks
)

data class Tracks(
    val items: List<TrackItem>
)

data class TrackItem(
    val id: String,
    val name: String,
    val artists: List<Artist>,
    val album: Album,
    val preview_url: String?,
    val duration_ms: Int
)

data class Artist(
    val name: String
)

data class Album(
    val name: String,
    val images: List<Image>
)

data class Image(
    val url: String,
    val height: Int,
    val width: Int
)
