package com.example.finaltask.data.retrofit

import com.example.finaltask.data.model.NumberResponse
import retrofit2.Response
import retrofit2.http.GET

interface NumbersApiService {

    @GET("/random/trivia?json")
    suspend fun getRandomNumberInfo(): Response<NumberResponse>

    @GET("/random/math?json")
    suspend fun getRandomMathInfo(): Response<NumberResponse>

    @GET("/random/date?json")
    suspend fun getRandomDateInfo(): Response<NumberResponse>

    @GET("/random/year?json")
    suspend fun getRandomYearInfo(): Response<NumberResponse>
}
