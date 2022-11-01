package com.sena.manda2.adapters

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.manda2.R
import com.sena.manda2.models.Rol
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sena.manda2.Activities.client.home.ClientHomeActivity
import com.sena.manda2.Activities.delivery.home.DeliveryHomeActivity
import com.sena.manda2.Activities.restaurant.home.RestaurantHomeActivity
import com.sena.manda2.utils.SharedPref

class RolesAdapter(val context:Activity,val roles: ArrayList<Rol>):RecyclerView.Adapter<RolesAdapter.RolesViewHolder>(){

    val sharedpref = SharedPref(context)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_roles,parent,false)
        return RolesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    override fun onBindViewHolder(holder: RolesViewHolder, position: Int) {

        val rol = roles[position] // CADA ROL

        holder.textViewRol.text = rol.name
        Glide.with(context).load(rol.image).into(holder.imageViewRol)

        holder.itemView.setOnClickListener{goToRol(rol)}
    }
    private fun goToRol(rol: Rol){
        if (rol.name == "RESTAURANTE"){
            sharedpref.save("rol", "RESTAURANTE")
            val i = Intent(context, RestaurantHomeActivity::class.java)
            context.startActivity(i)
        }
        else if (rol.name == "CLIENTE"){
            sharedpref.save("rol", "CLIENTE")
            val i = Intent(context, ClientHomeActivity::class.java)
            context.startActivity(i)
        }
        else if (rol.name == "REPARTIDOR"){
            sharedpref.save("rol", "REPARTIDOR")
            val i = Intent(context, DeliveryHomeActivity::class.java)
            context.startActivity(i)
        }
    }

    class RolesViewHolder(view:View):RecyclerView.ViewHolder(view){

        val textViewRol: TextView
        val imageViewRol: ImageView

        init {
            textViewRol = view.findViewById(R.id.textview_rol)
            imageViewRol = view.findViewById(R.id.imageview_rol)
        }


    }

}