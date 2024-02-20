package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lab2part1_needgrading.controller.UserController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun UserIdentityView(userController: UserController, onStartQuiz: () -> Unit) {
    var userName by remember { mutableStateOf("") } // State for userName input
    var userId by remember { mutableStateOf("") } // State for userId input

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // User Name Input
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Enter Your Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp)) // Spacing between text fields

        // User ID Input
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("Enter Your ID") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Start Quiz Button
        Button(
            onClick = {
                userController.setUserName(userName) // Update the userName in UserController
                userController.setUserId(userId) // Update the userId in UserController
                onStartQuiz() // Proceed to start the quiz
            },
            enabled = userName.isNotBlank() && userId.isNotBlank(), // Enable the button when both fields are not blank
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Start Quiz")
        }
    }
}
