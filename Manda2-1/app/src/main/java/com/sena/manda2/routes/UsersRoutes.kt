package com.sena.manda2.routes

import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String,@Field("password")password: String): Call<ResponseHttp>

    @Multipart
    @PUT("users/update")
    fun update(
        @Part image: MultipartBody.Part,
        @Part("user")user:RequestBody
    ):Call<ResponseHttp>


}