package com.sena.manda2.providers

import com.sena.manda2.api.ApiRoutes
import com.sena.manda2.models.Category
import com.sena.manda2.models.ResponseHttp
import com.sena.manda2.models.User
import com.sena.manda2.routes.CategoriesRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class CategoriesProvider (val token:String){

    private var categoriesRoutes: CategoriesRoutes? = null
    init {
       val api = ApiRoutes()
       categoriesRoutes =api.getCategoriesRotes(token)
    }

    fun getAll(): Call<ArrayList<Category>>?{
       return categoriesRoutes?.getAll(token)
    }

    fun create(file: File, category: Category): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"),category.toJson())
        return categoriesRoutes?.create(image, requestBody,token)
    }

}