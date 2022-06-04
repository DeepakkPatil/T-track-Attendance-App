package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
//    private var firebaseUserID: String = ""
    private lateinit var regButton : Button
    lateinit var regEmail : EditText
    lateinit var pass : EditText
    private lateinit var confirmPass : EditText
    private lateinit var phoneNo : EditText
    private lateinit var userName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        regButton = findViewById(R.id.regButton)
        regEmail = findViewById(R.id.regEmail)
        pass = findViewById(R.id.pass)
        confirmPass = findViewById(R.id.confirmPass)
        phoneNo = findViewById(R.id.phonenumber)
        userName = findViewById(R.id.userName)


        regButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val regEmail: String = regEmail.text.toString()
        val pass: String = pass.text.toString()
        val confirmPass: String = confirmPass.text.toString()
        val phoneNo: String=phonenumber.text.toString()
        val userName: String=userName.text.toString()

        if (regEmail.isBlank() || pass.isBlank() || confirmPass.isBlank() || phoneNo.isBlank() || userName.isBlank()) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }

        if (pass != confirmPass) {
            Toast.makeText(this, "Passwords don't match.", Toast.LENGTH_LONG).show()
            return
        } else {

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(userName,regEmail, pass, phoneNo)
            auth.createUserWithEmailAndPassword(regEmail, pass).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

                } else
                {
                    Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
                }
            }
            database.child(phoneNo).setValue(user).addOnSuccessListener {
                Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
                val a = Intent(this, login::class.java)

                startActivity(a)
            }


        }}}