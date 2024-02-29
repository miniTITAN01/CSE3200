package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab2part1_needgrading.controller.UserController
import java.time.LocalTime

@Composable
fun UserIdentityView(
    userController: UserController,
    navController: NavController, // Add NavController as a parameter
    //onStartQuiz: () -> Unit
) {
    var userEmail by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFEB3B)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Dynamic greeting based on time of day
            val greetingText = when (LocalTime.now().hour) {
                in 0..11 -> "Good morning"
                in 12..16 -> "Good afternoon"
                in 17..23 -> "Good evening"
                else -> "It's late! Hello There "
            }
            Text(greetingText, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))


            // User Email Input
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // User ID Input
            OutlinedTextField(
                value = userId,
                onValueChange = { userId = it },
                label = { Text("User ID") },
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Text("Remember me")
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = {/* Implement forgot ID logic */ }) {
                    Text("Forgot ID?")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign in Button
            Button(
                onClick = {
                    userController.setUserName(userEmail)
                    userController.setUserId(userId)
                    navController.navigate("QuizView") // Navigate to QuizView
                },
                enabled = userEmail.isNotBlank() && userId.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .fillMaxWidth() // Make button fill the width
                    .height(45.dp) // Increase the height for a larger touch target
                    .padding(horizontal = 16.dp) // Apply horizontal padding
            ) {

                Text("Sign in",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), // Increase text size and make it bold
                )
            }
        }
    }
}
