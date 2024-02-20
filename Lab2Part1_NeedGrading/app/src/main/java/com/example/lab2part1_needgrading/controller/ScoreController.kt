package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.ScoreModel

class ScoreController(private val scoreModel: ScoreModel) {

    val currentScore: Int
        get() = scoreModel.currentScore

    val questionsAnswered: Int
        get() = scoreModel.questionsAnswered

    fun correctAnswer() {
        scoreModel.incrementScore()
    }

    fun answerQuestion() {
        scoreModel.questionAnswered()
    }

    fun resetScore() {
        scoreModel.reset()
    }
}
