package com.example.finaltask.data.repository

import com.example.finaltask.data.local.model.Number

interface MyRepository {
    suspend fun getRandomNumberOfType(type: String): Number
    fun getListOfSavedNumbers(): List<Number>
}
