package com.codeplace.mvvmlibraryapp.ui.home.view.model

data class BookContentDto(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Double,
    val title: String
)