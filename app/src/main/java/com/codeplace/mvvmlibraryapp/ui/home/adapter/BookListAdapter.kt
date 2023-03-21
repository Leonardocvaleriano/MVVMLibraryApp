package com.codeplace.mvvmlibraryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmlibraryapp.databinding.ItemsBookBinding
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto

class BookListAdapter(val bookList: List<BookContentDto>) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {


    lateinit var binding:ItemsBookBinding
    class ViewHolder(var view:ItemsBookBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val booklistPosition = bookList[position]
        viewHolder.view.txtTitle.text = booklistPosition.title
        viewHolder.view.txtAuthor.text = booklistPosition.author
        viewHolder.view.txtCurrency.text = booklistPosition.currencyCode.toString()
        viewHolder.view.txtPrice.text = booklistPosition.price.toString()
        viewHolder.view.txtIsbn.text = booklistPosition.isbn

    }

    override fun getItemCount() = bookList.size
}
