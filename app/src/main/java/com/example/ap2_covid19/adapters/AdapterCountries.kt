package com.example.ap2_covid19.adapters

import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.widget.TextView
import com.example.ap2_covid19.R
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.ap2_covid19.classes.Countries
import com.example.ap2_covid19.CountryDetailsActivity
import kotlinx.android.synthetic.main.card.view.*

class AdapterCountries (private val countries: List<Countries>): RecyclerView.Adapter<AdapterCountries.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        val vh = VH(v)

        vh.itemView.setOnClickListener {
            val intent = Intent(v.context, CountryDetailsActivity::class.java)
            val arrayCountry = countries[vh.adapterPosition]
            intent.putExtra("info", arrayCountry)
            v.context.startActivity(intent)
        }

        return vh
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val country = countries[position]
        holder.countryName.text = country.name
        holder.countryCases.text = holder.itemView.resources.getString(R.string.confirmed) + " " +  country.confirmed.toString()
    }

    class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        var countryName:TextView = itemView.card_place
        var countryCases:TextView = itemView.card_cases
    }
}
