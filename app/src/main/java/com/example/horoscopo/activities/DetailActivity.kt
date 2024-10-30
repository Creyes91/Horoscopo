package com.example.horoscopo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horoscopo.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent pasa los parametros con los que se invoco el Activity
        //hace falta asignar por id y asi acceder a todos los parametros
        //finish() acaba el activity y vuelve al anterior

    }
}