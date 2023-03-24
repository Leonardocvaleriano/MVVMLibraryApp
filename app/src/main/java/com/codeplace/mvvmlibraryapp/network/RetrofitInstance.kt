package com.codeplace.mvvmlibraryapp.network

import com.codeplace.mvvmlibraryapp.MVVMLibraryApplication.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/*

object declaration(Create Singleton), for the follow reason:
- The other class which will implement it doesn't need to instance this object to use it contain.
- object are also used to make it contains remaining on from the init 'til the final of the system.

 */

object RetrofitInstance {
    fun getRetrofit():API {

        // Create Logger to get the response data
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        // Create the RetrofitBuilder
        val retrofit = Retrofit.Builder()
            // This is the URL that we always access with our request.
            .baseUrl(BASE_URL)
            // Applying the logger
            .client(client)
            // Show how to get the Json and how to parse it to the data class created in the project
            .addConverterFactory(GsonConverterFactory.create())
            // Bulding the instance of the retrofit
            .build()
        // Create the api
        return retrofit.create(API::class.java)
    }
}