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


        with(binding){

            val charactersToRemove = arrayOf("\"", "{", ":", "}")
            val wordToRemove = "message"

            txtMainError.text = error?.replace(charactersToRemove[0],"")
                ?.replace(charactersToRemove[1],"")
                ?.replace(charactersToRemove[2],"")
                ?.replace(charactersToRemove[3],"")
                ?.replace(wordToRemove,"")
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}