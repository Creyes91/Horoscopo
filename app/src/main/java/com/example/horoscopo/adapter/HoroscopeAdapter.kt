package com.example.horoscopo.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.data.Horoscope

class HoroscopeAdapter(val items: List<Horoscope>) : RecyclerView.Adapter<HoroscopeViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope= items[position]
        holder.render(horoscope)
    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view)
{

    var nameTV: TextView
    var dateTV: TextView
    var iconIV: ImageView

    fun render(horoscope: Horoscope) {

    }


}