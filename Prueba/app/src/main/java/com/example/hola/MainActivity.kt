package com.example.hola

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var mitexto : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var miboton: Button = this.findViewById<Button>(R.id.bt1)
        mitexto = this.findViewById<TextView>(R.id.tv1)
        //miboton.setOnClickListener {mitexto.text = "Gracias por pulsarme" }
        miboton.setOnLongClickListener { mitexto.text= "Mariquita"; true}

        fun pontexto(v: View){
            mitexto.text = "loquequieras"
            mitexto.setTextColor(Color.RED)
        }


    }
}