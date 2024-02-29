package com.example.lab2part1_needgrading.view

import androidx.compose.foundation.background
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizView(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFEB3B)) { // Yellow background
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(32.dp)
        ) {
            // Speech Bubble for "QUIZ"
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 400.dp, height = 200.dp)
                    .clip(RoundedCornerShape(10.dp)) // Rounded corners for speech bubble
                    .background(Color.White) // White background for the bubble
                    .padding(16.dp)
            ) {
                Text(
                    text = "QUIZ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            // Start Button with icon-like graphic
            Button(
                onClick = { navController.navigate("questionView") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // White button
                    contentColor = Color.Black // Black text
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)) // Rounded corners for the button
                    .defaultMinSize(minWidth = 200.dp, minHeight = 65.dp) // Minimum size for the button
            ) {
                // Simulating an icon with text and circles
                Text(
                    text = "⦿ START", // Using a bullet point to simulate the finger-pressing icon
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }
    }
}
