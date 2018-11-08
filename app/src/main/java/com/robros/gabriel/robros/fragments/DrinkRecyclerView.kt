package com.robros.gabriel.robros.fragments

import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robros.gabriel.robros.Models.Commande
import com.robros.gabriel.robros.Models.Drink

import com.robros.gabriel.robros.R
import com.robros.gabriel.robros.adapters.DrinkAdapter
import kotlinx.android.synthetic.main.fragment_drink_recycler_view.view.*

private const val ARG_PARAM1 = "position"

class DrinkRecyclerView : Fragment() {
    private var position: String? = null
    private val drinks = ArrayList<Drink>()
    private var listener: fListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getString(ARG_PARAM1)
        }
        drinks.add(Drink(1, "bleu", 5.99))
        drinks.add(Drink(2, "rouge", 5.99))
        drinks.add(Drink(3, "jaune", 5.99))
        drinks.add(Drink(4, "orange", 9.99))
        drinks.add(Drink(5, "vert", 9.99))
        drinks.add(Drink(6, "mauve", 9.99))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_drink_recycler_view, container, false)
        view.drinkRecyclerView.adapter = DrinkAdapter(drinks, this.position!!.toInt(), listener)
        view.drinkRecyclerView.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is fListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface fListener{
        // TODO: Update argument type and name
        fun onDrinkSelected(commande: Commande)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment DrinkRecyclerView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: String) =
                DrinkRecyclerView().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, position)
                    }
                }
    }
}
