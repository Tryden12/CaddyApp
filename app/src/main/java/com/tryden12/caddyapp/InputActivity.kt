package com.tryden12.caddyapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.tryden12.caddyapp.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityInputBinding

    private var clubDifferential          = 0

    private var yardageDifferential       = 0
    private var lieDifferential           = 0
    private var windDirectionDifferential = 0
    private var windSpeedDifferential     = 0
    private var pathToHole                = ""




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
                windDirectionDifferential = 1
            } R.id.back_wind_radioButton -> {
                windDirectionDifferential = -1
            } R.id.left_wind_radioButton -> {
                windDirectionDifferential = 0
            } R.id.right_wind_radioButton -> {
                windDirectionDifferential = 0
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
        yardageDifferential   = binding.editTextYardageToHole.text.toString().trim().toInt()
        windSpeedDifferential = binding.editTextWindSpeed.text.toString().trim().toInt()

        if (windSpeedDifferential in 0..10) {
            windSpeedDifferential = 0
        } else if (windSpeedDifferential in 11..20) {
            windSpeedDifferential = 1
        } else if (windSpeedDifferential in 21..30) {
            windSpeedDifferential = 2
        } else if (windSpeedDifferential in 31..40) {
            windSpeedDifferential = 3
        } else {
            windSpeedDifferential = 4
        }

        clubDifferential =  lieDifferential +
                           windDirectionDifferential + windSpeedDifferential

        Toast.makeText(applicationContext,
            "(Testing) total club differential = $clubDifferential",
            Toast.LENGTH_SHORT)
            .show()
    }
}