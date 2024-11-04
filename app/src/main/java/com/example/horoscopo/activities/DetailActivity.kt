package com.example.horoscopo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    private var fav= false

    lateinit var horoscope: Horoscope
    lateinit var Session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent pasa los parametros con los que se invoco el Activity
        //hace falta asignar por id y asi acceder a todos los parametros
        //finish() acaba el activity y vuelve al anterior

        val id= intent.getStringExtra("horoscope_id")!!

        horoscope = HoroscopeProvider.findByID(id)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        setFavIcon(menu?.findItem(R.id.favBtn)!!)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavIcon(menuItem: MenuItem) {
        val id= if (fav) R.drawable.ic_fav;
        else R.drawable.ic_fav_border;

        menuItem.icon= ContextCompat.getDrawable(this, id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.favBtn-> {
                fav = !fav
                setFavIcon(item)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}