package com.example.k2024_04_21_livedata_volley

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.k2024_04_21_livedata_volley.databinding.ActivityMainBinding
import com.example.k2024_04_21_livedata_volley.models.JSON_MetMuseum
import com.example.k2024_04_21_livedata_volley.view_models.UrlViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData : JSON_MetMuseum? = null
    private lateinit var volleyQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyQueue = Volley.newRequestQueue(this)
        val uriViewModel: UrlViewModel by viewModels()

        binding.loadImageMetaDataButton.setOnClickListener {
            loadImageMetadata(uriViewModel)
        }

        binding.nextImageButton.setOnClickListener {
            loadNextImage(uriViewModel)
        }
    }

    private fun loadImageMetadata(uriViewModel: UrlViewModel) {
        val nextIndex = uriViewModel.nextImageNumber()
        val metUrl = metPublicDomainUrl + nextIndex.toString()
        uriViewModel.setMetaDataUrl(metUrl)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, uriViewModel.getMetaDataUrl(), null,
            { response ->
                imageData = gson.fromJson(response.toString(), JSON_MetMuseum::class.java)
                uriViewModel.setImageUrl(imageData?.primaryImage ?: "Foobar")
                updateUIWithMetadata(imageData)
            },
            { error -> Log.i("PGB", "Error: ${error}") }
        )
        volleyQueue.add(jsonObjectRequest)
    }

    private fun updateUIWithMetadata(metadata: JSON_MetMuseum?) {
        binding.tvImageTitle.text = metadata?.title ?: "Unknown Title"
        binding.tvImageDescription.text = metadata?.artistDisplayName ?: "Unknown Artist"
    }


    private fun loadNextImage(uriViewModel: UrlViewModel) {
        val imageUrl = uriViewModel.getImageUrl()
        binding.imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.loading_animation)
            error(R.drawable.error)
        }
    }
}