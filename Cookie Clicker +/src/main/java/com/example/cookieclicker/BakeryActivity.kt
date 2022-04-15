package gd.rf.konggdev.cookieclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BakeryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bakery_activity)
        setBakeryExitButton()
    }

    fun setBakeryExitButton(){
        var exitButton = findViewById<ImageButton>(R.id.BakeryExitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
