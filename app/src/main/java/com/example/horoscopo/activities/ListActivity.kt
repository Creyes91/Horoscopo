package com.example.horoscopo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.adapter.HoroscopeAdapter
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider

class ListActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView=findViewById(R.id.rvMain) // creacion de RV

        horoscopeList=HoroscopeProvider.findAll() //creacion asignacion de lista
        val adapter = HoroscopeAdapter(horoscopeList) //creacion de adapter pasando la lista
        recyclerView.adapter=adapter //asignacion del adapter creado en la activity al adapter de RV
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) // manejo del RV en vertical

       // recyclerView.layoutManager= GridLayoutManager(this,2) //RV en columnas





    }
}