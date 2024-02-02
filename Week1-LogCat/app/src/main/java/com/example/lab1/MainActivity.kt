package com.example.lab1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab1.ui.theme.Lab1Theme

const val ON_CREATE = "ON__CREATE"
const val ON_START = "ON__START"
const val ON_RESUME = "ON__RESUME"
const val ON_PAUSE = "ON__PAUSE"
const val ON_STOP = "ON__STOP"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(ON_CREATE, "onCreate called")
        setContent{
            Lab1Content()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(ON_START, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(ON_RESUME, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(ON_PAUSE, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(ON_STOP, "onStop called")
    }

}


@Composable
fun Lab1Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Lab 1 of CSE3200",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Here is my demonstration",
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1Theme {
        Lab1Content()
    }
}