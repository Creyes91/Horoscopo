package com.example.horoscopo.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    private var isFav= false

    lateinit var horoscope: Horoscope
    lateinit var Session: SessionManager
    lateinit var txt: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent pasa los parametros con los que se invoco el Activity
        //hace falta asignar por id y asi acceder a todos los parametros
        //finish() acaba el activity y vuelve al anterior

        val id= intent.getStringExtra("horoscope_id")!!
        horoscope = HoroscopeProvider.findByID(id)
        supportActionBar?.title= getString(horoscope.name).uppercase()
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)  //poner un boton home a la izquierda del toolbar

        Session= SessionManager(this)// CONSTRUCTOR PRIMERO NO OLVIDAR

        isFav = Session.isFavorite(horoscope.id)

      //  imageSigno= findViewById(R.id.detailImageView)
        findViewById<ImageView>(R.id.detailImageView).setImageResource(horoscope.image)

        txt = findViewById<TextView>(R.id.textDetail).text.toString()





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        setFavIcon(menu?.findItem(R.id.favBtn)!!)

        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavIcon(menuItem: MenuItem) {
        val id= if (isFav) R.drawable.ic_fav;
        else R.drawable.ic_fav_border;

        menuItem.icon= ContextCompat.getDrawable(this, id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.favBtn-> {
                if (isFav)
                    Session.setFavorite("")
                else Session.setFavorite(horoscope.id)
                isFav = !isFav
                setFavIcon(item)
            }

            R.id.shareBtn->
            {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, txt)
                    type = "text/plain"

                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }
        }

        return super.onOptionsItemSelected(item)
    }
}