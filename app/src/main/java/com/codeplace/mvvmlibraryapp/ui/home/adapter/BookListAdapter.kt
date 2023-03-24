package com.codeplace.mvvmlibraryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmlibraryapp.databinding.ItemsBookBinding
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto
import com.codeplace.mvvmlibraryapp.util.priceFormatter

open class BookListAdapter(private val booksList: List<BookContentDto>,
                           private val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private lateinit var binding: ItemsBookBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = booksList.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val booksList = booksList[position]
        viewHolder.setData(booksList)
    }

    inner class ViewHolder(private val itemsV: ItemsBookBinding) : RecyclerView.ViewHolder(itemsV.root) {

        fun setData(bookItems: BookContentDto?) {
            bookItems?.let {
                itemsV.txtTitle.text = bookItems.title
                itemsV.txtAuthor.text = bookItems.author
                itemsV.txtCurrency.text = bookItems.currencyCode
                itemsV.txtPrice.text = priceFormatter(bookItems.price)
                itemsV.txtIsbn.text = bookItems.isbn

                //The click listener
                itemsV.cardBook.setOnClickListener{
                    // Create the call back to the activity.
                    // so with the listener created, when the book be clicked, we will execute the Interface created previously.
                    listener.onRecyclerViewCardClick(itemsV.cardBook, bookItems)
                    }
                }
            }
        }
    }




