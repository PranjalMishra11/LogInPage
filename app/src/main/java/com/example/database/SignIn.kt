package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseRefrence: DatabaseReference
    companion object{
        const val Key1= "com.example.database.SignIn.mail"
        const val Key2= "com.example.database.SignIn.name"
        const val Key3= "com.example.database.SignIn.uniqueId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.UserNameEditText)
        val newUser = findViewById<TextView>(R.id.newUser)

        newUser.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val userNameString = userName.text.toString()

            if(userNameString.isNotEmpty()){
                readData(userNameString)
            } else{
                Toast.makeText(this,"Please Enter user name" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userNameString: String) {
        databaseRefrence = FirebaseDatabase.getInstance().getReference("Users")
        databaseRefrence.child(userNameString).get().addOnSuccessListener {

            if(it.exists()){
                val email= it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, WelcomePage::class.java)
                intentWelcome.putExtra(Key1, email.toString())
                intentWelcome.putExtra(Key2, name.toString())
                intentWelcome.putExtra(Key3, userId.toString())
                startActivity(intentWelcome)


            }else{
                Toast.makeText(this,"UserName does not exist",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}