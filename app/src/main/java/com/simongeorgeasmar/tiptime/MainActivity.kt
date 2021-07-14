package com.simongeorgeasmar.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simongeorgeasmar.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {

        val stringInTextField = binding.costOfService.text.toString()
       //turned it to toDoubleOrNull() instead of just toDouble() so it doesn't crash if there is no input
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null){
            //This will clear the tipResult
            binding.tipResult.text = ""
            return
        }

        val selectedID = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedID) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        //this formats the tip into the way the currency of the user is usually formatted because different currencies have different formats
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}