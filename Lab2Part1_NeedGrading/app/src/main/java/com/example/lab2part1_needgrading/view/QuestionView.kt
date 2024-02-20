package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab2part1_needgrading.controller.QuestionController

@Composable
fun QuestionView(questionController: QuestionController) {
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
            // Only display the "Questions Answered" counter during the quiz
            if (!isQuizOver) {
                val currentQuestion = questionController.currentQuestion
                if (currentQuestion != null) {
                    Text(
                        text = "Questions Answered: $questionsAnswered",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = currentQuestion.questionText,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    currentQuestion.options.forEachIndexed { index, option ->
                        Button(
                            onClick = {
                                questionController.processAnswer(index)
                            },
                            enabled = !(isOnLastQuestion && questionsAnswered == totalQuestions),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(text = option)
                        }
                    }
                    if (isOnLastQuestion) {
                        // Display "Check Grade" button when on the last question
                        Button(
                            onClick = {
                                // Logic to display the grade; you might set isQuizOver to true here or navigate to a results screen
                                questionController.isQuizOver = true
                            },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Check Grade")
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { questionController.goToNextQuestion() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Next")
                    }
                }
            } else {

                // Display both score and "Questions Answered" at the end of the quiz
                Text(
                    text = "Quiz Over!",
                    style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 32.dp, bottom = 8.dp)
                )

                Text(
                    text = "Your final score:",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "$currentScore / $totalQuestions",
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp, bottom = 32.dp)
                )

                Text(
                    text = "Total Questions Answered: $questionsAnswered",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
                )
                // Restart Quiz button
                Button(
                    onClick = { questionController.resetQuiz() },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
                ) {
                    Text("Restart Quiz")
                }
            }
        }
    }
}

