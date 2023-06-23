package com.codeplace.mvvmlibraryapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmlibraryapp.network.repository.BookRepository
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.base.BaseViewModel
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import org.json.JSONArray
import org.json.JSONObject

class BookViewModel(private val bookRepository: BookRepository): BaseViewModel() {

    val bookList = MutableLiveData<StateFlow>()
    val bookDetailByID = MutableLiveData<StateFlow>()
    val listBooks = ArrayList<BookContentDto>()

     fun getBookList() = fetchData(bookList){
        bookRepository.getBookList()
    }
    fun getBookById(id:Int) = fetchData(bookDetailByID){
        bookRepository.getBookByID(id)
    }
    fun fillBooksList(result:List<BookContentDto?>){
         result.forEach {
            listBooks.add(BookContentDto(it?.id, it?.title, it?.isbn, it?.price, it?.currencyCode,it?.author))
        }
//         val jsonArray = JSONArray(result)
//        (0 until jsonArray.length())
//            .map { jsonArray.getJSONObject(it) }
//            .forEach { listBooks += BookContentDto(it) }
    }
}