package com.codeplace.mvvmlibraryapp.ui.home.adapter

import android.view.View
import com.codeplace.mvvmlibraryapp.ui.home.view.model.BookContentDto

interface RecyclerViewClickListener {
    // view parameters, because we need to detect what view was clicked.
    fun onRecyclerViewCardClick(view: View, book:BookContentDto )

}