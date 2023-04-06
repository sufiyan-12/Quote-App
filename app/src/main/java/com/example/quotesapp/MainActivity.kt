package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var prevBtn: TextView
    private lateinit var nextBtn: TextView
    private lateinit var shareBtn: FloatingActionButton
    private lateinit var quoteText: TextView
    private lateinit var authorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVars()
        setQuote(mainViewModel.getQuote())

        prevBtn.setOnClickListener {
            val index = mainViewModel.getIndex()
            if( index > 0 && index < mainViewModel.getSize())
                setQuote(mainViewModel.prevQuote())
            else
                Toast.makeText(this, "No previous quote present.", Toast.LENGTH_SHORT).show()
        }

        nextBtn.setOnClickListener {
            val index = mainViewModel.getIndex()
            if( index >= 0 && index < mainViewModel.getSize()-1)
                setQuote(mainViewModel.nextQuote())
            else
                Toast.makeText(this, "No Next quote present.", Toast.LENGTH_SHORT).show()
        }

        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }

    private fun setQuote(quote: Quote){
        quoteText.text = quote.text
        authorText.text = "author: ${quote.author}"
    }

    private fun initVars() {
        mainViewModel = ViewModelProvider(this, MainFactory(application))[MainViewModel::class.java]
        prevBtn = findViewById(R.id.previousBtn)
        nextBtn = findViewById(R.id.nextBtn)
        shareBtn = findViewById(R.id.shareBtn)
        quoteText = findViewById(R.id.quoteText)
        authorText = findViewById(R.id.authorText)
    }
}