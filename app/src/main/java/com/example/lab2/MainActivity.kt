package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab2.ui.theme.Lab2Theme

val myNameList: List<String> = listOf("Kat", "Su", "Arianna", "Karima")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val currentIndex = remember { mutableStateOf(0) }

    Column {
        Greeting(index = currentIndex.value)
        Button(
            onClick = {
                currentIndex.value = (currentIndex.value + 1) % myNameList.size
            }
        ) {
            Text("Select")
        }
        CounterText()
    }
}

@Composable
fun CounterText() {
    val myIndex = remember {
        mutableStateOf(0)
    }

    Button(onClick = { myIndex.value += 1 }) {
        Text(text = "Current Count = ${myIndex.value}")
    }
}

@Composable
fun Greeting(index: Int, modifier: Modifier = Modifier) {
    Text(
        text = myNameList[index],
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab2Theme {
        Greeting(0)
    }
}
