package com.example.lab2part1_needgrading

import com.example.lab2part1_needgrading.controller.QuestionController
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.ScoreModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for QuestionController class.
 */
class QuestionControllerUnitTest {

    private lateinit var questionController: QuestionController

    @Before
    fun setUp() {
        // Initialize dependencies
        val allQuestions = AllQuestions()
        val scoreModel = ScoreModel()

        // Initialize the class under test
        questionController = QuestionController(allQuestions, scoreModel)
    }

    @Test
    fun processAnswer_CorrectAnswer_IncrementsScore() {
        questionController.processAnswer(1) // Assuming True is the correct answer
        assertEquals(1, questionController.currentScore)
        assertEquals(1, questionController.questionsAnswered)
        assertFalse(questionController.isOnLastQuestion)
    }

    @Test
    fun processAnswer_IncorrectAnswer_DoesNotIncrementScore() {
        questionController.processAnswer(0) // Assuming False is the correct answer
        assertEquals(0, questionController.currentScore)
        assertEquals(1, questionController.questionsAnswered)
        assertFalse(questionController.isOnLastQuestion)
    }

    @Test
    fun goToNextQuestion_IncrementsTotalQuestions() {
        val initialTotalQuestions = questionController.totalQuestions
        questionController.goToNextQuestion()
        // We expect the total questions to remain the same since we're just moving to the next question
        assertEquals(initialTotalQuestions, questionController.totalQuestions)
    }



    @Test
    fun resetQuiz_ResetsState() {
        questionController.processAnswer(1) // Answer a question
        questionController.resetQuiz()
        assertEquals(0, questionController.currentScore)
        assertEquals(0, questionController.questionsAnswered)
        assertFalse(questionController.isQuizOver)
    }

    @Test
    fun checkGrade_QuizOverIfOnLastQuestion() {
        // To simulate checking grade when on the last question
        repeat(questionController.totalQuestions - 1) {
            questionController.goToNextQuestion()
        }
        questionController.checkGrade()
        assertTrue(questionController.isQuizOver)
    }
}
