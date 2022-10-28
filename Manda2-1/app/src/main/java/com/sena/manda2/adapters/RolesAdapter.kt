package com.sena.manda2.adapters

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.manda2.R
import com.sena.manda2.models.Rol
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.ViewGroup

class RolesAdapter(val context:Activity,val roles: ArrayList<Rol>):RecyclerView.Adapter<RolesAdapter.RolesViewHolder>(){


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