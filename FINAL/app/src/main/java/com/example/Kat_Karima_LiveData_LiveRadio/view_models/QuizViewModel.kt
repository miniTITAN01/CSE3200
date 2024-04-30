package com.example.k2024_04_21_livedata_volley.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.Kat_Karima_LiveData_LiveRadio.models.QuizQuestion

class QuizViewModel : ViewModel() {
    private val questions = listOf(
        QuizQuestion("1. Who is the artist of this piece?", listOf("Pierre Stephan", "Alexandra Mo", "Karima Hamanda", "J Reagan"), "Artist B"),
        QuizQuestion("2. What is the name of this artwork?", listOf("Figure of Admiral Samuel Hood", "Norwich Stone Ware", "Ale Area", "Adrion"), "Artwork C"),
        QuizQuestion("3. Which era does this artwork belong to?", listOf("Renaissance", "Baroque", "Neoclassicism", "Impressionism"), "Impressionism"),
        // Add more QuizQuestion instances for each image...
    )

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex

    val currentQuestion: LiveData<QuizQuestion> = _currentQuestionIndex.map { index ->
        questions[index]
    }

    fun isLastQuestion(): Boolean = _currentQuestionIndex.value ?: 0 >= questions.size - 1

    fun moveToNextQuestion() {
        if (!isLastQuestion()) {
            _currentQuestionIndex.value = (_currentQuestionIndex.value ?: 0) + 1
        }
    }

    fun checkAnswer(selectedAnswer: String): Boolean {
        return selectedAnswer == currentQuestion.value?.correctAnswer
    }
}
