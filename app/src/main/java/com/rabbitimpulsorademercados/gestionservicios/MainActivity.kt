package com.rabbitimpulsorademercados.gestionservicios

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

/* Realizada por Ing. Brayan Ricardo Blanco García
    Esta app muestra una vista web de la plataforma Jira para tickets de Mesa de ayuda de Rabbitmx.
    Se establecio un tiempo de 10 minutos en que se está actualizando para que siempre muestre la pantalla de generar tickets.
    El tiempo puede ser modificado en la variable tiempoSegundos
 */
class MainActivity : AppCompatActivity() {
    //Private
    private var webView: WebView?=null


    //Sensor
    private lateinit var sensorManager: SensorManager
    private var mProximity: Sensor? = null

    /* funcionalidad nueva
     var portada=false*/

    //la pagina que carga siempre el webview
    private val BASE_URL = "https://rabbit-mx.atlassian.net/servicedesk/customer/portal/6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "¡CARGANDO...UN MOMENTO POR FAVOR!" , Toast.LENGTH_LONG).show()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
       /*Funcionalidad 2
        val objetoIntent: Intent=intent
        portada = objetoIntent.getBooleanExtra("portada",portada)

        if(portada){
            temporizador()
            Toast.makeText(this, "intent recibido" , Toast.LENGTH_SHORT).show()
            portada=false
        }*/
        //WebView
        webView = findViewById(R.id.webView)
        webView?.webViewClient = WebViewClient()
        webView?.clearCache(true) //false
        webView?.settings?.javaScriptEnabled=true
        webView?.loadUrl(BASE_URL)

    }
    //Temporizador para actualizar la activity.
    fun temporizador(){
        /*Funcionalidad nueva
        val amensaje = Intent(this, mensaje::class.java) //para recargar activity*/
        val cargar = Intent(this, MainActivity::class.java) //para recargar activity

        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 600
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


        object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
            }
            override fun onFinish() {
                startActivity(cargar)
            }
        }.start()
    }
    fun cargar(view: View){
        val recargar = Intent(this, MainActivity::class.java)
        temporizador()
        startActivity(recargar)
    }
    //Sensor
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        val distance = event.values[0]
        // Do something with this sensor data.
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()


        mProximity?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}