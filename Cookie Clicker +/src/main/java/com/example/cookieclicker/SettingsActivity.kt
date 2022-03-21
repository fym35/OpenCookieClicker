package com.example.cookieclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setupLoadButton()
        setupExitButton()
        setupSaveButton()
    }

    fun setupSaveButton() {
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Toast.makeText(applicationContext, "todo: implement saves (beta 1.3)", Toast.LENGTH_LONG).show()
        }
    }

    fun setupLoadButton() {
        var button = findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            Toast.makeText(applicationContext, "todo: implement saves (beta 1.3)", Toast.LENGTH_LONG).show()
        }
    }

    fun setupExitButton() {
        var exitButton = findViewById<ImageButton>(R.id.exitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}