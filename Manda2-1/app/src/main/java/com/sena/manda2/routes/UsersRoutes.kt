package com.sena.manda2.routes

import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>

}