package com.robros.gabriel.robros.Models

open class Drink (var id: Number, var name: String, var price: Number) {
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(drink: Drink?)
    }
}