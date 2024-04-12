package com.example.lab6_needgrading.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

class AudioViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var contextRef: WeakReference<Context>

    val isPlaying: Boolean
        get() = mediaPlayer?.isPlaying ?: false

    private var currentUrlIndex = 0
    private var videoUrls = listOf(
        "http://stream.rtlradio.de/rnb/mp3-192/",
        "https://ilm-stream12.radiohost.de/ilm_ilovemusicandchill_mp3-192?_art=dD0xNzExNjU2NDk5JmQ9ZDMyZTg4Y2Q5NA",
        "https://stream.0nlineradio.com/edm-festival",
        "https://live.amperwave.net/direct/saga-wsnyfmmp3-ibc2",
        "https://streams.ilovemusic.de/iloveradio17.mp3"
    )

    fun initializePlayer(context: Context) {
        contextRef = WeakReference(context)
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener {
                start()
            }
            setOnErrorListener { _, what, extra ->
                true
            }
        }
        playCurrentStation()
    }

    fun playCurrentStation() {
        mediaPlayer?.apply {
            reset()
            setDataSource(contextRef.get()!!, android.net.Uri.parse(videoUrls[currentUrlIndex]))
            prepareAsync()
        }
    }

    fun nextStation() {
        if (currentUrlIndex < videoUrls.size - 1) {
            currentUrlIndex++
            playCurrentStation()
        }
    }

    fun previousStation() {
        if (currentUrlIndex > 0) {
            currentUrlIndex--
            playCurrentStation()
        }
    }

    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }

    fun pauseAudio() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }
}
