package com.rabbitimpulsorademercados.gestionservicios

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import android.widget.Toast

/*Activa el sersor de proximidad en 2° plano y una vez que registra un cambio en la data, si el dispositivo esta con pantalla prendida lo prende. */
class Service : Service() {

    private val TAG = "service"
    private lateinit var timerLock: CountDownTimer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    private var sensorManager: SensorManager? = null
    private var mProximitySensor: Sensor? = null
    private var contador=false
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Start")

        Handler().apply {
            sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
            mProximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            if (mProximitySensor == null) {
                TODO("Mandar mensaje si el dispositivo no cuenta con sensor de proximidad")
            } else {
                sensorManager!!.registerListener(proximitySensorEventListener, mProximitySensor, SensorManager.SENSOR_DELAY_NORMAL)

            }

        }
        return START_STICKY
    }

    var proximitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {

                if (event.values[0] == 0f) {
                    //Para prender pantalla.
                    val power: PowerManager.WakeLock =
                        (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                            newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyApp::MyWakelockTag").apply {
                                acquire()   //PARTIAL_WAKE_LOCK
                            }
                        }
                    //llamar el temporizador

                    if(contador==false){
                        //timerLock.cancel()
                        temporizadorBloqueo()

                    }

                }
            }

        }
    }

    override fun onCreate() {
        super.onCreate()
        temporizadorBloqueo()
    }

    override fun onDestroy() {
        Log.d(TAG, "Destroy")
        super.onDestroy()
    }

    fun temporizadorBloqueo(){
        //contador=true
        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 8
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


        timerLock = object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
               Log.d("Contador", "Contando.....")
            }
            override fun onFinish() {
               // contador=false
                //cancel()

                Log.d("Contador", "Bloqueandooooooooo")
            }
        }.start()
    }
}