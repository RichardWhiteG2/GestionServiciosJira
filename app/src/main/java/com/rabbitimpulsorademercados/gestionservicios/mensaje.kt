package com.rabbitimpulsorademercados.gestionservicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class mensaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)
    }
    fun tickets(view: View){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}