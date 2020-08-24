package com.example.finaltask.di

import com.example.finaltask.data.NumberDataSource
import com.example.finaltask.data.db.DatabaseHelper
import com.example.finaltask.data.db.NumberDao
import com.example.finaltask.data.repository.MyRepository
import com.example.finaltask.data.repository.MyRepositoryImpl
import com.example.finaltask.vm.DetailViewModel
import com.example.finaltask.vm.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { DatabaseHelper() }
    single { NumberDataSource() }
    single { NumberDao(get()) }
    single { MyRepositoryImpl(get(), get()) }

    viewModel { DetailViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}
