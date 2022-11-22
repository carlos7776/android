package com.sena.manda2.routes

import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface CategoriesRoutes {


    @Multipart
    @POST("categories/create")
    fun create(
        @Part image: MultipartBody.Part,
        @Part("category")category:RequestBody,
        @Header("Authorization") token:String
    ):Call<ResponseHttp>



}