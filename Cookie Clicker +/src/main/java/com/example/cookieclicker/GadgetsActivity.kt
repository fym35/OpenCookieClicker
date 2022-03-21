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

class GadgetsActivity : AppCompatActivity(){
    var cookieData = CookieData()
    lateinit var bombCostTextView: TextView
    lateinit var clickerKeyCostTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gadgets_activity)
        setGadgetsExitButton()
        setBuyBombButton()
        refreshTextViews()
        setBuyButtonClickerKey()
        bombCostTextView = findViewById(R.id.bombprice)
        clickerKeyCostTextView = findViewById(R.id.clickerkeyprice)
    }

    fun refreshTextViews(){
        if(cookieData.BombGadgetActive)
        {
            bombCostTextView.text = "Already purchased"
        }
        if(cookieData.KeyGadgetActive)
        {
            clickerKeyCostTextView.text = "Already purchased"
        }
    }
    fun setGadgetsExitButton(){
        var exitButton = findViewById<ImageButton>(R.id.GadgetsExitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    fun setBuyBombButton(){
        var button = findViewById<Button>(R.id.BombButton)
        button.setOnClickListener {
            if(cookieData.BombGadgetActive == false){
                if(cookieData.cookiesCounter >= 70000) {
                        cookieData.BombGadgetActive = true
                        cookieData.cookiesCounter -= 70000
                        refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setBuyButtonClickerKey(){
        var button = findViewById<Button>(R.id.KeyGadgetBuy)
        button.setOnClickListener {
            if(cookieData.KeyGadgetActive == false) {
                if (cookieData.cookiesCounter >= 90000) {
                    cookieData.BombGadgetActive = true
                    cookieData.cookiesCounter -= 90000
                    refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }


}