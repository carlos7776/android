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
import com.sena.manda2.models.Category
import com.sena.manda2.utils.SharedPref

class CategoriesAdapter(val context:Activity, val categories: ArrayList<Category>):RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    val sharedpref = SharedPref(context)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_categories,parent,false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder:CategoriesViewHolder,position: Int) {

        val category = categories[position] // Devuelve CADA una de las categorias

        holder.textViewCategory.text = category.name
        Glide.with(context).load(category.image).into(holder.imageVieCategory)

       // holder.itemView.setOnClickListener{goToRol(rol)}
    }
    //private fun goToRol(rol: Rol){
      //      val i = Intent(context, RestaurantHomeActivity::class.java)
        //    context.startActivity(i)
        //}
    class CategoriesViewHolder(view:View):RecyclerView.ViewHolder(view){

        val textViewCategory: TextView
        val imageVieCategory: ImageView

        init {
            textViewCategory = view.findViewById(R.id.textview_category)
            imageVieCategory = view.findViewById(R.id.imageview_category)
        }


    }

}