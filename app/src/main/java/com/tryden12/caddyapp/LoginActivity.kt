package com.tryden12.caddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.tryden12.caddyapp.database.AppDatabase
import com.tryden12.caddyapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var email : String = ""
    private var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /********* Bind the Buttons **************************************************************/
        binding.buttonLogin.setOnClickListener{checkUserCredentials()}
        binding.buttonSignUp.setOnClickListener {
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }

    private fun checkUserCredentials() : Boolean {
        email = binding.editTextEmail.text.toString().trim()
        password = binding.editTextPassword.text.toString().trim()

        val errorMsg = ""

        if (email.isEmpty() || password.isEmpty()) {
            binding.textViewWarning.text = "Email or Password cannot be empty!"
            binding.textViewWarning.isVisible = true
        }

/*
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
                .userDao()
            val emailCheck = db.getUser(email, password)


        }
*/
        return true

    }




}