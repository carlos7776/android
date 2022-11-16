package com.sena.manda2.Activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.sena.manda2.Activities.MainActivity
import com.sena.manda2.R
import com.sena.manda2.fragments.client.ClientCategoriesFragment
import com.sena.manda2.fragments.client.ClientOrdersFragment
import com.sena.manda2.fragments.client.ClientProfileFragment
import com.sena.manda2.models.User
import com.sena.manda2.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"
    //var buttonLogout: Button? = null
    var sharedPref:SharedPref? = null

    var bootomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        sharedPref = SharedPref(this)
       // buttonLogout = findViewById(R.id.btn_logout)
        //buttonLogout?.setOnClickListener{logout()}

        bootomNavigation = findViewById(R.id.botton_navigation)
        bootomNavigation?.setOnItemSelectedListener {

            when (it.itemId){

                R.id.item_home -> {
                    openFrangment(ClientCategoriesFragment())
                    true
                }
                R.id.item_orders -> {
                    openFrangment(ClientOrdersFragment())
                    true
                }
                R.id.item_profile -> {
                    openFrangment(ClientProfileFragment())
                    true
                }
                else -> false
            }


        }

        getUserFromSession()
    }

    private fun openFrangment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si el usuario existe en session
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }

    }

}