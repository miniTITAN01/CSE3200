package com.example.lab2part1_needgrading

import com.example.lab2part1_needgrading.controller.NextQuestions
import com.example.lab2part1_needgrading.model.AllQuestions
import org.junit.Assert
import org.junit.Test

class NextQuestionTests {
    @Test
    fun getNextIncQuestionNumberTest1(){
        val allQuestions = AllQuestions()
        val currentQuestions = 0

        val nextQuestions = NextQuestions()

        Assert.assertEquals(nextQuestions.getNextIncQuestionNumber(), currentQuestions+1)

    }

    @Test
    fun getNextIncQuestionNumberTest2(){
        val allQuestions = AllQuestions()
        val totalNumberOfQuestions = allQuestions.getNumberOfQuestions()
        val nextQuestions = NextQuestions()

        repeat(totalNumberOfQuestions-1, { nextQuestions.getNextIncQuestionNumber() })

        Assert.assertEquals(nextQuestions.getNextIncQuestionNumber(), 0)

    }

    @Test
    fun getNextRandomQuestionNumberTest1(){
        val allQuestions = AllQuestions()
        val totalNumberOfQuestions = allQuestions.getNumberOfQuestions()
        val nextQuestions = NextQuestions()

        Assert.assertTrue((nextQuestions.getNextRandomQuestionNumber()in (0..totalNumberOfQuestions)))
    }
}