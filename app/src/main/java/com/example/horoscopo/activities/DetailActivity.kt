package com.example.horoscopo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider

class DetailActivity : AppCompatActivity() {

    lateinit var horoscope: Horoscope
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent pasa los parametros con los que se invoco el Activity
        //hace falta asignar por id y asi acceder a todos los parametros
        //finish() acaba el activity y vuelve al anterior

        val id= intent.getStringExtra("horoscope_id")!!

        horoscope = HoroscopeProvider.findByID(id)






    }
}