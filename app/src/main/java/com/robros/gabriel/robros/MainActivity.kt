package com.robros.gabriel.robros

import android.bluetooth.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.robros.gabriel.robros.Models.Commande
import com.robros.gabriel.robros.fragments.*
import kotlinx.android.synthetic.main.app_bar.*
import com.robros.gabriel.robros.R.animator.*
import java.io.IOException
import java.util.*
import kotlin.Exception
import kotlin.NoSuchElementException

class MainActivity : FragmentActivity(),
PositionRecyclerView.fListener,
DrinkRecyclerView.fListener {

    //Default UUID for serial Bluetooth communication
    private val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    //MAC addres of our Bluetooth chip
    private val address = "00:1B:10:30:09:C0"

    private var socket: BluetoothSocket? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Runnable {

            val tr = supportFragmentManager.beginTransaction()
            tr.replace(R.id.contentFrame, PositionRecyclerView.newInstance())
            tr.commit()

        }.run()

        setContentView(R.layout.activity_main)
        setActionBar(toolbar)
        try{
            btConnect()
        } catch (ex: Exception) {
            Snackbar.make(appbar, ex.localizedMessage, Snackbar.LENGTH_LONG).show()
        }

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
        val infos = commande.position.toString() + "," + commande.drink.id

        if(socket == null || !socket!!.isConnected) {

            try {
                btConnect()
            } catch (ex: Exception) {
                Snackbar.make(appbar, ex.localizedMessage, Snackbar.LENGTH_LONG).show()
                return
            }
        }

        socket!!.outputStream.write(infos.toByteArray())


        Toast.makeText(this, "Commande Reçue!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {

        if(socket != null ) {
            if(socket!!.isConnected){
                socket!!.close()
            }
        }

        super.onDestroy()
    }

    private fun btConnect() {

        val bt = BluetoothAdapter.getDefaultAdapter()

        if(!bt.isEnabled) {
            throw Exception("Veuillez activer le bluetooth sur l'appareil. ")
        }

        var device: BluetoothDevice? = null

        try{
            device = bt.bondedDevices.first { it.address == address }
        } catch (ex: NoSuchElementException) {
            throw Exception("Veuillez vous connecter au robot par le menu Bluetooth du téléphone. ")
        }

        socket = device.createRfcommSocketToServiceRecord(uuid)

        try{
            socket!!.connect()

        } catch (ex: IOException) {
            throw Exception("Impossible de joindre le robot. ")
        }

    }

}