package com.codeplace.mvvmlibraryapp.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmlibraryapp.databinding.ItemsBookBinding
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import com.codeplace.mvvmlibraryapp.util.priceFormatter

class BookListAdapter(private val List: List<BookContentDto>, val context:Context) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private lateinit var binding: ItemsBookBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = List.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val booksList = List[position]
        viewHolder.setData(booksList, position)
    }

    inner class ViewHolder(private val itemsV: ItemsBookBinding) : RecyclerView.ViewHolder(itemsV.root) {
        private var itemsBook: BookContentDto? = null
        private var currentPosition: Int = 0

        init {
            //The click listener
            itemsV.cardBook.setOnClickListener {
                Intent(this,)

            }
        }
        fun setData(bookItems: BookContentDto?, position: Int) {
            bookItems?.let {
                itemsV.txtTitle.text = bookItems.title
                itemsV.txtAuthor.text = bookItems.author
                itemsV.txtCurrency.text = bookItems.currencyCode
                itemsV.txtPrice.text = priceFormatter(bookItems.price)
                itemsV.txtIsbn.text = bookItems.isbn
            }
            this.itemsBook = bookItems
            this.currentPosition = position
        }
    }
}



