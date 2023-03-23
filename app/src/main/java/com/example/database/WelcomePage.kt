package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val name= intent.getStringExtra(SignIn.Key2)
        val mail= intent.getStringExtra(SignIn.Key1)
        val userId = intent.getStringExtra(SignIn.Key3)

        val welcomeText= findViewById<TextView>(R.id.tvWelcome)
        welcomeText.text= "Welcome $name"

        val mailText= findViewById<TextView>(R.id.tvMail)
        mailText.text="Mail : $mail"

        val idText= findViewById<TextView>(R.id.tvUnique)
        idText.text="UniqueID : $userId"


    }
}