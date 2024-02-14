package com.example.lab2part1_needgrading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab2part1_needgrading.controller.QuestionController
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.model.ScoreModel
import com.example.lab2part1_needgrading.ui.theme.Lab2Part1_NeedGradingTheme
import com.example.lab2part1_needgrading.view.QuestionView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Part1_NeedGradingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize the MVC components
                    val allQuestions = AllQuestions()
                    val scoreModel = ScoreModel()
                    val questionController = QuestionController(allQuestions, scoreModel)

                    // Now we pass the questionController to our QuestionView
                    QuestionView(questionController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Part1_NeedGradingTheme {
        // In the preview, we need to create temporary instances for the preview to work
        val allQuestions = AllQuestions()
        val scoreModel = ScoreModel()
        val questionController = QuestionController(allQuestions, scoreModel)

        QuestionView(questionController)
    }
}
