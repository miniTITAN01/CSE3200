package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.QuestionModel
import com.example.lab2part1_needgrading.model.ScoreModel

class QuestionController(
    private val allQuestions: AllQuestions,
    private val scoreModel: ScoreModel
) {
    private var currentQuestionIndex = 0

    val currentQuestion: QuestionModel?
        get() = if (allQuestions.size > currentQuestionIndex) {
            allQuestions.getQuestionByIndex(currentQuestionIndex)
        } else {
            null // or handle the end of the quiz here
        }

    fun processAnswer(selectedAnswerIndex: Int) {
        val question = currentQuestion
        if (question != null && question.correctAnswerIndex == selectedAnswerIndex) {
            scoreModel.currentScore++
        }
        goToNextQuestion()
    }



    fun goToNextQuestion() {
        if (currentQuestionIndex < allQuestions.size - 1) {
            currentQuestionIndex++
        } else {
            // Handle the end of the quiz here. For now, let's loop back to the start.
            currentQuestionIndex = 0
        }
    }

}
