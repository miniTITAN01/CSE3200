package com.example.lab2part1_needgrading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab2part1_needgrading.ui.theme.Lab2Part1_NeedGradingTheme
import com.example.lab2part1_needgrading.view.QuizView
import com.example.lab2part1_needgrading.view.UserIdentityView
import com.example.lab2part1_needgrading.controller.UserController
import com.example.lab2part1_needgrading.model.UserModel
import com.example.lab2part1_needgrading.view.QuestionView
import com.example.lab2part1_needgrading.controller.QuestionController
import com.example.lab2part1_needgrading.controller.ScoreController
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.ScoreModel
import com.example.lab2part1_needgrading.view.ScoreView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Part1_NeedGradingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val allQuestions = remember { AllQuestions() } // Initialize your questions list
                    val scoreModel = remember { ScoreModel() }
                    val scoreController = remember { ScoreController(scoreModel) } // Pass the total number of questions dynamically

                    NavHost(navController = navController, startDestination = "userIdentityView") {
                        composable("userIdentityView") {
                            val userController = remember { UserController(UserModel("", "")) }
                            UserIdentityView(userController = userController, navController = navController)
                        }
                        composable("quizView") {
                            QuizView(navController = navController)
                        }
                        composable("questionView") {
                            QuestionView(questionController = QuestionController(allQuestions, scoreModel), navController = navController)
                        }
                        composable("scoreView") {
                            ScoreView(
                                currentScore = scoreController.currentScore,
                                totalQuestions = allQuestions.size,
                                questionsAnswered = scoreController.questionsAnswered,
                                scoreController = scoreController, // Pass ScoreController
                                navController = navController // Pass NavController
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Part1_NeedGradingTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "userIdentityView") {
            composable("userIdentityView") {
                val userController = UserController(UserModel("", ""))
                UserIdentityView(userController = userController, navController = navController)
            }
            // Add previews for other views if necessary
        }
    }
}
