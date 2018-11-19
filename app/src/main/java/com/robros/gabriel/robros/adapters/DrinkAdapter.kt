package com.robros.gabriel.robros.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robros.gabriel.robros.Models.Commande
import com.robros.gabriel.robros.Models.Drink
import com.robros.gabriel.robros.R
import com.robros.gabriel.robros.fragments.DrinkRecyclerView
import kotlinx.android.synthetic.main.drink_card.view.*

class DrinkAdapter(var drinks: ArrayList<Drink>,val pos: Int, var mListener: DrinkRecyclerView.fListener?): RecyclerView.Adapter<DrinkAdapter.ViewHolder>()  {

    override fun getItemCount() = drinks.size

    //create empty ViewHolder from drink_card.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.drink_card, parent, false)

        return ViewHolder(view)
    }

    //bind the data to the holder, and add the listener
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drinks[position])

        holder.itemView.setOnClickListener {
            mListener!!.onDrinkSelected(Commande(this.pos, drinks[position]))
        }
    }

    inner class ViewHolder(mView: View): RecyclerView.ViewHolder(mView) {
        private val lblDrinkName = mView.lblDrinkName
        private val lblDrinkPrice = mView.lblDrinkPrice
        private var drink: Drink? = null

        fun bind(drink: Drink) {
            this.drink = drink
            this.lblDrinkName.text = "Couleur du breuvage : ${drink.name}"
            this.lblDrinkPrice.text = "Prix : ${drink.price} $"

        }

    }
}