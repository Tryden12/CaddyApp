package com.tryden12.caddyapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
    private var index                     = 0

    private val clubs = mutableListOf<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFindClub.setOnClickListener { findTheClub() }
        binding.lieTypeRadioGroup.setOnCheckedChangeListener(this)


        clubs.add(getString(R.string.driver))
        clubs.add(getString(R.string.three_wood))
        clubs.add(getString(R.string.four_iron))
        clubs.add(getString(R.string.five_iron))
        clubs.add(getString(R.string.six_iron))
        clubs.add(getString(R.string.seven_iron))
        clubs.add(getString(R.string.eight_iron))
        clubs.add(getString(R.string.nine_iron))
        clubs.add(getString(R.string.pitching_wedge))
        clubs.add(getString(R.string.sand_wedge))


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
            R.id.no_tree_radioButton -> {
                pathToHole = getString(R.string.punch_out)
            } R.id.yes_tree_radioButton -> {
                pathToHole = getString(R.string.use_this_club)
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun findTheClub() {
        // Get input from user
        yardage               = binding.editTextYardageToHole.text.toString().trim().toInt()
        windSpeedDifferential = binding.editTextWindSpeed.text.toString().trim().toInt()


            /********** Club by Yardage *************************************/
            when (yardage) {
                in 0..70 -> {
                    clubByYardage = clubs[9] // sand wedge
                } in 71..130 -> {
                    clubByYardage = clubs[8] // pitching wedge
                } in 131..145 -> {
                    clubByYardage = clubs[7] // 9iron
                } in 146..160 -> {
                    clubByYardage = clubs[6] // 8iron
                } in 161..175 -> {
                    clubByYardage = clubs[5] // 7iron
                } in 176..190 -> {
                    clubByYardage = clubs[4] // 6iron
                } in 191..205 -> {
                    clubByYardage = clubs[3] // 5iron
                } in 206..220 -> {
                    clubByYardage = clubs[2] // 4iron
                } in 221..240 -> {
                    clubByYardage = clubs[1] // 3wood
                } in 241..600 -> {
                    clubByYardage = clubs[0] // Driver
                } else -> {
                    clubByYardage = clubs[0] // Driver
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

        /********** Total Differential Calculation ************************************************/
        clubDifferential =  lieDifferential + windDirectionDifferential + windSpeedDifferential


        /********** Change Club Choice Based on Differential **************************************/
        var clubByYardageIndex = 0
        if (clubs.contains(clubByYardage)) {
            //  Get index of club from list
            clubByYardageIndex = clubs.indexOf(clubByYardage).toString().toInt()

                // If total = in bounds of list
                if (clubByYardageIndex + clubDifferential in 0..9) {
                    clubByYardage = clubs[clubByYardageIndex + clubDifferential]
                }

                // In case the total goes out of bounds:
                else {
                    clubByYardage = clubs[clubByYardageIndex]
                }

        }


        /********** Display Results ***************************************************************/
        if (pathToHole == getString(R.string.punch_out)) {
            val builder = AlertDialog.Builder(this)
            builder
                .setMessage(getString(R.string.punch_out) + clubByYardage)
                .setPositiveButton(android.R.string.ok, null)
            builder.show()
        } else {

            val inflater: LayoutInflater = this.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.custom_alert_dialog, null)

            val message = dialogView.findViewById<TextView>(R.id.text_dialog)
            message.text = clubByYardage

            //val btn = dialogView.findViewById<TextView>(R.id.btn_dialog)
            //btn.setOnClickListener({ dialogBuilder.setOnDismissListener(this) })

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder
                .setView(dialogView)

                .setNegativeButton(android.R.string.ok, null)
            dialogBuilder.show()
            dialogBuilder.setView(dialogView)
        }

        /********** Toast Test *******************************************************************
        if (pathToHole == getString(R.string.punch_out)) {
        val builder = AlertDialog.Builder(this)
        builder
        .setMessage(getString(R.string.punch_out) + clubByYardage)
        .setPositiveButton(android.R.string.ok, null)
        builder.show()
        } else {
        val builder = AlertDialog.Builder(this)
        builder
        .setMessage(clubByYardage)
        .setPositiveButton(android.R.string.ok, null)
        builder.show()
        }
         */
    }




}