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

    private lateinit var timer: CountDownTimer


    //la pagina que carga siempre el webview
    private val BASE_URL = "https://rabbit-mx.atlassian.net/servicedesk/customer/portal/6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "¡CARGANDO...UN MOMENTO POR FAVOR!" , Toast.LENGTH_LONG).show()
        temporizador()

        //WebView
        webView = findViewById(R.id.webView)
        webView?.webViewClient = WebViewClient()
        webView?.clearCache(true) //false
        webView?.settings?.javaScriptEnabled=true
        webView?.loadUrl(BASE_URL)

    }
    //Temporizador para actualizar la activity.
    fun temporizador(){
        val cargar = Intent(this, mensaje::class.java) //para recargar activity

        //En la variable tiempoSegundos se ingresa la cantidad de tiempo para que se recargue la activity.
        val tiempoSegundos = 60
        val tiempoMilisegundos = (tiempoSegundos.toLong())*1000


       timer = object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onTick(p0: Long) {
                val cuentaSegundos = (p0/1000).toInt()
            }
            override fun onFinish() {
                startActivity(cargar)
            }
        }.start()

    }
    fun cargar(view: View){
        val recargar = Intent(this, mensaje::class.java)
        timer.cancel()
        startActivity(recargar)
    }


}