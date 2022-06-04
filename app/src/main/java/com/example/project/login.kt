package com.example.project
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var signup : Button
    lateinit var loginbtn : Button

    lateinit var emaillogin : EditText
    lateinit var Password : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val username = intent.getStringExtra("classy")
        signup = findViewById(R.id.signup)
        loginbtn = findViewById(R.id.loginbtn)
        emaillogin= findViewById(R.id.emaillogin)
        Password= findViewById(R.id.password)

        signup.setOnClickListener{
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        auth= FirebaseAuth.getInstance()

        loginbtn.setOnClickListener{
            loginUser()
        }
        signup.setText(username)
    }



    private fun loginUser() {
        val userEmail : String = emaillogin.text.toString()
        val userPassword : String = Password.text.toString()

        if (userEmail.isBlank() || userPassword.isBlank())
        {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener { task->
                    if (task.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"Login Successfull ",Toast.LENGTH_SHORT).show()
                        val i= Intent(this, classes::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                    }
                    else{
                        Toast.makeText(this, "Error:"+task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }
    }


}