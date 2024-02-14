package com.example.lab2part1_needgrading.model

class AllQuestions {
    private val allQuestions = arrayListOf<Question<Boolean>>(
        Question<Boolean>("My name is Kat", true, Difficulty.EASY),
        Question<Boolean>("CSE is taught by Prof. Bradford", true, Difficulty.MEDIUM),
        Question<Boolean>("Kriti is married", true, Difficulty.HARD)
    )
    fun getNumberOfQuestions():Int{
        return allQuestions.size
    }

    fun getQuestion(i: Int): Question<Boolean>{
        return allQuestions[i]
    }

}