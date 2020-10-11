package com.example.finaltask.data

import com.example.finaltask.data.model.NumberResponse
import com.example.finaltask.data.retrofit.NumbersApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NumberDataSource {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("http://numbersapi.com")
        .build()

    private val randomNumberService = retrofit.create(NumbersApiService::class.java)

    suspend fun getRandomNumber(): Response<NumberResponse> {
        return randomNumberService.getRandomNumberInfo()
    }

    suspend fun getRandomMath(): Response<NumberResponse> {
        return randomNumberService.getRandomMathInfo()
    }

    suspend fun getRandomDate(): Response<NumberResponse> {
        return randomNumberService.getRandomDateInfo()
    }

    suspend fun getRandomYear(): Response<NumberResponse> {
        return randomNumberService.getRandomYearInfo()
    }
}
