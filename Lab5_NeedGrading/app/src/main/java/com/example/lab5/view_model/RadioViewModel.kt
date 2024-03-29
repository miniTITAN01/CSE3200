package com.example.lab5.view_model

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lab5.models.RadioStations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RadioViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null
    val radioStations = RadioStations()
    private val _currentStationIndex = MutableStateFlow(0) // Backing property for current station index
    var currentStationIndex = _currentStationIndex.asStateFlow()

    private val _volumeStates = mutableStateListOf<MutableState<Float>>()

    init {
        // Initialize volume states for each station
        repeat(radioStations.urls.size) {
            _volumeStates.add(mutableStateOf(0.5f)) // Default volume for each station
        }
        initializeMediaPlayer()
    }

    private val _isPlaying = mutableStateOf(false)
    var isPlaying: Boolean by _isPlaying
        private set

    init {
        initializeMediaPlayer()
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            // Listener setup
            setOnPreparedListener {
                val currentVolume = _volumeStates[_currentStationIndex.value].value
                setVolume(currentVolume)
                _isPlaying.value = true
                start()
            }
            setOnErrorListener { _, what, extra ->
                Log.e("RadioViewModel", "Error occurred: What: $what, Extra: $extra")
                true
            }
            setOnCompletionListener {
                Log.d("RadioViewModel", "Playback completed")
                // Handle completion, like auto-playing the next station
            }
        }
    }

    fun getStationNames(): List<String> = radioStations.urls.mapIndexed { index, _ -> "Station ${index + 1}" }

    fun playStation(index: Int) {
        mediaPlayer?.apply {
            if (_currentStationIndex.value != index || !isPlaying) {
                try {
                    reset()
                    _currentStationIndex.value = index
                    setDataSource(radioStations.urls[index])
                    prepareAsync()
                } catch (e: Exception) {
                    Log.e("RadioViewModel", "Exception setting data source", e)
                }
            } else {
                // Toggle play/pause if the same station is selected
                pauseOrResume()
            }
        }
    }

    fun pauseOrResume() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying = false
            } else {
                it.start()
                isPlaying = true
            }
        }
    }

    fun skipToNextStation() {
        val nextIndex = (_currentStationIndex.value + 1) % radioStations.urls.size
        playStation(nextIndex)
    }

    fun skipToPreviousStation() {
        val prevIndex = if (_currentStationIndex.value - 1 < 0) radioStations.urls.lastIndex else _currentStationIndex.value - 1
        playStation(prevIndex)
    }

    fun setVolume(volume: Float) {
        _volumeStates[_currentStationIndex.value].value = volume
        mediaPlayer?.setVolume(volume, volume)
    }
    fun getVolumeState(index: Int): MutableState<Float> {
        return _volumeStates[index]
    }


    override fun onCleared() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onCleared()
    }
}
