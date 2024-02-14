package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2part1_needgrading.controller.QuestionController

@Composable
fun QuestionView(questionController: QuestionController) {
    val currentQuestion = questionController.currentQuestion

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (currentQuestion != null) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentQuestion.questionText,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                currentQuestion.options.forEachIndexed { index, option ->
                    Button(
                        onClick = { questionController.processAnswer(index) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = option)
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("No question available", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
