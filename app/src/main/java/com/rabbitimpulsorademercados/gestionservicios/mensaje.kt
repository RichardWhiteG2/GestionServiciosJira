package com.rabbitimpulsorademercados.gestionservicios

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PowerManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class mensaje : AppCompatActivity() {
    private lateinit var timerLock: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        val intent = Intent(this, Service::class.java)
        //Llama al temporizador para bloquear el dispositivo.
        temporizadorLock()
        //Se llama al servicio donde pone a trabajar e sersor de proximidad aun que la tablet este apagada.
        startService(intent)

    }
    fun tickets(view: View){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    //Temporizador para llamar servicio de bloqueo.
    fun temporizadorLock(){
        val cargarLock = Intent(this, ServiceLock::class.java) //para recargar activity

        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 12
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


        timerLock = object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
            }
            override fun onFinish() {
                //Inicia el servicio de bloqueo.
                //startService(cargarLock)
            }
        }.start()

    }

}