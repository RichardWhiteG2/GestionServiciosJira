package com.rabbitimpulsorademercados.gestionservicios

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PowerManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class mensaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        val intent = Intent(this, Service::class.java)
        //Se llama al servicio donde pone a trabajar e sersor de proximidad aun que la tablet este apagada.
        startService(intent)

    }
    fun tickets(view: View){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

}