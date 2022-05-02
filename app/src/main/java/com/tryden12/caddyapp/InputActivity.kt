package com.tryden12.caddyapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tryden12.caddyapp.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}