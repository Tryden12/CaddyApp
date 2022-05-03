package com.tryden12.caddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.core.view.isVisible
import com.tryden12.caddyapp.database.User
import com.tryden12.caddyapp.databinding.ActivityCreateUserBinding

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private var email : String = ""
    private var password : String = ""
    private var phoneNumber : String = ""

    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            addUser()
        }

        binding.editTextConfirmPasswordSignup.transformationMethod = PasswordTransformationMethod()
    }
    /*** Method for Testing ***********************************/
    fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun addUser() {
        email               = binding.editTextEmail.text.toString().trim()
        password            = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextConfirmPasswordSignup.text.toString().trim()
        phoneNumber         = binding.editTextPhoneNumSignup.text.toString().trim()


        /********* Validations **************************************************************/
        if (email.isEmpty() || password.isEmpty() ||
            confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
                binding.textViewWarning.text = getString(R.string.all_required)
                binding.textViewWarning.isVisible = true
        }else if (!email.contains("@")) {
            binding.textViewWarning.text = getString(R.string.email_character)
            binding.textViewWarning.isVisible = true
        } else if (password != confirmPassword) {
            binding.textViewWarning.text = getString(R.string.pw_do_not_match)
            binding.textViewWarning.isVisible = true
        } else if (phoneNumber.length != 10) {
            binding.textViewWarning.text = getString(R.string.phone_num_length)
            binding.textViewWarning.isVisible = true
        } else {
            binding.textViewWarning.text = "All clear!"
            binding.textViewWarning.isVisible = true
        }


    }
}