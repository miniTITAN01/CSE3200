package com.example.k2024_04_21_livedata_volley.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.android.volley.toolbox.Volley
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.k2024_04_21_livedata_volley.R
import com.example.k2024_04_21_livedata_volley.databinding.FragmentHomeBinding
import com.example.k2024_04_21_livedata_volley.models.JSON_MetMuseum
import com.example.k2024_04_21_livedata_volley.view_models.RadioViewModel
import com.example.k2024_04_21_livedata_volley.view_models.UrlViewModel
import com.google.gson.Gson


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData: JSON_MetMuseum? = null
    private lateinit var volleyQueue: RequestQueue

    private var isMetadataLoaded = false

    private val imageIdsReviewed = mutableSetOf<Int>()
    private val totalImageCount = 15

    private val radioViewModel: RadioViewModel by viewModels()
    private val uriViewModel: UrlViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        volleyQueue = Volley.newRequestQueue(requireContext())
        radioViewModel.initializePlayer(requireContext())

        setupListeners()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() {
        binding.loadImageMetaDataButton.setOnClickListener {
            loadImageMetadata()
        }

        binding.nextImageButton.setOnClickListener {
            if (isMetadataLoaded) {
                loadNextImage()
            } else {
                // Show error image or handle the case where metadata is not loaded
                binding.imageView.load(R.drawable.error)
            }
        }

        binding.checkKnowledgeButton.setOnClickListener {
            navigateToDashboard()

        }

        setupRadioControls()
    }

    private fun loadImageMetadata() {
        val nextIndex = uriViewModel.nextImageNumber()
        val metUrl = metPublicDomainUrl + nextIndex.toString()
        uriViewModel.setMetaDataUrl(metUrl)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, uriViewModel.getMetaDataUrl(), null,
            { response ->
                imageData = gson.fromJson(response.toString(), JSON_MetMuseum::class.java)
                uriViewModel.setImageUrl(imageData?.primaryImage ?: "Foobar")
                updateUIWithMetadata(imageData)

                imageData?.objectID?.let { this.onImageReviewed(it) }
                isMetadataLoaded = true
            },
            { error -> Log.i("PGB", "Error: ${error}")
                isMetadataLoaded = false}

        )
        volleyQueue.add(jsonObjectRequest)
    }

    private fun updateUIWithMetadata(metadata: JSON_MetMuseum?) {
        binding.tvImageTitle.text = metadata?.title ?: "Unknown Title"
        binding.tvImageDescription.text = metadata?.artistDisplayName ?: "Unknown Artist"
        binding.tvCulture.text = "Culture: ${metadata?.culture ?: "Unknown"}"
        binding.tvRegion.text = "Region: ${metadata?.region ?: "Unknown"}"

    }

    private fun loadNextImage() {
        val imageUrl = uriViewModel.getImageUrl()
        binding.imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.loading_animation)
            error(R.drawable.error)
        }
    }

    private fun setupRadioControls() {
        binding.playPauseButton.setOnClickListener {
            if (radioViewModel.isPlaying) {
                radioViewModel.pauseAudio()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_play)
            } else {
                // Move the playCurrentStation call here
                radioViewModel.playCurrentStation()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        binding.nextStationButton.setOnClickListener {
            radioViewModel.nextStation()
        }

        binding.prevStationButton.setOnClickListener {
            radioViewModel.previousStation()
        }

        binding.volumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val volume = progress / 100.0f
                    radioViewModel.setVolume(volume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun onImageReviewed(imageId: Int) {
        imageIdsReviewed.add(imageId)
        if (imageIdsReviewed.size >= totalImageCount) {
            binding.checkKnowledgeButton.visibility = View.VISIBLE
        }
    }
    fun navigateToDashboard() {
        val navController = findNavController()
        navController.navigate(R.id.navigation_dashboard)
    }




}
