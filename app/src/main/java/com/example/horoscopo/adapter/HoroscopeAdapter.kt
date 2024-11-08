package com.example.horoscopo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.utils.SessionManager

class HoroscopeAdapter(var items: List<Horoscope>, val onItemClick:(Int) -> Unit) : RecyclerView.Adapter<HoroscopeViewHolder>()
{



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
        holder.itemView.setOnClickListener{
            onItemClick(position)


       /* val v = if (holder.detailTxt.visibility== View.GONE) View.VISIBLE else View.GONE
        holder.detailTxt.visibility=v*/
        }


    }

    fun updateItems(items: List<Horoscope>) {
        this.items=items
        notifyDataSetChanged()

    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view)
{

    var nameTextView: TextView = view.findViewById(R.id.nameTV)
    var datesTextView: TextView = view.findViewById(R.id.dateTV)
    var symbolImageView: ImageView = view.findViewById(R.id.iconIV)
    var favImg: ImageView= view.findViewById(R.id.favImg)

    //prueba expandir cardview
  /*  var detailTxt: TextView = view.findViewById(R.id.detailTxt)
    var expandable: CardView= view.findViewById(R.id.expandableCV)*/

  //  var layout: LinearLayout= view.findViewById(R.id.layout)

    fun render(horoscope: Horoscope) {
        //val context = itemView.context
        //nameTextView.text = context.getString(horoscope.name)
        //symbolImageView.setImageDrawable(context.getDrawable(horoscope.image))




        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.date)
        symbolImageView.setImageResource(horoscope.image)
       // detailTxt.setText("prueba")

        if(SessionManager(itemView.context).isFavorite(horoscope.id))
            favImg.visibility= View.VISIBLE
        else
            favImg.visibility= View.GONE

    }



}