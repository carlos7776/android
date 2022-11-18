package com.sena.manda2.Activities.client.update

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.sena.manda2.R
import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import com.sena.manda2.providers.UsersProvider
import com.sena.manda2.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ClientUpdateActivity : AppCompatActivity() {

    var TAG = "ClientUpdateActivity"
    var circleImageUser: CircleImageView? = null
    var editeTextName: EditText? = null
    var editeTextLastname: EditText? = null
    var editeTextPhone: EditText? = null
    var buttonUpdate: Button? = null

    var sharedPref:SharedPref? = null
    var user: User? = null

    private var imageFile: File? = null
    var usersProvider :UsersProvider? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_update)

        sharedPref = SharedPref(this)

        circleImageUser = findViewById(R.id.circleimage_user)
        editeTextName = findViewById(R.id.edittext_name)
        editeTextLastname = findViewById(R.id.edittext_lastname)
        editeTextPhone = findViewById(R.id.edittext_phone)
        buttonUpdate = findViewById(R.id.btn_update)

        getUserFromSession()

        usersProvider = UsersProvider(user?.sessionToken)

        editeTextName?.setText(user?.name)
        editeTextLastname?.setText(user?.lastname)
        editeTextPhone?.setText(user?.phone)

        if (user?.image.isNullOrBlank()){

            Glide.with(this ).load(user?.image).into(circleImageUser!!)
        }

    }



    private fun saveUserInSession(data:String){

        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref?.save("user", user)
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si el usuario existe en session
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)

        }
        circleImageUser?.setOnClickListener { selectImage() }
        buttonUpdate?.setOnClickListener{  updateDate() }

    }
    private fun updateDate(){

        val name =editeTextName?.text.toString()
        val lastname = editeTextLastname?.text.toString()
        val phone = editeTextPhone?.text.toString()

        user?.name = name
        user?.lastname = lastname
        user?.phone = phone


        if (imageFile != null){

            usersProvider?.update(imageFile!!, user!!)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d(TAG,"RESPONSE: $response")
                    Log.d(TAG,"BODY: ${response.body()}")

                    Toast.makeText(this@ClientUpdateActivity,response.body()?.message,Toast.LENGTH_LONG).show()
                    if(response.body()?.isSuccess == true){
                        saveUserInSession(response.body()?.data.toString())
                    }

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG,"Error: ${t.message}")
                    Toast.makeText(this@ClientUpdateActivity,"Error: ${t.message}",Toast.LENGTH_LONG).show()

                }

            })
        }
        else{
            usersProvider?.updateithoutImage( user!!)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d(TAG,"RESPONSE: $response")
                    Log.d(TAG,"BODY: ${response.body()}")

                    Toast.makeText(this@ClientUpdateActivity,response.body()?.message,Toast.LENGTH_LONG).show()

                    if(response.body()?.isSuccess == true){
                        saveUserInSession(response.body()?.data.toString())
                    }



                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG,"Error: ${t.message}")
                    Toast.makeText(this@ClientUpdateActivity,"Error: ${t.message}",Toast.LENGTH_LONG).show()

                }

            })


        }


    }


    private  val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->

            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK){
                val fileUri = data?.data
                imageFile = File(fileUri?.path)
                circleImageUser?.setImageURI(fileUri)
            }
            else if (resultCode == ImagePicker.RESULT_ERROR){
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "No se subio la imagen", Toast.LENGTH_LONG).show()
            }


        }

    private fun selectImage(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080,1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }
    }
}