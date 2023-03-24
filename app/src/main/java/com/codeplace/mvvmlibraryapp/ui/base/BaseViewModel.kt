package com.codeplace.mvvmlibraryapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDescriptionDto
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import kotlinx.coroutines.launch
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    fun fetchDataHome(
        liveData: MutableLiveData<StateFlow>,
        service: suspend () -> Response<List<BookContentDto>>
    ) {
        liveData.value = StateFlow.Loading(true)

        viewModelScope.launch {

            try {
                val response = service()
                if (response.isSuccessful) {

                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Success(response.body() as List<BookContentDto>)
                }
                else {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = handleNetworkErrors(response)
                }
            } catch (e: Exception) {
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error("No internet connection", null,"Check your connection, then refresh the page" , e.toString())
            }
        }
    }

    fun fetchDataBookDetails(
        liveData: MutableLiveData<StateFlow>,
        service: suspend () -> Response<BookContentDescriptionDto>
    ) {
        liveData.value = StateFlow.Loading(true)
        viewModelScope.launch {
            try {
                val response = service()
                if (response.isSuccessful) {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Success(response.body() as BookContentDescriptionDto)
                }
                else {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = handleNetworkErrors(response)
                }
            } catch (e: Exception) {
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error("No internet connection", null,"Check your connection, then refresh the page" , e.toString())
            }
        }
    }

    fun handleNetworkErrors(response: Response<*>): StateFlow {
       // val errorDetail = response.errorBody()?.toString()
        val errorMessage = response.message()
        val errorCode = response.code().toString()
        return StateFlow.Error(errorMessage, errorCode, "Please try again later...", null)
    }

    // Original handleNetworkError
//    fun handleNetworkError2(response: Response<*>):StateFlow {
//        val error = response.errorBody()?.string()
//        val errorCode = response.code().toString()
//        val message = JsonParser().parse(error)
//            .asJsonObject("error")
//        return StateFlow.Error(response.message(),errorCode,null,null)
//    }
}