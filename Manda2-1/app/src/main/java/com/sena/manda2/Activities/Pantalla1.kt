package com.sena.manda2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.sena.manda2.R

class Pantalla1 : AppCompatActivity() {

    var volver: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla1)

        volver = findViewById(R.id.delvolver)
        volver?.setOnClickListener { MainActivity() }

    }

    private fun MainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}