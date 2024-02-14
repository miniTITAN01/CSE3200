package com.example.lab2part1_needgrading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab2part1_needgrading.controller.NextQuestions
import com.example.lab2part1_needgrading.model.AllQuestions
import com.example.lab2part1_needgrading.ui.theme.Lab2Part1_NeedGradingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Part1_NeedGradingTheme {
                Column {
                    Greeting("CSE3200 CLASS")
                    Text("Welcome to our Quiz app")
                    QuizComponent()
                }
            }
        }
    }
}
@Composable
fun QuizComponent(){
    val allQuestions = AllQuestions()
    val  nextQuestions= NextQuestions()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var questionNumber by remember{
            mutableStateOf(0)
        }
        var question by remember{
            mutableStateOf(allQuestions.getQuestion(questionNumber).questionText)
        }

        Text(question)
        Row{
            Button(onClick = { question = "True" }) {
                Text ("True Button")
            }
            Button(onClick = { question = "False" }) {
                Text ("False Button")
            }
            Button(onClick = {
                questionNumber = nextQuestions.getNextIncQuestionNumber()
                question = allQuestions.getQuestion(questionNumber).questionText
            }) {
                Text("Next Question")

            }
            
        }
    }
    
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab2Part1_NeedGradingTheme {
        Greeting("Android")
    }
}