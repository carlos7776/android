package com.sena.manda2.fragments.client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.sena.manda2.Activities.MainActivity
import com.sena.manda2.Activities.SelectRolesActivity
import com.sena.manda2.Activities.client.update.ClientUpdateActivity
import com.sena.manda2.R
import com.sena.manda2.models.User
import com.sena.manda2.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView


class ClientProfileFragment : Fragment() {

    var myView: View? = null
    var buttonSelectRol: Button? = null
    var buttonUpdateProfile: Button? = null
    var circleImageUser: CircleImageView?= null
    var textViewName: TextView? = null
    var textViewEmail: TextView? = null
    var textViewPhone: TextView? = null
    var imageviewlogout : ImageView? = null

    var sharedPref: SharedPref? = null
    var user: User? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_profile, container, false)

        sharedPref = SharedPref(requireActivity())

        buttonSelectRol = myView?.findViewById(R.id.btn_select_rol)
        buttonUpdateProfile = myView?.findViewById(R.id.btn_update_profile)
        textViewName =  myView?.findViewById(R.id.textview_name)
        textViewEmail =  myView?.findViewById(R.id.textview_email)
        textViewPhone =  myView?.findViewById(R.id.textview_phone)
        circleImageUser =  myView?.findViewById(R.id.circleimage_user)
        imageviewlogout =  myView?.findViewById(R.id.imageview_logout)


        buttonSelectRol?.setOnClickListener { goToSelectRol() }
        imageviewlogout?.setOnClickListener { logout() }
        buttonUpdateProfile?.setOnClickListener { goToUpdate()  }

        getUserFromSession()

        textViewName?.text = "${user?.name} ${user?.lastname}"
        textViewEmail?.text = user?.email
        textViewPhone?.text = user?.phone

        if(!user?.image.isNullOrBlank()) {
            Glide.with(requireContext()).load(user?.image).into(circleImageUser!!)
        }


        return myView
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(requireContext(), MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si el usuario existe en session
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)

        }

    }

    private fun goToUpdate(){
        val i = Intent(requireContext(), ClientUpdateActivity::class.java)
        startActivity(i)
    }

    private fun goToSelectRol() {
        val i = Intent(requireContext(), SelectRolesActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Eliminar el historial de pantallas
        startActivity(i)
    }

}