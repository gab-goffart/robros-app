package com.robros.gabriel.robros

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.robros.gabriel.robros.Models.Commande
import com.robros.gabriel.robros.fragments.*
import kotlinx.android.synthetic.main.app_bar.*
import com.robros.gabriel.robros.R.animator.*

class MainActivity : FragmentActivity(),
PositionRecyclerView.fListener,
DrinkRecyclerView.fListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setActionBar(toolbar)

        val tr = supportFragmentManager.beginTransaction()
        tr.replace(R.id.contentFrame, PositionRecyclerView.newInstance())
        tr.commit()
    }

    override fun onPositionSelected(position: Number) {
        //Changer le fragment pour celui des drinks
        Runnable {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(slide_in_left_to_right,slide_out_left_to_right, slide_in_right_to_left ,slide_out_right_to_left)
            transaction.replace(R.id.contentFrame, DrinkRecyclerView.newInstance(position.toString()))
            transaction.addToBackStack("PositionRecyclerView")
            transaction.commit()

        }.run()
    }

    override fun onDrinkSelected(commande: Commande) {
        //TODO: Envoyer la commande via Bluetooth LE
        Toast.makeText(this, "Breuvage couleur ${commande.drink.name} au cout de ${commande.drink.price} pour la table ${commande.position}", Toast.LENGTH_LONG).show()
    }

}
