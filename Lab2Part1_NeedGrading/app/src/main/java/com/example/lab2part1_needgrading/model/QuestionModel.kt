package com.example.lab2part1_needgrading.model

data class QuestionModel(
    val id: Int,
    val questionText: String,
    // Assuming we keep the options for minimal change, but it will always be ["True", "False"]
    val options: List<String> = listOf("True", "False"),
    val correctAnswerIndex: Int // 0 for False, 1 for True
)
