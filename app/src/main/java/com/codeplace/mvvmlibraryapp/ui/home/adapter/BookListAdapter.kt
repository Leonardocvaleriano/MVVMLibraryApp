package com.codeplace.mvvmlibraryapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmlibraryapp.databinding.ItemsBookBinding
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import com.codeplace.mvvmlibraryapp.util.priceFormatter

class BookListAdapter(private val bookList: List<BookContentDto>, val context:Context ) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private lateinit var binding:ItemsBookBinding
    class ViewHolder(var view:ItemsBookBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val bookItemPosition = bookList[position]
        viewHolder.view.txtTitle.text = bookItemPosition.title
        viewHolder.view.txtAuthor.text = bookItemPosition.author
        viewHolder.view.txtCurrency.text = bookItemPosition.currencyCode
        viewHolder.view.txtPrice.text = priceFormatter(bookItemPosition.price)
        viewHolder.view.txtIsbn.text = bookItemPosition.isbn

    }
    override fun getItemCount() = bookList.size
}
