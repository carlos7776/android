package com.sena.manda2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.sena.manda2.Activities.client.home.ClientHomeActivity
import com.sena.manda2.R
import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import com.sena.manda2.providers.UsersProvider
import com.sena.manda2.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"
    //define variable
    var volver: ImageView? = null
    var editTextName: EditText? = null
    var editTextLastname: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPhone: EditText? = null
    var editTextPassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var buttonRegister:Button? = null

    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //usar variable

        volver = findViewById(R.id.delvolver)
        editTextName =findViewById(R.id.edittext_name)
        editTextLastname =findViewById(R.id.edittext_lastname)
        editTextEmail =findViewById(R.id.edittext_email)
        editTextPhone =findViewById(R.id.edittext_phone)
        editTextConfirmPassword =findViewById(R.id.edittext_confirm_password)
        editTextPassword =findViewById(R.id.edittext_password)
        buttonRegister=findViewById(R.id.btn_register)

        volver?.setOnClickListener { MainActivity() }
        buttonRegister?.setOnClickListener{register()}

    }

    private  fun  register(){
        val name = editTextName?.text.toString()
        val lastname = editTextLastname?.text.toString()
        val email = editTextEmail?.text.toString()
        val phone = editTextPhone?.text.toString()
        val password = editTextPassword?.text.toString()
        val confirmPassword = editTextConfirmPassword?.text.toString()

        if(isValidForm( name = name, phone = phone, lastname = lastname, email = email, password = password, confirmPassword = confirmPassword)){

            val user = User(
                name = name,
                lastname = lastname,
                email = email,
                phone = phone,
                password = password
            )

            usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp>{
                override fun onResponse(
                    call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    if (response.body()?.isSuccess == true){
                        saveUserInSession(response.body()?.data.toString())
                        goToClientHome()
                    }

                    Toast.makeText(this@RegisterActivity, response.body()?.message, Toast.LENGTH_LONG).show()

                    Log.d (TAG, "response: ${response} ")
                    Log.d (TAG, "Body: ${response.body()} ")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {

                    Log.d(TAG,"SE PRODUJO UN ERROR  ${t.message}")
                    Toast.makeText(this@RegisterActivity, "SE PRODUJO UN ERROR  ${t.message}", Toast.LENGTH_LONG).show()
                }

            })

        }

    }

    private fun goToClientHome(){
        val i = Intent(this, SaveImageActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun saveUserInSession(data:String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    fun String.isEmailValid():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
    private fun isValidForm(
        name :String,
        lastname:String,
        email:String,
        phone:String,
        confirmPassword:String,
        password:String
    ):Boolean{
        if(name.isBlank()){
            Toast.makeText(this,"Debes ingresar nombre",Toast.LENGTH_SHORT).show()
            return false
        }
        if(lastname.isBlank()){
            Toast.makeText(this,"Debes ingresar apellido",Toast.LENGTH_SHORT).show()
            return false
        }
        if(phone.isBlank()){
            Toast.makeText(this,"Debes ingresar celular",Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.isBlank()){
            Toast.makeText(this,"Debes ingresar email",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isBlank()){
            Toast.makeText(this,"Debes ingresar contraseña",Toast.LENGTH_SHORT).show()
            return false
        }
        if(confirmPassword.isBlank()){
            Toast.makeText(this,"Debes ingresar la confirmacion de contraseña",Toast.LENGTH_SHORT).show()
            return false
        }
        if(!email.isEmailValid()){
            Toast.makeText(this,"El email no es valido",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != confirmPassword){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun MainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}