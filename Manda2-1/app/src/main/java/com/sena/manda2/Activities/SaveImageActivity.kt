package com.sena.manda2.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.sena.manda2.Activities.client.home.ClientHomeActivity
import com.sena.manda2.R
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class SaveImageActivity : AppCompatActivity() {

    var circleImageUser: CircleImageView? = null
    var buttonNext: Button? = null
    var buttonConfirm: Button? = null

    private var ImageFile: File? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)

        circleImageUser = findViewById(R.id.circleimage_user)
        buttonNext = findViewById(R.id.btn_netx)
        buttonConfirm = findViewById(R.id.btn_confirm)

        circleImageUser?.setOnClickListener{ selectImage()}


        buttonNext?.setOnClickListener{ goToClientHome() }
        buttonConfirm?.setOnClickListener{ }
    }
    private fun goToClientHome(){
        val i = Intent(this, ClientHomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private  val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result:ActivityResult ->

            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK){
                val fileUri = data?.data
                ImageFile = File(fileUri?.path)
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