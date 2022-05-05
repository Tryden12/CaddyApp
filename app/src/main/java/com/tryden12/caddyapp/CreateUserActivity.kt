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
import com.tryden12.caddyapp.database.User
import com.tryden12.caddyapp.databinding.ActivityCreateUserBinding
import kotlinx.coroutines.*

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private var email : String = ""
    private var password : String = ""
    private var phoneNumber : String = ""
    private var userID : Long = -1

    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bind the button
        binding.buttonSignUp.setOnClickListener {
            addUser()
        }

        // Transform password to dots
        binding.editTextConfirmPasswordSignup.transformationMethod = PasswordTransformationMethod()
    }



    /*** Method for Testing ***********************************/
    fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    /********* Add User Method ********************************************************************/
    private fun addUser() {
        // Bind edittext to variable
        email               = binding.editTextEmail.text.toString().trim()
        password            = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextConfirmPasswordSignup.text.toString().trim()
        phoneNumber         = binding.editTextPhoneNumSignup.text.toString().trim()




        /********* Validations ********************************************************************/
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
            binding.textViewWarning.isVisible = false




            /*** Coroutine for Adding User ********************************************************/
            CoroutineScope(Dispatchers.IO).launch {
                val userDao = AppDatabase.getDatabase(applicationContext).userDao()

                val user = User(0, email, password, phoneNumber)
                userID = userDao.addUser(user)

                withContext(Dispatchers.Main) {
                    delay(100L)
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }
        }




        /******* Testing email watcher: *********

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequnce: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequnce: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                val userEmail = editable.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val userDao = AppDatabase.getDatabase(applicationContext)
                        .userDao()

                    if (!userDao.is_taken(email)) {
                        isAllowed = false
                        Toast.makeText(applicationContext,
                            getString(R.string.email_taken),
                            Toast.LENGTH_SHORT).show()
                    } else {
                        isAllowed = true
                    }

                }
            }
        }) */




    }
}