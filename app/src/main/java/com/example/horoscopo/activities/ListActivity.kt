package com.example.horoscopo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope

class ListActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}