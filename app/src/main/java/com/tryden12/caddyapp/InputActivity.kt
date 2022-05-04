package com.tryden12.caddyapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import com.tryden12.caddyapp.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityInputBinding

    private var clubDifferential = 0

    private var yardageDifferential = 0
    private var lieDifferential = 0
    private var windDifferential = 0
    private var pathToHole = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFindClub.setOnClickListener { findTheClub() }
        binding.lieTypeRadioGroup.setOnCheckedChangeListener(this)
    }



    override fun onCheckedChanged(radioGroup: RadioGroup?, buttonId: Int) {
        when (buttonId) {
            /*********** Lie Type *********************/
            R.id.flat_lie_radioButton -> {
                lieDifferential = 0
            }
            R.id.uphill_lie_radioButton -> {
                lieDifferential = 1
            }
            R.id.downhill_lie_radioButton -> {
                lieDifferential = -1
            }
            R.id.left_lie_radioButton -> {
                lieDifferential = 1
            }
            R.id.right_lie_radioButton -> {
                lieDifferential = 1
            }
            /*********** Wind Direction ****************/
            R.id.front_wind_radioButton -> {
                windDifferential = 1
            } R.id.back_wind_radioButton -> {
                windDifferential = -1
            } R.id.left_wind_radioButton -> {
                windDifferential = 0
            } R.id.right_wind_radioButton -> {
                windDifferential = 0
            }

            /*********** Trees in the Way? ****************/
            R.id.yes_tree_radioButton -> {
                pathToHole = "Punch out with a "
            } R.id.yes_tree_radioButton -> {
                pathToHole = "Hit you shot with a "
            }
        }
    }

    private fun findTheClub() {
        var yardage = binding.editTextYardageToHole.text.toString().trim().toInt()
    }
}