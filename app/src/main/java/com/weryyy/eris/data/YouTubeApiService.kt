package com.weryyy.eris.data

import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    
    @GET("youtube/v3/search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("videoCategoryId") category: String = "10", // Music category
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String
    ): YouTubeSearchResponse
}

// YouTube response models
data class YouTubeSearchResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoItem(
    val id: VideoId,
    val snippet: VideoSnippet
)

data class VideoId(
    val videoId: String
)

data class VideoSnippet(
    val title: String,
    val channelTitle: String,
    val description: String,
    val thumbnails: VideoThumbnails
)

data class VideoThumbnails(
    val default: VideoThumbnail?,
    val medium: VideoThumbnail?,
    val high: VideoThumbnail?
)

data class VideoThumbnail(
    val url: String
)
