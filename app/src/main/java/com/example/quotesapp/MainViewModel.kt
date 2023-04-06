package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0
    private var size = 0

    init{
        quoteList = loadQuoteFromAssets()
    }

    fun getQuote() = quoteList[index]
    fun prevQuote() = quoteList[--index]
    fun nextQuote() = quoteList[++index]
    fun getIndex() = index
    fun getSize() = size

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }
}