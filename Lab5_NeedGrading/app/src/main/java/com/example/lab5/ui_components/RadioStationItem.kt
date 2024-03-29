package com.example.lab5.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5.R

@Composable
fun RadioStationItem(
    index: Int,
    stationName: String,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    volumeState: MutableState<Float>, // Updated parameter for volume state
    onVolumeChange: (Float) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painterResource(id = getDrawableResourceForStation(index)),
                    contentDescription = "Radio Station Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = stationName,
                        style = TextStyle(color = Color.White, fontSize = 16.sp),
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.5f))
                            .padding(8.dp)
                    )
                }
            }
            // Control buttons and volume slider
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPreviousClick) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Previous"
                    )
                }
                IconButton(onClick = onPlayPauseClick) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play"
                    )
                }
                IconButton(onClick = onNextClick) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Next"
                    )
                }
                Slider(
                    value = volumeState.value, // Accessing volume state value
                    onValueChange = { newValue ->
                        onVolumeChange(newValue)
                        volumeState.value = newValue // Updating volume state
                    },
                    modifier = Modifier.width(100.dp)
                )
            }
        }
    }
}

// Helper function to map station index to drawable resource
fun getDrawableResourceForStation(index: Int): Int {
    return when (index) {
        0 -> R.drawable.station1
        1 -> R.drawable.station2
        2 -> R.drawable.station3
        3 -> R.drawable.station4
        4 -> R.drawable.station5
        else -> R.drawable.ic_launcher_background // Replace with a default image if needed
    }
}
