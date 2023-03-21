package com.codeplace.mvvmlibraryapp.util

import java.text.DecimalFormat
import java.text.NumberFormat


fun priceFormatter(price:Double ): String {
    val formatter: NumberFormat = DecimalFormat("##,##")
    val formattedNumber = formatter.format(price)
    return formattedNumber.toString()
}