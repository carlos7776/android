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

class UsersProvider {

    private  var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRotes()
    }

    fun register(user: User): Call<ResponseHttp>?{
        return  usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>?{
        return  usersRoutes?.login(email, password)
    }
    fun updateithoutImage(user: User): Call<ResponseHttp>?{
        return  usersRoutes?.updateithoutImage(user)
    }



    fun update(file:File,user: User): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"),user.toJson())
        return  usersRoutes?.update(image, requestBody)
    }

}