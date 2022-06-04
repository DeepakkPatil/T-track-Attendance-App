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
import kotlinx.android.synthetic.main.activity_register_stud.*

class registerStud : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var regButton : Button
    lateinit var regEmail : EditText
    private lateinit var rollnostud : EditText
    private lateinit var phoneNo : EditText
    private lateinit var userName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_stud)

        auth = FirebaseAuth.getInstance()

        regButton = findViewById(R.id.regButton)
        regEmail = findViewById(R.id.regEmail)
        rollnostud = findViewById(R.id.rollnostud)
        phoneNo = findViewById(R.id.phonenumber)
        userName = findViewById(R.id.userName)


        regButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val classy = intent.getStringExtra("classy")
        val regEmail: String = regEmail.text.toString()
        val rollnostud: String=rollnostud.text.toString()
        val phoneNo: String=phoneNo.text.toString()
        val count="0"
        val userName: String=userName.text.toString()

        if (regEmail.isBlank() || rollnostud.isBlank() || phoneNo.isBlank() || userName.isBlank()) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }


            database = FirebaseDatabase.getInstance().getReference(classy.toString())
            val attendance = attendance(userName,regEmail, rollnostud, phoneNo,count)

            database.child(rollnostud).setValue(attendance).addOnSuccessListener {
                Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
                val a = Intent(this, MainActivity2::class.java)
                startActivity(a)
            }


        }}