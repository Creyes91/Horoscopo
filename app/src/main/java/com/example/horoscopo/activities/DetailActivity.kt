package com.example.horoscopo.activities

import android.annotation.SuppressLint
import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    private var isFav= false

    lateinit var horoscope: Horoscope
    lateinit var Session: SessionManager
    lateinit var lucky_TextView: TextView
    lateinit var date_lucky_TextView: TextView
    lateinit var lucky_ProgressBar: ProgressBar
    private var time= "monthly"
    lateinit var navigateBarView: BottomNavigationView





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

      // imageSigno= findViewById(R.id.detailImageView)
        findViewById<ImageView>(R.id.detailImageView).setImageResource(horoscope.image)
        lucky_TextView = findViewById(R.id.textDetail)
        date_lucky_TextView= findViewById(R.id.date_TextView)
        lucky_ProgressBar= findViewById(R.id.lucky_PB)
        navigateBarView = findViewById(R.id.navView)

        navigateBarView.selectedItemId= R.id.item_today

        navigateBarView.setOnItemSelectedListener {
            when (it.itemId)
            {
                R.id.item_today->{
                    time= "daily"

                }
                R.id.item_thisWeek->{
                    time= "weekly"

                }
                R.id.item_thisMonth->{
                    time= "monthly"

                }

            }

            getHoroscopeLuck(time)
            return@setOnItemSelectedListener true


        }


        getHoroscopeLuck(time)







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
                    putExtra(Intent.EXTRA_TEXT, getString(horoscope.name) + date_lucky_TextView.text.toString()+ lucky_TextView.text.toString())
                    type = "text/plain"

                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shareLuck(){
        val sendIntent = Intent()
        sendIntent.action= Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(horoscope.name) + date_lucky_TextView.text.toString()+ lucky_TextView.text.toString())
        sendIntent.type = "text/plain"
    }
    fun getHoroscopeLuck(time:String)
    {
        var result = "Inicializacion"
        var date = ""

        CoroutineScope(Dispatchers.IO).launch {
            val url =
                URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/${time}?sign=${horoscope.id}&day=TODAY")
            val con = url.openConnection() as HttpsURLConnection

            try {
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                if (responseCode == HttpsURLConnection.HTTP_OK) // revisa que la conexion se establezca y de el codigo correspondiente
                {
                    val jsonResponse = readStream(con.inputStream).toString()
                    result = JSONObject(jsonResponse).getJSONObject("data")
                        .getString("horoscope_data") //llama a un objeto Json y dentro de este accede a los valores
                    date = JSONObject(jsonResponse).getJSONObject("data").getString("date")
                } else {
                    result = "hubo un error"
                }
            } catch (e : Exception)
            {
                date= "error en la conexion"
            }
            CoroutineScope(Dispatchers.Main).launch {
                lucky_TextView.text = result
                date_lucky_TextView.text= date
                lucky_ProgressBar.visibility = View.GONE
                lucky_TextView.visibility = View.VISIBLE
            }
        }



    }

    private fun readStream(inputStream: InputStream): StringBuilder {

        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null){
            response.append(line)
        }
        reader.close()
        return response
    }


}