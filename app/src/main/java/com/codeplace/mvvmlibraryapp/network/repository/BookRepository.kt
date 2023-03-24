package com.codeplace.mvvmlibraryapp.network.repository
import com.codeplace.mvvmlibraryapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository{
    // Search for the list of books
    // Still suspend fun, cause the place right to execute this network request is the ViewModel class.
    suspend fun getBookList() = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit()
        return@withContext api.getBooksList()
    }

    suspend fun getBookByID(id:Int) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit()
        return@withContext api.getBookById(id)
    }

}