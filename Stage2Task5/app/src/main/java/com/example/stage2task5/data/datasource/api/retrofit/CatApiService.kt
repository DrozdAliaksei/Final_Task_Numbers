package com.example.stage2task5.data.datasource.api.retrofit

import com.example.stage2task5.data.datasource.api.model.CatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApiService {

    @GET("v1/images/search")
    suspend fun getPageOfCats(
        @Header("x-api-key") headerKey: String,
        @Query("limit") limit: Int,
        @Query("order") perPage: String,
        @Query("page") page: Int
    ): Response<List<CatResponse>>
}
