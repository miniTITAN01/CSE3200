package com.example.lab2part1_needgrading.model

//QUESTIONS storage/database
data class QuestionModel(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)