package com.codeplace.mvvmlibraryapp.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import kotlinx.coroutines.launch
import retrofit2.Response

const val TAG = "ViewModel"
open class BaseViewModel: ViewModel() {

    fun fetchData(liveData: MutableLiveData<StateFlow>,
                  service:suspend() -> Response<List<BookContentDto>>){
        liveData.value = StateFlow.Loading(true)

        viewModelScope.launch {
            try {
                val response = service()
                if (response.isSuccessful) {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Success(response.body() as List<BookContentDto>)
                } else {
                    liveData.value = StateFlow.Loading(false)
                    handleNetworkErrors(response)
                }

            } catch (e: Exception) {
                liveData.value = StateFlow.Loading(false)
               // liveData.value = StateFlow.Error(e.toString(), null, null, null)
                liveData.value = StateFlow.Error("An unknown error occurred",null,"Please, try again...",null)
                Log.d(TAG,"ViewModel")
            }
        }
    }

    private fun handleNetworkErrors(response: Response<*>):StateFlow {
        val detail = response.errorBody()?.toString()
        //val errorCode = response.code().toString()
        val errorMessage = response.message()
        return StateFlow.Error(errorMessage,null,detail,null)
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