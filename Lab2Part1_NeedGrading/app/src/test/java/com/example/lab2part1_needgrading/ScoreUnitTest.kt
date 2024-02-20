package com.example.lab2part1_needgrading

import com.example.lab2part1_needgrading.controller.ScoreController
import com.example.lab2part1_needgrading.model.ScoreModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ScoreControllerUnitTest {

    private lateinit var scoreController: ScoreController
    private lateinit var scoreModel: ScoreModel

    @Before
    fun setUp() {
        scoreModel = ScoreModel()
        scoreController = ScoreController(scoreModel)
    }

    @Test
    fun correctAnswer_IncrementsScore() {
        scoreController.correctAnswer()
        assertEquals(1, scoreController.currentScore)
    }

    @Test
    fun answerQuestion_IncrementsQuestionsAnswered() {
        scoreController.answerQuestion()
        assertEquals(1, scoreController.questionsAnswered)
    }

    @Test
    fun resetScore_ResetsCurrentScoreAndQuestionsAnswered() {
        scoreController.correctAnswer()
        scoreController.answerQuestion()
        scoreController.resetScore()
        assertEquals(0, scoreController.currentScore)
        assertEquals(0, scoreController.questionsAnswered)
    }
}

class ScoreModelUnitTest {

    private lateinit var scoreModel: ScoreModel

    @Before
    fun setUp() {
        scoreModel = ScoreModel()
    }

    @Test
    fun incrementScore_IncrementsCurrentScore() {
        scoreModel.incrementScore()
        assertEquals(1, scoreModel.currentScore)
    }

    @Test
    fun questionAnswered_IncrementsQuestionsAnswered() {
        scoreModel.questionAnswered()
        assertEquals(1, scoreModel.questionsAnswered)
    }

    @Test
    fun reset_ResetsCurrentScoreAndQuestionsAnswered() {
        scoreModel.incrementScore()
        scoreModel.questionAnswered()
        scoreModel.reset()
        assertEquals(0, scoreModel.currentScore)
        assertEquals(0, scoreModel.questionsAnswered)
    }
}
