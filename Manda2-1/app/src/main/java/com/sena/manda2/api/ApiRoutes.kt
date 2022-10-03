package com.sena.manda2.api
import com.sena.manda2.models.User
import com.sena.manda2.routes.UsersRoutes
import  retrofit2.Retrofit

class ApiRoutes {
    val API_URL = "http://10.183.145.240:3001/api/"
    val retrofit = RetrofitClient()
    fun getUsersRotes(): UsersRoutes{
        return  retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}

