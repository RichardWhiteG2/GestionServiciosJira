package com.rabbitimpulsorademercados.gestionservicios

import android.app.admin.DevicePolicyManager
import android.os.CountDownTimer
import android.util.Log

class TimerLock {
    private lateinit var timerLock: CountDownTimer
    private lateinit var devicePolicyManager: DevicePolicyManager
    var contando=false

    public fun temporizadorLock()  {
        var corriendo=true
        //val cargarLock = Intent(this, ServiceLock::class.java) //para recargar activity
        Log.d("Contador", "Nuevo....Contando.....TimerLock")
        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 8
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000

        if(!contando) {
            contando=true
            timerLock = object : CountDownTimer(tiempoMilisegundos, 1000) {
                override fun onTick(p0: Long) {
                    val cuentaSegundos = (p0 / 1000).toInt()
                    Log.d("Contador", "Contando.....TimerLock")
                }

                override fun onFinish() {
                    //Inicia el servicio de bloqueo.
                    //startService(cargarLock)

                    Log.d("Contador", "Bloqueandooooooooo en TimerLock")
                    contando=false
                    devicePolicyManager.lockNow()
                    corriendo=false
                }
            }.start()
        }

    }
}