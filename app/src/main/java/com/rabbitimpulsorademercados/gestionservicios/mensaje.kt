package com.rabbitimpulsorademercados.gestionservicios

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PowerManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class mensaje : AppCompatActivity() {
    private lateinit var timerLock: CountDownTimer
    private lateinit var devicePolicyManager: DevicePolicyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        //PAra bloquear equipo
        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        val intent = Intent(this, Service::class.java)
        //Llama al temporizador para bloquear el dispositivo.

        //Se llama al servicio donde pone a trabajar e sersor de proximidad aun que la tablet este apagada.
        startService(intent)

    }
    fun tickets(view: View){

        val intent = Intent(this, MainActivity::class.java)
        timerLock.cancel()
        Log.d("Contador", "Cancelando timer")
        startActivity(intent)

    }

    //Temporizador para llamar servicio de bloqueo.
    fun temporizadorLock(){
        //val cargarLock = Intent(this, ServiceLock::class.java) //para recargar activity
        Log.d("Contador", "Nuevo....Contando.....Mensaje")
        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 6
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


        timerLock = object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
                Log.d("Contador", "Contando.....Mensaje")
            }
            override fun onFinish() {
                //Inicia el servicio de bloqueo.
                //startService(cargarLock)

                Log.d("Contador", "Bloqueandooooooooo en Mensaje")
                devicePolicyManager.lockNow()
            }
        }.start()

    }

    override fun onStart() {
        super.onStart()

        Log.d("Contador", "Start")
        temporizadorLock()
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("Contador", "OnRestar")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Contador", "Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Contador", "Pause")
    }
}