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

class UpgradesActivity : AppCompatActivity(){
    lateinit var cookieData : CookieData
    lateinit var autoClickerTextView: TextView
    lateinit var workersTextView: TextView
    lateinit var bakeriesTextView: TextView
    lateinit var upgradeClickTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upgrade_activity)
        cookieData = intent.getSerializableExtra("cookieData") as CookieData
        Log.i("App", cookieData.toString())
        autoClickerTextView = findViewById(R.id.autoClickerText)
        workersTextView = findViewById(R.id.workersText)
        bakeriesTextView = findViewById(R.id.bakeriesText)
        upgradeClickTextView = findViewById(R.id.clickUpgradeText)
        refreshTextViews()
        setExitButton()
        initClickUpgradeButton()
        handleAutoClickerUpgrade()
    }
    fun refreshTextViews(){
        autoClickerTextView.text = "Cursors: ${cookieData.autoClickerUpgradeLevel}\n" + "Upgrade cost: ${cookieData.autoClickerPrice}"
        workersTextView.text = "Workers: ${cookieData.workersUpgradeLevel}\nWorker cost: ${cookieData.workersPrice}"
        bakeriesTextView.text = "Bakeries: ${cookieData.bakeriesUpgradeLevel}\nBakery cost: ${cookieData.bakeriesPrice}"
        upgradeClickTextView.text = "Click Upgrade: ${cookieData.clickUpgradeLevel}\nUpgrade cost: ${cookieData.getClickUpgPrice()}"
    }

    fun setExitButton(){
        var exitButton = findViewById<ImageButton>(R.id.exitButton)
        exitButton.setOnClickListener {
            var returnIntent = Intent()
            returnIntent.putExtra("cookieData", cookieData)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    fun initClickUpgradeButton(){
        val clickUpgradeButton = findViewById<Button>(R.id.clickUpgradeButton)
        clickUpgradeButton.setOnClickListener{
            if( cookieData.cookiesCounter>= cookieData.getClickUpgPrice()){
                cookieData.cookiesCounter -= cookieData.getClickUpgPrice()
                cookieData.clickUpgradeLevel += 1
                cookieData.calculateClickValue()
                refreshTextViews()
            }else{
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun handleAutoClickerUpgrade() {
        findViewById<Button>(R.id.autoClickerButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.autoClickerPrice) {
                cookieData.updateAutoClicker()
                refreshTextViews()
            } else {
                Toast.makeText(
                    applicationContext,
                    "You cannot buy this upgrade!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        findViewById<Button>(R.id.workersButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.workersPrice) {
                cookieData.updateWorkers()
                refreshTextViews()
            } else {
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.bakeriesButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.bakeriesPrice) {
                cookieData.updateBakeries()
                refreshTextViews()
            } else {
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }
    }

}
