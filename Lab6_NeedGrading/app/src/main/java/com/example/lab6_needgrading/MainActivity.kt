package com.example.lab6_needgrading


import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6_needgrading.databinding.ActivityMainBinding
import com.example.lab6_needgrading.view_model.AudioViewModel

import com.example.lab6_needgrading.view_model.VideoViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val audioViewModel: AudioViewModel by viewModels()
    private val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioViewModel.initializePlayer(this)
        videoViewModel.initializePlayer(this)

        setupRadioControls()
        setupVideoControls()
    }

    private fun setupRadioControls() {
        binding.playPauseButton.setOnClickListener {
            if (audioViewModel.isPlaying) {
                audioViewModel.pauseAudio()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_play)
            } else {
                audioViewModel.playCurrentStation()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        binding.nextStationButton.setOnClickListener {
            audioViewModel.nextStation()
        }

        binding.prevStationButton.setOnClickListener {
            audioViewModel.previousStation()
        }

        binding.volumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {  // Check if the change was made by the user
                    val volume = progress / 100.0f
                    audioViewModel.setVolume(volume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun setupVideoControls() {
        binding.playVideo.setOnClickListener {
            if (videoViewModel.isPlaying) {
                videoViewModel.pauseVideo()
            } else {
                videoViewModel.playVideo()
            }

            binding.videoPlayerView.player = videoViewModel.player
        }


        binding.nextVideoButton.setOnClickListener {
            videoViewModel.nextVideo()
        }

        binding.prevVideoButton.setOnClickListener {
            videoViewModel.previousVideo()
        }
    }

    override fun onPause() {
        super.onPause()
        audioViewModel.pauseAudio()
        videoViewModel.pauseVideo()
    }

    override fun onStop() {
        super.onStop()
        audioViewModel.releasePlayer()
        videoViewModel.releasePlayer()
    }
}
