package com.sena.manda2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.sena.manda2.R

class MainActivity : AppCompatActivity() {

    var imageViewGoToRegister: ImageView? = null
    var buttonViewGoToLogin: Button? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)
        imageViewGoToRegister?.setOnClickListener { goToRegister() }


        buttonViewGoToLogin= findViewById(R.id.inicio_sesion)
        buttonViewGoToLogin?.setOnClickListener {Pantalla1()}

    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
    }

    private fun Pantalla1() {
        val i = Intent(this, Pantalla1::class.java)
        startActivity(i)
    }

}