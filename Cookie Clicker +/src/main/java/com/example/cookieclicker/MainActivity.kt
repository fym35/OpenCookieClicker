package gd.rf.konggdev.cookieclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    var cookieData = CookieData()
    val upgradeRequestCode = 10
    val handler = Handler()
    lateinit var timer: Runnable
    var enableGadgets = true
    val secondInMillis = 1000L
    var gTimeout = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickButton()
        setupUpgradeButton()
        setupGadgetsButton()
        setupBakeries()
        initTimer()
        setupBombGadget()
        setupKeyGadget()
        setupSettingsButton()
    }

    fun setupKeyGadget() {
        val button = findViewById<Button>(R.id.BombGadget)
        button.setOnClickListener {
            if(cookieData.KeyGadgetActive && enableGadgets){
                var kgrnd =  (0..5).random()
                cookieData.cookiesPerSecond += kgrnd
                var kgrnd2 = (0..10).random()
                cookieData.clickUpgradeLevel += kgrnd2
                Toast.makeText(applicationContext, "Upgraded by ${kgrnd}\n autoclick and ${kgrnd2}\n click upgrade", Toast.LENGTH_LONG).show()
            }else if(cookieData.KeyGadgetActive && enableGadgets == false){
                Toast.makeText(applicationContext, "This gadget is on timeout!", Toast.LENGTH_LONG).show()
            }else{

            }
        }
    }

    fun setupBombGadget() {
        val button = findViewById<Button>(R.id.BombGadget)
        button.setOnClickListener {
            if(cookieData.BombGadgetActive && enableGadgets){
                var rnd =  (80..6000).random()
                cookieData.cookiesCounter += rnd
                enableGadgets = false
            }else if(cookieData.BombGadgetActive){
                Toast.makeText(applicationContext, "This gadget is on timeout!", Toast.LENGTH_LONG).show()
            }else{

            }
        }
    }


    fun setupClickButton() {
        val button = findViewById<ImageButton>(R.id.cookieButton)
        button.setOnClickListener {
            cookieData.cookiesCounter += cookieData.clickValue
            refreshCookieView()
        }
    }

    fun setupUpgradeButton() {
        val button = findViewById<Button>(R.id.upgradeButton)
        button.setOnClickListener {
            val intent = Intent(this, UpgradesActivity::class.java)
            intent.putExtra("cookieData", cookieData)
            startActivityForResult(intent, upgradeRequestCode)
        }
    }

    fun setupGadgetsButton() {
        val button = findViewById<Button>(R.id.upgradeButton3)
        button.setOnClickListener {
            val intent = Intent(this, GadgetsActivity::class.java)
            startActivity(intent)
        }
    }

    fun setupBakeries(){
        val button = findViewById<Button>(R.id.upgradeButton4)
        button.setOnClickListener {
            val intent = Intent(this, BakeryActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == upgradeRequestCode && resultCode == Activity.RESULT_OK) {
            cookieData = data!!.getSerializableExtra("cookieData") as CookieData
            refreshCookieView()
            var button = findViewById<ImageButton>(R.id.cookieButton)
            var background = findViewById<ConstraintLayout>(R.id.background)
            cookieData.startBakeryBonus(handler , button, background)
            refreshGadgets()
        }
    }



    fun setupSettingsButton(){
        var button = findViewById<Button>(R.id.settingsButton)
        button.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    fun initTimer() {
        timer = Runnable {
            gTimeout += 1
                if(gTimeout > 80)
                {
                    enableGadgets = true
                    gTimeout = 0
                }
                if(cookieData.cookiesCounter > 8000)
                {
                   var rnds = (0..52).random()
                   if(rnds == 30 || rnds == 31 || rnds == 32 || rnds == 33 || rnds == 34 || rnds == 35 || rnds == 36 || rnds == 37 || rnds == 38 || rnds == 39 || rnds == 40)
                   {
                       refreshCookieView()
                       var button = findViewById<ImageButton>(R.id.cookieButton)
                       var background = findViewById<ConstraintLayout>(R.id.background)
                       cookieData.startBakeryBonus(handler , button, background)
                   }
                }
                cookieData.cookiesCounter += cookieData.cookiesPerSecond
                refreshCookieView()
                handler.postDelayed(timer, secondInMillis)
        }
        handler.postDelayed(timer, secondInMillis)
    }


    fun refreshCookieView() {
        val cookiesTextView: TextView = findViewById(R.id.cookieTextArea)
        cookiesTextView.text = "🍪Cookies : ${cookieData.cookiesCounter}"
    }

    fun refreshGadgets() {
        if(cookieData.BombGadgetActive)
        {
            val button = findViewById<Button>(R.id.BombGadget)
            button.visibility = View.VISIBLE
        }else{
            val button = findViewById<Button>(R.id.BombGadget)
            button.visibility = View.INVISIBLE
        }
        if(cookieData.KeyGadgetActive)
        {
            val button = findViewById<Button>(R.id.keyGadget)
            button.visibility = View.VISIBLE
        }else{
            val button = findViewById<Button>(R.id.keyGadget)
            button.visibility = View.INVISIBLE
        }


    }

}


