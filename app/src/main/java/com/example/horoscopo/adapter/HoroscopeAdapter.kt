package com.example.horoscopo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope

class HoroscopeAdapter(val items: List<Horoscope>) : RecyclerView.Adapter<HoroscopeViewHolder>()
{
    //private var onClickListener= OnClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int { //devuelve tamaño de la lista
        return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope= items[position]
        holder.render(horoscope)

    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view)
{

    var nameTextView: TextView = view.findViewById(R.id.nameTV)
    var datesTextView: TextView = view.findViewById(R.id.dateTV)
    var symbolImageView: ImageView = view.findViewById(R.id.iconIV)

    fun render(horoscope: Horoscope) {
        //val context = itemView.context
        //nameTextView.text = context.getString(horoscope.name)
        //symbolImageView.setImageDrawable(context.getDrawable(horoscope.image))

        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.date)
        symbolImageView.setImageResource(horoscope.image)

    }




}