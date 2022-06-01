package com.example.recycler_view.api



import com.example.recycler_view.models.photo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitAPIHandler {
   @GET( "photos")

    fun getphotos(): Call<List<photo>>
    companion object{
        val API_URL="https://jsonplaceholder.typicode.com/"
        fun create():RetrofitAPIHandler{
            val retrofit= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .build()
            return retrofit.create(RetrofitAPIHandler::class.java)
        }
    }
}


