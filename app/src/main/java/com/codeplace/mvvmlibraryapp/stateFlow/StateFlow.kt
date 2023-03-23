package com.codeplace.mvvmlibraryapp.stateFlow

import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto

/*
* Class create to enable the exhaustive when statement
* when we would need to implement it in our activity to handle
* the state of the code procedures.
* */
interface StateFlow{
    data class Loading(val loading:Boolean):StateFlow
    // This Success class has to be generic, because our app can use this class for implement a lot of kinds of data types.
    data class Success(val data:List<BookContentDto>):StateFlow
    data class Error(val  errorMessage: String?, val errorCode: String?, val detail: String?, val errorId: String?):StateFlow

}