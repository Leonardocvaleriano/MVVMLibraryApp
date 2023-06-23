package com.codeplace.mvvmlibraryapp.ui.home.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeplace.mvvmlibraryapp.R
import com.codeplace.mvvmlibraryapp.databinding.ActivityHomeBinding
import com.codeplace.mvvmlibraryapp.stateFlow.StateFlow
import com.codeplace.mvvmlibraryapp.ui.home.adapter.BookListAdapter
import com.codeplace.mvvmlibraryapp.ui.home.adapter.RecyclerViewClickListener
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import com.codeplace.mvvmlibraryapp.ui.home.viewModel.BookViewModel
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), RecyclerViewClickListener  {

     private lateinit var binding: ActivityHomeBinding
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
                is StateFlow.Success<*> -> fillBookList(it.data as List<BookContentDto?> )
                is StateFlow.Error -> errorHandle(it.errorMessage)
            }
        }
    }
    private fun fillBookList(result:List<BookContentDto?>) {
        viewModel.fillBooksList(result)
        initBookListAdapter()
    }

    private fun initBookListAdapter() {

        with(binding){
            val adapter = BookListAdapter(viewModel.listBooks, this@HomeActivity)
            recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
            recyclerView.adapter = adapter
        }
    }
    private fun loading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) VISIBLE else GONE
    }

    private fun errorHandle(error: String?) {
        Intent(this, ErrorActivity::class.java).also {
            it.putExtra("EXTRA_ERROR_MESSAGE", error)
             startActivity(it)
        }
     }

    override fun onRecyclerViewCardClick(view: View, book: BookContentDto) {
        when(view.id){
            R.id.card_book -> {
                Intent(this, BookDetailsActivity::class.java).also {
                    it.putExtra("EXTRA_ID", book.id)
                    startActivity(it)
                }
            }
        }
    }
}