package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme
import com.example.lab5.ui_components.RadioStationItem
import com.example.lab5.view_model.RadioViewModel
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    private val radioViewModel by viewModels<RadioViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenContent(radioViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreenContent(radioViewModel: RadioViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "WELCOME TO KAT'S RADIO COLLECTION",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        val currentPlayingIndex = radioViewModel.currentStationIndex.collectAsState().value

        LazyColumn {
            itemsIndexed(radioViewModel.getStationNames()) { index, stationName ->
                val volumeState = radioViewModel.getVolumeState(index)

                RadioStationItem(
                    index = index,
                    stationName = stationName,
                    isPlaying = currentPlayingIndex == index && radioViewModel.isPlaying,
                    onPlayPauseClick = {
                        // If the current station is playing, pause it; otherwise, play the selected station.
                        if (currentPlayingIndex == index) {
                            radioViewModel.pauseOrResume()
                        } else {
                            radioViewModel.playStation(index)
                        }
                     },
                    onNextClick = { radioViewModel.skipToNextStation() },
                    onPreviousClick = { radioViewModel.skipToPreviousStation() },
                    volumeState = volumeState,
                    onVolumeChange = { newVolume -> radioViewModel.setVolume(newVolume) }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab5Theme {
        MainScreenContent(RadioViewModel())
    }
}
