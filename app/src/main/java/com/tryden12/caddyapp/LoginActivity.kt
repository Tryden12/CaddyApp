package com.tryden12.caddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.view.isVisible
import com.tryden12.caddyapp.database.AppDatabase
import com.tryden12.caddyapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var email : String = ""
    private var password : String = ""
    private var isAllowed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /********* Bind the Buttons **************************************************************/
        binding.buttonLogin.setOnClickListener{toMainActivity()}
        binding.buttonSignUp.setOnClickListener {
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Transform password to dots
        binding.editTextPassword.transformationMethod = PasswordTransformationMethod()


    }
    /*** Method for Testing ***********************************/
    fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    /*** Validation & Check User Credentials ******************************************************/
    private fun checkUserCredentials() {
        email = binding.editTextEmail.text.toString().trim()
        password = binding.editTextPassword.text.toString().trim()


        if (email.isEmpty() || password.isEmpty()) {
            binding.textViewWarning.isVisible = true
        } else {
            /*** Coroutine for Checking Credentials ***********************************************/
            CoroutineScope(Dispatchers.IO).launch {
                val userDao = AppDatabase.getDatabase(applicationContext).userDao()

                if (userDao.loginUser(email,password)) {
                    withContext(Dispatchers.Main) {
                        delay(100L)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        binding.textViewWarning.text = getString(R.string.incorrect_field)
                        binding.textViewWarning.isVisible = true
                    }
                }
            }
        }
    }




}