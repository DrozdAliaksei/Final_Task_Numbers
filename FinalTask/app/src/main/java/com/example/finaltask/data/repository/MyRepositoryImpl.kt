package com.example.finaltask.data.repository

import com.example.finaltask.data.NumberDataSource
import com.example.finaltask.data.db.NumberDao
import com.example.finaltask.data.local.model.Number
import com.example.finaltask.data.model.NumberResponse
import com.example.finaltask.util.Constants.RANDOM_DATE
import com.example.finaltask.util.Constants.RANDOM_MATH
import com.example.finaltask.util.Constants.RANDOM_NUMBER
import com.example.finaltask.util.Constants.RANDOM_YEAR
import retrofit2.Response

class MyRepositoryImpl(
    private val remoteDataSource: NumberDataSource,
    val localDataSource: NumberDao
) : MyRepository {

    override suspend fun getRandomNumberOfType(type: String): Number {
        return when (type) {
            RANDOM_NUMBER -> {
                responseCheckForResult(remoteDataSource.getRandomNumber())
            }
            RANDOM_MATH -> {
                responseCheckForResult(remoteDataSource.getRandomMath())

            }
            RANDOM_DATE -> {
                responseCheckForResult(remoteDataSource.getRandomDate())
            }
            RANDOM_YEAR -> {
                responseCheckForResult(remoteDataSource.getRandomYear())
            }
            else -> Number()
        }
    }

    override fun getListOfSavedNumbers(): List<Number> {
//        return localDataSource.queryForAll()
        return listOf()
    }

    private fun responseCheckForResult(response: Response<NumberResponse>): Number {
        return if (response.isSuccessful) {
            responseToNumberConverter(response.body()!!)
        } else {
            //TODO return typed number from local repository
            Number()
        }
    }

    private fun responseToNumberConverter(response: NumberResponse): Number {
        return Number(
            text = response.text,
            found = response.found,
            number = response.number,
            type = response.type,
            date = response.date,
            year = response.year
        )
    }
}
