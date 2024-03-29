package com.example.lab5.ui_components

import android.content.Context
import android.media.AudioManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.lab5.R

@Composable
fun VolumeControl() {
    Log.d("VolumeControl", "VolumeControl composable is being run")
    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    var currentVolume by remember { mutableStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) }
    var isMuted by remember { mutableStateOf(currentVolume == 0) }

    Column(modifier = Modifier.background(Color.Gray)) {
        Row {
            IconButton(onClick = {
                isMuted = !isMuted
                currentVolume = if (isMuted) 0 else audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0)
            }) {
                Icon(
                    painter = painterResource(if (isMuted) R.drawable.volume_off else R.drawable.volume_up),
                    contentDescription = if (isMuted) "Unmute" else "Mute"
                )
            }
            Slider(
                value = currentVolume.toFloat(),
                onValueChange = { volume ->
                    currentVolume = volume.toInt()
                    isMuted = volume == 0f
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0)
                },
                valueRange = 0f..maxVolume.toFloat(),
                onValueChangeFinished = {
                    // Persist the volume if necessary
                }
            )
        }
    }
}