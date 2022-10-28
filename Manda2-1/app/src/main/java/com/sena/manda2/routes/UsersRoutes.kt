package com.sena.manda2.routes

import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String,@Field("password")password: String): Call<ResponseHttp>
}