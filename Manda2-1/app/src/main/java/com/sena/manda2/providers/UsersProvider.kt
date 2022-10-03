package com.sena.manda2.providers

import com.sena.manda2.api.ApiRoutes
import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import com.sena.manda2.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {

    private  var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRotes()
    }

    fun register(user: User): Call<ResponseHttp>?{
        return  usersRoutes?.register(user)
    }

}