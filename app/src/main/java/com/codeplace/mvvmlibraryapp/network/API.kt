package com.codeplace.mvvmlibraryapp.network

import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDescriptionDto
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// This interface will be responsible to get the data as Gson on the web.
interface API {
    // To get the data, I need to insert the verb that will be applied.
    @GET("/books")
    // This fun will be used to make actual requests.
    suspend fun getBooksList(): Response<List<BookContentDto>>

    @GET("/book/{id}")
    suspend fun getBookById(@Path(value = "id")id:Int): Response<BookContentDescriptionDto>
}
