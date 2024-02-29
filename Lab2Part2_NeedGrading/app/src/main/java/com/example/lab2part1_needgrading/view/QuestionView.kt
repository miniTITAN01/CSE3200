package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab2part1_needgrading.controller.QuestionController

@Composable
fun QuestionView(questionController: QuestionController, navController: NavController) {
    val isQuizOver = questionController.isQuizOver
    val currentScore = questionController.currentScore
    val questionsAnswered = questionController.questionsAnswered
    val totalQuestions = questionController.totalQuestions
    val isOnLastQuestion = questionController.isOnLastQuestion

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top spacer to push content down
            Spacer(modifier = Modifier.weight(1f))
            // Only display the "Questions Answered" counter during the quiz
            if (!isQuizOver) {
                val currentQuestion = questionController.currentQuestion
                if (currentQuestion != null) {
                    Text(
                        text = "Questions Answered: $questionsAnswered",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currentQuestion.questionText,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp)) // Space before options

                    // Options row for True/False
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        currentQuestion.options.forEachIndexed { index, option ->
                            Button(
                                onClick = {
                                    questionController.processAnswer(index)
                                },
                                enabled = !(isOnLastQuestion && questionController.hasLastQuestionBeenAnswered),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                                modifier = Modifier.weight(1f) // Each button takes half the width
                            ) {
                                Text(text = option)
                            }
                        }
                    }


                    if (isOnLastQuestion) {
                        // Display "Check Grade" button when on the last question
                        Button(
                            onClick = {
                                // Logic to display the grade; you might set isQuizOver to true here or navigate to a results screen
                                questionController.isQuizOver = true
                                navController.navigate("scoreView")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Check Grade")
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            // If it's not the last question, simply go to the next question
                            if (!isOnLastQuestion) {
                                questionController.goToNextQuestion()
                            } else {
                                questionController.lastQuestionSkipped = true
                            }
                        },
                        enabled = !(isOnLastQuestion || questionController.lastQuestionSkipped),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}

