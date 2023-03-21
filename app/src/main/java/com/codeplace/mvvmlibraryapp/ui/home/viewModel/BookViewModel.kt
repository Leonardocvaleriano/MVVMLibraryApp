package com.codeplace.mvvmlibraryapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmlibraryapp.network.repository.BookRepository
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.base.BaseViewModel

class BookViewModel(val bookRepository: BookRepository): BaseViewModel() {

    val bookList = MutableLiveData<StateFlow>()

     fun getBookList() = fetchData(bookList){
        bookRepository.getBookList()
    }
}