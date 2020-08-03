package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.local.model.Film

sealed class Result {

    abstract fun isSuccess(): Boolean
    abstract fun data(): List<Film>

    class ResultSuccess(private val response: List<Film>) : Result() {
        override fun isSuccess(): Boolean {
            return true
        }

        override fun data() = response
    }

    object ResultFail : Result() {
        override fun isSuccess(): Boolean {
            return true
        }

        override fun data(): List<Film> {
            return listOf()
        }
    }
}
