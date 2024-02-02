package com.example.week2project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

const val ON_CREATE = "ON__CREATE"
const val ON_START = "ON__START"
const val ON_RESUME = "ON__RESUME"
const val ON_PAUSE = "ON__PAUSE"
const val ON_STOP = "ON__STOP"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(ON_CREATE, "onCreate called")
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
