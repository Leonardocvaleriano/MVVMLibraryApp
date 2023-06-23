package com.codeplace.mvvmlibraryapp.ui.home.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.mvvmlibraryapp.databinding.ActivityBookDetailsBinding
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDescriptionDto
import com.codeplace.mvvmlibraryapp.ui.home.viewModel.BookViewModel
import com.codeplace.mvvmlibraryapp.util.priceFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding
    private val viewModel by viewModel<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("EXTRA_ID")) {
            val id = intent.getIntExtra("EXTRA_ID", 0)
            initValues(id)
            initObservables()
        }
    }

    private fun initValues(id: Int) {
        viewModel.getBookById(id)
    }

    private fun initObservables() {
        viewModel.bookDetailByID.observe(this) {
            when(it){
                is StateFlow.Loading -> loading(it.loading)
                is StateFlow.Success<*> -> initBinding(it.data as BookContentDescriptionDto)
                is StateFlow.Error -> errorHandle(it.errorMessage, it.detail)
            }
         }
    }

    private fun loading(loading: Boolean) {
       with(binding)
       {
           view.visibility = if (loading) GONE else VISIBLE
           progressBar.visibility =  if(loading) VISIBLE else GONE
           txtDescriptionTitle.visibility = if (loading) GONE else VISIBLE
           imgBook.visibility = if (loading) GONE else VISIBLE
       }
    }

    private fun initBinding(data: BookContentDescriptionDto) {
        with(binding){
            // init android content

            // init data
            txtTitle.text = data.title
            txtAuthor.text= data.author
            txtIsbn.text = data.isbn
            txtCurrency.text = data.currencyCode
            txtPrice.text = priceFormatter(data.price)
            txtDescription.text = data.description
        }
    }

    private fun errorHandle(error: String?, detail:String?) {

        Intent(this, ErrorActivity::class.java).also {
            it.putExtra("EXTRA_ERROR_MESSAGE", error)
            it.putExtra("EXTRA_ERROR_DETAIL", detail)
            startActivity(it)
            finish()
        }
    }
}