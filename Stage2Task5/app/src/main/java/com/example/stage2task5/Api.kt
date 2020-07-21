package com.example.stage2task5

import android.util.Log
import com.example.stage2task5.data.ApiData
import com.example.stage2task5.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("v1/images/search?limit=10&order=Desc?api_key=efd7f557-8c35-4689-b6b0-8de585e348b1")
    suspend fun getPageOfCats(
        @Query(value = "page") page: Int
    ): Response<List<ApiData>>
}

object CatApiImpl {
    private const val TAG = "CatApi"

    private const val headerKey = "efd7f557-8c35-4689-b6b0-8de585e348b1"
    private const val limit = 10
    private const val order = "Desc"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getPageOfCats(page: Int): List<Cat> {
        val response = catService.getPageOfCats(page)
        return if(response.isSuccessful) withContext(Dispatchers.IO) {
            Log.e(TAG,response.body().toString())
            response.body()!!.map { data-> Cat(data.id,data.imageUrl) }
        } else listOf()
    }
}
