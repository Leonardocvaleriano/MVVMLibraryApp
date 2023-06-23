package com.codeplace.mvvmlibraryapp.ui.home.view.model

import org.json.JSONObject
import java.io.Serializable

data class BookContentDto(
    val id: Int?,
    val title: String?,
    val isbn: String?,
    val price: Double?,
    val currencyCode: String?,
    val author: String?

) : Serializable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt("id"),
        jsonObject.getString("title"),
        jsonObject.getString("isbn"),
        jsonObject.getDouble("price"),
        jsonObject.getString("currencyCode"),
        jsonObject.getString("author")
    )
}