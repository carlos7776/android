package com.sena.manda2.api
import com.sena.manda2.models.User
import com.sena.manda2.routes.CategoriesRoutes
import com.sena.manda2.routes.UsersRoutes
import  retrofit2.Retrofit

class ApiRoutes {

    val API_URL = "http://192.168.0.24:3001/api/"
    val retrofit = RetrofitClient()

    fun getUsersRotes(): UsersRoutes{
        return  retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
    fun getUsersRotesWithToken(token:String): UsersRoutes{
        return  retrofit.getClientWithToken(API_URL,token).create(UsersRoutes::class.java)
    }
    fun getCategoriesRotes(token:String): CategoriesRoutes{
        return  retrofit.getClientWithToken(API_URL,token).create(CategoriesRoutes::class.java)
    }


}

