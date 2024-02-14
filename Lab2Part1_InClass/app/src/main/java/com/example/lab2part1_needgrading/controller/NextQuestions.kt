package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.AllQuestions

class NextQuestions {
    private val totalQuestions = AllQuestions().getNumberOfQuestions()
    private var currentQuestion = 0

    fun getNextIncQuestionNumber(): Int{

        currentQuestion = (currentQuestion+1) % totalQuestions
        return currentQuestion
    }

    fun getNextRandomQuestionNumber(): Int{
        val getRand = ( 0 .. totalQuestions).random()
        return getRand
    }
}