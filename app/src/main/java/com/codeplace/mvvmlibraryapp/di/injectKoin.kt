package com.codeplace.mvvmlibraryapp.di

import com.codeplace.mvvmlibraryapp.network.repository.BookRepository
import com.codeplace.mvvmlibraryapp.ui.home.viewModel.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single{
        BookRepository()
    }

    viewModel {
        BookViewModel(get())
    }
}