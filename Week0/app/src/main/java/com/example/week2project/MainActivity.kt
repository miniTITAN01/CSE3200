package com.example.week2project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Get the layout inflater
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a layout
        val view = layoutInflater.inflate(R.layout.activity_main, null)

        // Add the inflated layout to the activity
        setContentView(view)

        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener(
            View.OnClickListener {
                Toast.makeText(this,"Good job! You got it", Toast.LENGTH_LONG).show()
            }
        )
    }
}
