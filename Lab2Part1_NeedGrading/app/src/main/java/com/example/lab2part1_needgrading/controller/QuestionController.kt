package com.example.lab2part1_needgrading.controller

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.QuestionModel
import com.example.lab2part1_needgrading.model.ScoreModel

class QuestionController(
    private val allQuestions: AllQuestions,
    private val scoreModel: ScoreModel
) {
    private var currentQuestionIndex by mutableStateOf(0)
    var isQuizOver by mutableStateOf(false)
    val currentScore: Int
        get() = scoreModel.currentScore

    val questionsAnswered: Int
        get() = scoreModel.questionsAnswered
    val totalQuestions: Int
        get() = allQuestions.size
    val isOnLastQuestion: Boolean
        get() = currentQuestionIndex == allQuestions.size - 1

    val currentQuestion: QuestionModel?
        get() = if (allQuestions.size > currentQuestionIndex) {
            allQuestions.getQuestionByIndex(currentQuestionIndex)
        } else {
            null
        }

    fun processAnswer(selectedAnswerIndex: Int) {
        // Prevent further processing if the quiz is over or the last question has been answered
        if (isQuizOver || (isOnLastQuestion && questionsAnswered >= totalQuestions)) {
            return
        }

        currentQuestion?.let {
            scoreModel.questionAnswered()
            if (it.correctAnswerIndex == selectedAnswerIndex) {
                scoreModel.incrementScore()
            }
        }

        if (!isOnLastQuestion) {
            goToNextQuestion()
        } else {
            // Potentially set a flag or take action indicating readiness for "Check Grade"
        }
    }

    fun goToNextQuestion() {
        if (currentQuestionIndex < allQuestions.size - 1) {
            currentQuestionIndex++
        }
    }

    fun resetQuiz() {
        currentQuestionIndex = 0
        isQuizOver = false
        scoreModel.reset()
    }

    // Call this method when "Check Grade" button is clicked
    fun checkGrade() {
        if (isOnLastQuestion) {
            isQuizOver = true
        }
    }
}
