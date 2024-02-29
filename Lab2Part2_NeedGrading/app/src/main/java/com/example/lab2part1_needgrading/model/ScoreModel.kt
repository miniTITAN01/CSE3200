package com.example.lab2part1_needgrading.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ScoreModel {
    // Use mutableStateOf for observable state that Compose can react to
    private var _currentScore = mutableStateOf(0)
    val currentScore: Int by _currentScore // Delegated property to expose the current score

    private var _questionsAnswered = mutableStateOf(0)
    val questionsAnswered: Int by _questionsAnswered // Delegated property for questions answered

    fun incrementScore() {
        _currentScore.value++ // Increment the backing value
    }

    fun questionAnswered() {
        _questionsAnswered.value++ // Increment the backing value
    }

    fun reset() {
        _currentScore.value = 0 // Reset the score
        _questionsAnswered.value = 0 // Reset the questions answered counter
    }
}
