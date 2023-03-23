package com.codeplace.mvvmlibraryapp.ui.home.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.mvvmlibraryapp.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val error = intent.getStringExtra("EXTRA_ERROR_MESSAGE")
        val errorDetail = intent.getStringExtra("EXTRA_ERROR_DETAIL")


        with(binding){
            TxtMainError.text = error.toString()
            TxtDetailedError.text = errorDetail.toString()
        }

    }
}