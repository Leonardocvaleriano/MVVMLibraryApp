package com.codeplace.mvvmlibraryapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmlibraryapp.network.repository.BookRepository
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.base.BaseViewModel

class BookViewModel(private val bookRepository: BookRepository): BaseViewModel() {

    val bookList = MutableLiveData<StateFlow>()
    val bookDetailByID = MutableLiveData<StateFlow>()

     fun getBookList() = fetchDataHome(bookList){
        bookRepository.getBookList()
    }
    fun getBookById(id:Int) = fetchDataBookDetails(bookDetailByID){
        bookRepository.getBookByID(id)
    }
}