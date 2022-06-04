
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

class MainActivity2 : AppCompatActivity() {


    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val classy = intent.getStringExtra("classy")
        val regButton: Button = findViewById(R.id.regstudent)
        val mrkatd: Button = findViewById(R.id.mrkatd)
        val showatd: Button = findViewById(R.id.showattendance)
        val rlno: EditText = findViewById(R.id.rlno)


        regButton.setOnClickListener {
            val a = Intent(this, registerStud::class.java)
            a.putExtra("classy", classy)
            startActivity(a)
        }
        mrkatd.setOnClickListener {

            val rollnn: String = rlno.text.toString()
            if( rollnn.isEmpty()) {
                Toast.makeText(this, "Enter Roll No First", Toast.LENGTH_SHORT).show()
            }
            else{


                database = FirebaseDatabase.getInstance().getReference(classy.toString())
                database.child(rollnn).get().addOnSuccessListener {

                    if (it.exists()) {
//
//                    val userName:String,val regEmail:String, val rollnostud:String,val phoneNo:String ,val count:String

                        var username = it.child("userName").value.toString()
                        var email = it.child("regEmail").value.toString()
                        var mobile = it.child("phoneNo").value.toString()
                        var rolln = it.child("rollnostud").value.toString()


                        var count = it.child("count").value.toString().toInt()

                        count = count + 1
//                    total=count * money


                        database = FirebaseDatabase.getInstance().getReference(classy.toString())
                        val attendance = attendance(username, email, rolln, mobile, count.toString())
                        database.child(rolln).setValue(attendance).addOnSuccessListener {


                            Toast.makeText(this, "Attendance Marked", Toast.LENGTH_SHORT).show()


                        }.addOnFailureListener {

                            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()

                        }


                    } else {

                        Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "Register user First", Toast.LENGTH_SHORT).show()



                    }

                }.addOnFailureListener {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


                }

            }
        }
//            database.child(cname).setValue(attendence).addOnSuccessListener {
//                Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
//                val a = Intent(this, home::class.java)
//                startActivity(a)
//
//
//            }
        showatd.setOnClickListener {
            var i = Intent(this, UserlistActivity::class.java)
            i.putExtra("classy",classy)
            startActivity(i)
            finish()


        }

    }
}


