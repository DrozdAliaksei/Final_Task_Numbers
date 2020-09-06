package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.local.model.Film

sealed class Result {

    class ResultSuccess(val data: List<Film>) : Result()

    class ResultFailed(val message: String) : Result()
}
