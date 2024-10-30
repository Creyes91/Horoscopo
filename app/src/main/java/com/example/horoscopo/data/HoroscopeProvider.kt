package com.example.horoscopo.data

import com.example.horoscopo.R


class HoroscopeProvider
{
    companion object
    {
        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope("aries", R.string.horoscopo_name_aries, R.string.horoscope_date_aries, R.drawable.aries_icon),
            Horoscope("taurus",R.string.horoscopo_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus_icon),
            Horoscope("gemini", R.string.horoscopo_name_Gemini, R.string.horoscope_date_gemini, R.drawable.gemini_icon),
            Horoscope("leo",R.string.horoscopo_name_leo, R.string.horoscope_date_leo, R.drawable.leo_icon),
            Horoscope("aquarius",R.string.horoscopo_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius_icon),
            Horoscope("cancer", R.string.horoscopo_name_cancer,R.string.horoscope_date_cancer, R.drawable.cancer_icon),
            Horoscope("libra",R.string.horoscopo_name_libra, R.string.horoscope_date_libra, R.drawable.libra_icon),
            Horoscope("capricorn",R.string.horoscopo_name_capricorn,R.string.horoscope_date_capricorn,R.drawable.capricornus_icon),
            Horoscope("pisces",R.string.horoscopo_name_pisces,R.string.horoscope_date_pisces,R.drawable.pisces_icon),
            Horoscope("sagittarius",R.string.horoscopo_name_sagittarius, R.string.horoscope_date_sagittarius, R.drawable.sagittarius_icon),
            Horoscope("virgo",R.string.horoscopo_name_virgo,R.string.horoscope_date_virgo, R.drawable.virgo_icon),
            Horoscope("scorpio", R.string.horoscopo_name_scorpio,R.string.horoscope_date_scorpio, R.drawable.scorpio_icon)
        )

        fun findAll(): List<Horoscope>
        {
            return horoscopeList
        }

        fun findByID(id: String): Horoscope
        {
            return horoscopeList.find { it.id==id }!!

        }

    }

}