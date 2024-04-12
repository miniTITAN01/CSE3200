package com.example.lab6_needgrading.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer


class VideoViewModel : ViewModel() {
    private var videoPlayer: ExoPlayer? = null
    private var currentVideoIndex = 0

    val isPlaying: Boolean
        get() = videoPlayer?.isPlaying ?: false

    val player: ExoPlayer?
        get() = videoPlayer

    private var videoUrls = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
    )

    fun initializePlayer(context: Context) {
        videoPlayer = ExoPlayer.Builder(context).build()
        setMediaSource(currentVideoIndex)
    }

    fun setMediaSource(index: Int) {
        val mediaItem = MediaItem.fromUri(videoUrls[index])
        videoPlayer?.setMediaItem(mediaItem)
        videoPlayer?.prepare()
        videoPlayer?.playWhenReady = false
    }

    fun playVideo() {
        videoPlayer?.play()
    }

    fun pauseVideo() {
        videoPlayer?.pause()
    }

    fun nextVideo() {
        if (currentVideoIndex < videoUrls.size - 1) {
            currentVideoIndex++
            setMediaSource(currentVideoIndex)
        }
    }

    fun previousVideo() {
        if (currentVideoIndex > 0) {
            currentVideoIndex--
            setMediaSource(currentVideoIndex)
        }
    }

    fun releasePlayer() {
        videoPlayer?.release()
        videoPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }
}
