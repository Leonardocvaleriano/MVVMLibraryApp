package com.codeplace.mvvmlibraryapp

import android.app.Application

class MVVMLibraryApplication:Application() {

    companion object{
        const val BASE_URL = "http://tpbookserver.herokuapp.com"
        val IGNORE_SSL = true
        val IS_TESTING = true
    }
}