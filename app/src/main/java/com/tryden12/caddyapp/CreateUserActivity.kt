package com.tryden12.caddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addUser() {

    }
}