package com.codeplace.mvvmlibraryapp.ui.home.view.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.mvvmlibraryapp.databinding.ActivityHomeBinding
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.view.ViewModel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
     private val viewModel by viewModel<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValues()
        initObservables()
    }

    private fun initValues() {
        viewModel.getBookList()
    }
    private fun initObservables() {
        viewModel.bookList.observe(this){
            when(it){
                is StateFlow.Loading -> loading(it.loading)
                is StateFlow.Success<*> -> initBookListAdapter(it.data as List<*>)
                is StateFlow.Error -> error(it.errorMessage)

            }
        }
    }

    private fun initBookListAdapter(data: List<*>) {

        Toast.makeText(this, "System is working", Toast.LENGTH_SHORT).show()
    }

    private fun loading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) VISIBLE else GONE
    }

    private fun error(error: String?) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
     }

}