package com.codeplace.mvvmlibraryapp.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDescriptionDto
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel : ViewModel() {

//    fun fetchData(
//        liveData: MutableLiveData<StateFlow>,
//        service: suspend () -> Response<List<BookContentDto>>
//    ) {
//        liveData.value = StateFlow.Loading(true)
//
//        viewModelScope.launch {
//
//            try {
//                val response = service()
//                if (response.isSuccessful) {
//
//                    liveData.value = StateFlow.Loading(false)
//                    liveData.value = StateFlow.Success(response.body() as List<BookContentDto>)
//                }
//                else {
//                    liveData.value = StateFlow.Loading(false)
//                    liveData.value = handleNetworkErrors(response)
//                }
//            } catch (e: Exception) {
//                liveData.value = StateFlow.Loading(false)
//                liveData.value = StateFlow.Error("No internet connection", null,"Check your connection, then refresh the page" , e.toString())
//            }
//        }
//    }

    fun fetchData(
        liveData: MutableLiveData<StateFlow>,
        service: suspend () -> Response<*>
    ) {
        liveData.value = StateFlow.Loading(true)
        viewModelScope.launch {
            try {
                val response = service()
                if (response.isSuccessful) {

                    val dataResponse = response.body()!!
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Success(dataResponse)
                }
                else if(response.code() == 504) {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Error("An Error occurred when tried to call the service, please try again", null,null,null)
                } else {
                    liveData.value = StateFlow.Error(response.errorBody()!!.string(),null,null,null)
                }
            } catch (e: Exception) {
                Log.e("VmViewModel", Log.getStackTraceString(e))
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!, null,null,null)
            }
        }
    }

//    fun handleNetworkErrors(response: Response<*>): StateFlow {
//       // val errorDetail = response.errorBody()?.toString()
//        val errorMessage = response.message()
//        val errorCode = response.code().toString()
//        return StateFlow.Error(errorMessage, errorCode, "Please try again later...", null)
//    }

}