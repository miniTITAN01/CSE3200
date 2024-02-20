package com.example.lab2part1_needgrading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab2part1_needgrading.controller.QuestionController
import com.example.lab2part1_needgrading.controller.ScoreController
import com.example.lab2part1_needgrading.controller.UserController
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.ScoreModel
import com.example.lab2part1_needgrading.model.UserModel
import com.example.lab2part1_needgrading.ui.theme.Lab2Part1_NeedGradingTheme
import com.example.lab2part1_needgrading.view.QuestionView
import com.example.lab2part1_needgrading.view.UserIdentityView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Part1_NeedGradingTheme {
                // Use Surface for theming consistency
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Remember state to control the display of the quiz or the user identity view
                    val showQuiz = remember { mutableStateOf(false) }
                    val userController = remember { UserController(UserModel("", "")) } // Assuming UserModel requires initialization

                    if (!showQuiz.value) {
                        // Show user identity input before starting the quiz
                        UserIdentityView(userController = userController) {
                            // Set to true once the user decides to start the quiz
                            showQuiz.value = true
                        }
                    } else {
                        // Initialize quiz components and show the quiz once the user has entered their identity
                        val allQuestions = AllQuestions()
                        val scoreModel = ScoreModel()
                        val questionController = QuestionController(allQuestions, scoreModel)
                        QuestionView(questionController = questionController)
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
        // Assuming temporary instances for preview purposes
        val userController = UserController(UserModel("", ""))
        UserIdentityView(userController = userController) {
            // Preview does not start the quiz; this lambda is not functional in preview
        }
    }
}
