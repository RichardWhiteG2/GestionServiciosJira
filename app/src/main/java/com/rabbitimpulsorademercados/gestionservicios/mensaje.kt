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

/*muestra el mensaje de la pantalla principal y  se ejecuta el temporizador temporizadorLock() para bloquear la pantalla despues de cierto tiempo  (el tiempo se puede modificar en la variable tiempoSegundos)*/
class mensaje : AppCompatActivity() {
    private lateinit var timerLock: CountDownTimer
    private lateinit var devicePolicyManager: DevicePolicyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        //PAra bloquear equipo
        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        //Se llama al servicio donde pone a trabajar e sersor de proximidad aun que la tablet este apagada.
        val intent = Intent(this, Service::class.java)
        startService(intent)

    }

    //Al precionar el boton comenza en activity mensaje se ejecuta tickets()
    fun tickets(view: View){

        val intent = Intent(this, MainActivity::class.java)

        //Se cancela el timerlock si se envia a la siguiente activity.
        timerLock.cancel()
        Log.d("Contador", "Cancelando timer")
        //incia el MAinActivity
        startActivity(intent)
    }

    //Temporizador para llamar servicio de bloqueo.
    fun temporizadorLock(){

        Log.d("Contador", "Nuevo....Contando.....Mensaje")
        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 600
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


        timerLock = object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
                Log.d("Contador", "Contando.....Mensaje")
            }
            override fun onFinish() {
                //Inicia el servicio de bloqueo.
               Log.d("Contador", "Bloqueandoo en Mensaje")
                devicePolicyManager.lockNow()
            }
        }.start()

    }
    override fun onStart() {
        super.onStart()
        Log.d("Contador", "Start")
        temporizadorLock()
    }
}