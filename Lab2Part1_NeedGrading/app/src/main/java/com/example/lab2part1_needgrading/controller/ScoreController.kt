package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.ScoreModel

class ScoreController(private val scoreModel: ScoreModel) {
    fun incrementScore() {
        scoreModel.currentScore++
    }

    fun resetScore() {
        scoreModel.currentScore = 0
    }

    val currentScore: Int
        get() = scoreModel.currentScore
}
