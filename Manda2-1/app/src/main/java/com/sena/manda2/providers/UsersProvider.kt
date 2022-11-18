package com.sena.manda2.providers

import com.sena.manda2.api.ApiRoutes
import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import com.sena.manda2.routes.UsersRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class UsersProvider (val token:String? = null){

    private  var usersRoutes: UsersRoutes? = null
    private  var usersRoutesToken: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRotes()
        if(token != null){
            usersRoutesToken = api.getUsersRotesWithToken(token!!)
        }

    }

    fun register(user: User): Call<ResponseHttp>?{
        return  usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>?{
        return  usersRoutes?.login(email, password)
    }
    fun updateithoutImage(user: User): Call<ResponseHttp>?{
        return  usersRoutesToken?.updateithoutImage(user, token!!)
    }



    fun update(file:File,user: User): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"),user.toJson())
        return usersRoutesToken?.update(image, requestBody,token!!)
    }

}