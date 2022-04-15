package gd.rf.konggdev.cookieclicker

import android.graphics.Color
import java.io.Serializable
import kotlin.math.pow
import android.os.Handler
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout

class CookieData : Serializable {
    var cookiesCounter: Long = 0
    var clickValue: Long = 1
    var cookiesPerSecond: Long = 0
    var clickUpgradeLevel: Long = 1
    var autoClickerUpgradeLevel: Long = 0
    var workersUpgradeLevel: Long = 0
    var bakeriesUpgradeLevel: Long = 0
    private val clickUpgradeStartPrice: Long = 20
    private val priceIncreaseCoef: Double = 2.5
    private val increaseUpgradeValue: Double = 2.0
    private val autoClickerPriceCoef: Double = 5.0 / 4.0
    private val autoclickValue = 1
    private val workerValue = 4
    private val bakeriesValue = 10
    var autoClickerPrice = 500
    var workersPrice = 2000
    var bakeriesPrice = 5000
    var BombGadgetActive = false
    var KeyGadgetActive = false
    private var NewBakery = false
    private val BakeryBonusCoef = 6

    fun getKeyPrice(): Long {
        var upgValue = 90000
        return upgValue.toLong()
    }

    fun getBombPrice(): Long {
        var upgValue = 70000
        return upgValue.toLong()
    }


    override fun toString(): String {
        var buffer = "{ cookiesCounter: $cookiesCounter, "
        buffer += "clickValue: $clickValue, "
        buffer += "cookiesPerSecond $cookiesPerSecond, "
        buffer += "clickUpgradeLevel: $clickUpgradeLevel, "
        buffer += "autoClickerUpgradeLevel: $autoClickerUpgradeLevel }"
        return buffer
    }

    fun getClickUpgPrice(): Long {
        var upgradeValue =
            clickUpgradeStartPrice * priceIncreaseCoef.pow((clickUpgradeLevel - 1).toDouble())
        return upgradeValue.toLong()
    }

    fun calculateClickValue() {
        clickValue *= 2
    }
        fun updateAutoClicker() {
            cookiesCounter -= autoClickerPrice
            var tmp = autoClickerPrice.toDouble()
            tmp *= autoClickerPriceCoef
            autoClickerPrice = tmp.toInt()
            autoClickerUpgradeLevel++
            cookiesPerSecond += autoclickValue
        }

        fun updateWorkers() {
            cookiesCounter -= workersPrice
            var tmp = workersPrice.toDouble()
            tmp *= autoClickerPriceCoef
            workersPrice = tmp.toInt()
            workersUpgradeLevel++
            cookiesPerSecond += workerValue
        }

        fun updateBakeries() {
            cookiesCounter -= bakeriesPrice
            var tmp = bakeriesPrice.toDouble()
            tmp *= autoClickerPriceCoef
            bakeriesPrice = tmp.toInt()
            bakeriesUpgradeLevel++
            cookiesPerSecond += bakeriesValue
            NewBakery = true
        }

        fun startBakeryBonus(handler: Handler, button: ImageButton, background: ConstraintLayout) {
            if (NewBakery) {
                cookiesPerSecond *= BakeryBonusCoef
                clickValue *= BakeryBonusCoef
                button.setImageResource(R.drawable.goldencookie)
                background.setBackgroundColor(Color.rgb(233, 154, 36))
                var bonusTimer = Runnable {
                    cookiesPerSecond /= BakeryBonusCoef
                    clickValue /= BakeryBonusCoef
                    button.setImageResource(R.drawable.cookie)
                    background.setBackgroundColor(Color.rgb( 34, 31, 27))
                }
                handler.postDelayed(bonusTimer, 30000)
            }
            NewBakery = false
        }

}
