package com.tryden12.caddyapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.tryden12.caddyapp.database.User
import com.tryden12.caddyapp.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityInputBinding

    private var clubDifferential          = 0

    private var yardage                   = 0
    private var lieDifferential           = 0
    private var windDirectionDifferential = 0
    private var windSpeedDifferential     = 0
    private var pathToHole                = ""

    private var clubByYardage             = ""

    private var clubs: Array<String> =
        arrayOf(
            getString(R.string.driver),
            getString(R.string.three_wood),
            getString(R.string.four_iron),
            getString(R.string.five_iron),
            getString(R.string.six_iron),
            getString(R.string.seven_iron),
            getString(R.string.eight_iron),
            getString(R.string.nine_iron),
            getString(R.string.pitching_wedge),
            getString(R.string.sand_wedge),
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFindClub.setOnClickListener { findTheClub() }
        binding.lieTypeRadioGroup.setOnCheckedChangeListener(this)




    }



    override fun onCheckedChanged(radioGroup: RadioGroup?, buttonId: Int) {
        when (buttonId) {
            /*********** Lie Type **********************/
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
            /*********** Wind Direction ******************/
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
        // Get input from user
        yardage               = binding.editTextYardageToHole.text.toString().trim().toInt()
        windSpeedDifferential = binding.editTextWindSpeed.text.toString().trim().toInt()


            /********** Club by Yardage *************************************/
            when (yardage) {
                in 0..70 -> {
                    clubByYardage = clubs[9]
                } in 71..130 -> {
                    clubByYardage = clubs[8]
                } in 131..145 -> {
                    clubByYardage = clubs[7]
                } in 146..160 -> {
                    clubByYardage = clubs[6]
                } in 161..175 -> {
                    clubByYardage = clubs[5]
                } in 176..190 -> {
                    clubByYardage = clubs[4]
                } in 191..205 -> {
                    clubByYardage = clubs[3]
                } in 206..220 -> {
                    clubByYardage = clubs[2]
                } in 221..240 -> {
                    clubByYardage = clubs[1]
                } in 241..600 -> {
                    clubByYardage = clubs[0]
                } else -> {
                    clubByYardage = clubs[0]
                }

            }

            /********** Wind Speed *****************************************/
            when (windSpeedDifferential) {
                in 0..10 -> {
                    windSpeedDifferential = 0
                }
                in 11..20 -> {
                    windSpeedDifferential = 1
                }
                in 21..30 -> {
                    windSpeedDifferential = 2
                }
                in 31..40 -> {
                    windSpeedDifferential = 3
                }
                else -> {
                    windSpeedDifferential = 4
                }
            }

        /********** Total Calculation *****************************************/
        clubDifferential =  lieDifferential +
                           windDirectionDifferential + windSpeedDifferential

        Toast.makeText(applicationContext,
            "(Testing) total club differential = $clubDifferential",
            Toast.LENGTH_SHORT)
            .show()
    }
}