package com.tryden12.caddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tryden12.caddyapp.databinding.ActivityCreateUserBinding

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}