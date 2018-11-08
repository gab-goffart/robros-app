package com.robros.gabriel.robros.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robros.gabriel.robros.R
import com.robros.gabriel.robros.fragments.PositionRecyclerView
import kotlinx.android.synthetic.main.position_card.view.*

class PositionAdapter(private val positions: ArrayList<Number>, private val mListener: PositionRecyclerView.fListener?) : RecyclerView.Adapter<PositionAdapter.ViewHolder>() {

    //renvoie le nombre d'éléments
    override fun getItemCount() = positions.size

    //crée une View et la renvoie (vide)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.position_card, parent, false)
        return ViewHolder(view)
    }

    //Renvoie une view pleine
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(positions[position])

        holder.itemView.setOnClickListener {
            mListener!!.onPositionSelected(holder.position)
        }
    }

    inner class ViewHolder(mView: View): RecyclerView.ViewHolder(mView) {
        var position : Number = 0
        var lblPosition = mView.lblposition

        fun bind(position: Number) {

            this.position = position

            lblPosition.text = "place #${this.position}"
        }
    }
}