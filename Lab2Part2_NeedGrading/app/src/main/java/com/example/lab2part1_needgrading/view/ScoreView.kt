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

import com.example.lab2part1_needgrading.controller.ScoreController

@Composable
fun ScoreView(
    currentScore: Int,
    totalQuestions: Int,
    questionsAnswered: Int,
    scoreController: ScoreController,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center content vertically
        ) {

            Text(
                text = "CONGRATULATIONS!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Box(
                modifier = Modifier
                    .background(Color.White, MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Score: $currentScore / $totalQuestions",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }

            Text(
                text = "Total Questions Answered: $questionsAnswered",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("quizView") {
                        popUpTo("quizView") { inclusive = true }
                    }
                    scoreController.resetScore() // Reset the quiz state
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "⦿ Restart Quiz",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )

            }

        }
    }
}
