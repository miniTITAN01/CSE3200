package com.example.k2024_04_21_livedata_volley.ui.dashboard


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import coil.load
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.Kat_Karima_LiveData_LiveRadio.models.QuizQuestion
import com.example.k2024_04_21_livedata_volley.R
import com.example.k2024_04_21_livedata_volley.databinding.FragmentDashboardBinding
import com.example.k2024_04_21_livedata_volley.models.JSON_MetMuseum
import com.example.k2024_04_21_livedata_volley.view_models.QuizViewModel
import com.example.k2024_04_21_livedata_volley.view_models.RadioViewModel
import com.example.k2024_04_21_livedata_volley.view_models.UrlViewModel
import com.google.gson.Gson


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel by lazy {
        ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData: JSON_MetMuseum? = null
    private lateinit var volleyQueue: RequestQueue

    private var isMetadataLoaded = false
    private val imageIdsReviewed = mutableSetOf<Int>()
    private val totalImageCount = 5

    private val uriViewModel: UrlViewModel by viewModels()

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Setup Quiz View
        observeViewModel()
        binding.submitAnswerButton.setOnClickListener {
            submitAnswer()
        }

        volleyQueue = Volley.newRequestQueue(requireContext())

        setupListeners()

        return root
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


    }

    private fun observeViewModel() {
        quizViewModel.currentQuestion.observe(viewLifecycleOwner, Observer { quizQuestion ->
            quizQuestion?.let {
                setupQuestion(it)
            }
        })
    }

    private fun setupQuestion(quizQuestion: QuizQuestion) {
        binding.questionTextView.text = quizQuestion.question
        binding.optionsRadioGroup.removeAllViews()
        quizQuestion.options.forEach { option ->
            val radioButton = RadioButton(requireContext()).apply {
                text = option
                textSize = 24f
                layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                )

            }
            binding.optionsRadioGroup.addView(radioButton)
        }
    }

    private fun submitAnswer() {
        val selectedOptionId = binding.optionsRadioGroup.checkedRadioButtonId
        if (selectedOptionId != -1) {
            val radioButton: RadioButton = binding.root.findViewById(selectedOptionId)
            val isCorrect = quizViewModel.checkAnswer(radioButton.text.toString())

            if (isCorrect) {
                // Provide feedback for correct answer
            } else {
                // Provide feedback for incorrect answer
            }

            if (quizViewModel.isLastQuestion()) {
                // Handle the end of the quiz
            } else {
                quizViewModel.moveToNextQuestion()
            }
        } else {
            // Prompt the user to select an answer
        }
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
                isMetadataLoaded = true
            },
            { error -> Log.i("PGB", "Error: ${error}")
                isMetadataLoaded = false}
        )
        volleyQueue.add(jsonObjectRequest)

        // Fetch metadata for the next 4 images
        for (i in 1 until totalImageCount) {
            val nextImageUrl = metPublicDomainUrl + (nextIndex + i).toString()
            val nextJsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, nextImageUrl, null,
                { response ->
                    val nextImageData = gson.fromJson(response.toString(), JSON_MetMuseum::class.java)
                },
                { error -> Log.i("PGB", "Error fetching metadata: ${error}") }
            )
            volleyQueue.add(nextJsonObjectRequest)
        }
    }

    private fun loadNextImage() {
        val imageUrl = uriViewModel.getImageUrl()
        binding.imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.loading_animation)
            error(R.drawable.error)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
