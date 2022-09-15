package com.sena.manda2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.sena.manda2.R

class RegisterActivity : AppCompatActivity() {

    //define variable
    var volver: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //usar variable

        volver = findViewById(R.id.delvolver)
        volver?.setOnClickListener { MainActivity() }

    }

    private fun MainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}