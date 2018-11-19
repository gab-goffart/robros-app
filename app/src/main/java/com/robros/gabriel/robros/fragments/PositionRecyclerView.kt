package com.robros.gabriel.robros.fragments

import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.robros.gabriel.robros.R
import com.robros.gabriel.robros.adapters.PositionAdapter
import kotlinx.android.synthetic.main.fragment_position_recycler_view.view.*

class PositionRecyclerView : Fragment() {

    private var listener: fListener? = null
    private var positions = ArrayList<Number>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        positions.add(1)
        positions.add(2)
        positions.add(3)
        positions.add(4)
        positions.add(5)
        positions.add(6)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_position_recycler_view, container, false)

        //TODO: add the adapter
        view.positionRecyclerView.adapter = PositionAdapter(positions, listener)
        view.positionRecyclerView.layoutManager = LinearLayoutManager(view.context)
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
    interface fListener {
        // TODO: Update argument type and name
        fun onPositionSelected(position: Number)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PositionRecyclerView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PositionRecyclerView()
    }
}
