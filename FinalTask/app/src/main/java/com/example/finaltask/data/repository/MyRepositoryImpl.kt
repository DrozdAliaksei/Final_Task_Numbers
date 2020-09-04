package com.example.finaltask.data.repository

import com.example.finaltask.data.NumberDataSource
import com.example.finaltask.data.db.NumberDao
import com.example.finaltask.data.local.model.Number
import com.example.finaltask.util.Constants.RANDOM_DATE
import com.example.finaltask.util.Constants.RANDOM_MATH
import com.example.finaltask.util.Constants.RANDOM_NUMBER
import com.example.finaltask.util.Constants.RANDOM_YEAR

class MyRepositoryImpl(
    private val remoteDataSource: NumberDataSource,
    private val localDataSource: NumberDao
) : MyRepository {

    override suspend fun getRandomNumberOfType(type: String): Number {
        return when (type) {
            RANDOM_NUMBER -> {
                val response = remoteDataSource.getRandomNumber()
                return if (response.isSuccessful) {
                    val result = response.body().let { it!!.toNumber() }
                    saveToLocalRepository(result)
                    result
                } else {
                    getLocalTypedNumber(type)
                }
            }
            RANDOM_MATH -> {
                val response = remoteDataSource.getRandomMath()
                return if (response.isSuccessful) {
                    val result = response.body().let { it!!.toNumber() }
                    saveToLocalRepository(result)
                    result
                } else {
                    getLocalTypedNumber(type)
                }
            }
            RANDOM_DATE -> {
                val response = remoteDataSource.getRandomDate()
                return if (response.isSuccessful) {
                    val result = response.body().let { it!!.toNumber() }
                    saveToLocalRepository(result)
                    result
                } else {
                    getLocalTypedNumber(type)
                }
            }
            RANDOM_YEAR -> {
                val response = remoteDataSource.getRandomYear()
                return if (response.isSuccessful) {
                    val result = response.body().let { it!!.toNumber() }
                    saveToLocalRepository(result)
                    result
                } else {
                    getLocalTypedNumber(type)
                }
            }
            else -> Number(text = "Some problems with interesting numbers")
        }
    }

    override fun clearHistory() = localDataSource.removeAll()

    private fun getLocalTypedNumber(type: String): Number {
        val list = localDataSource.typedList(type).map { it.toNumber() }
        return list[(0..list.size).random()]
    }

    override fun getListOfSavedNumbers(): List<Number> {
        return localDataSource.queryForAll().map {
            it.toNumber()
        }
    }

    private fun saveToLocalRepository(number: Number) =
        localDataSource.add(number.toNumberTable())
    }
