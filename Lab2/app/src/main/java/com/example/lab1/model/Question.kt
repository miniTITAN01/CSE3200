package com.example.lab1.model


data class Question <T>{
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty

}