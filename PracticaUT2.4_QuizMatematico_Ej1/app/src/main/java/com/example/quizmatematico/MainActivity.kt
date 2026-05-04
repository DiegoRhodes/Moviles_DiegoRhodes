package com.example.quizmatematico

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numeroGenerado = 0

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var escribirNum = findViewById<TextView>(R.id.escribirNum)
        var genNum = findViewById<Button>(R.id.genNum)
        var rbSi = findViewById<RadioButton>(R.id.rbSi)
        var rbNo = findViewById<RadioButton>(R.id.rbNo)
        var comprobar = findViewById<Button>(R.id.comprobar)
        var resultado = findViewById<TextView>(R.id.resultado)
        var amarillo = findViewById<Switch>(R.id.amarillo)
        var layoutPrincipal = findViewById<View>(R.id.main)



        genNum.setOnClickListener {
            numeroGenerado = Random.nextInt(1900, 2501)
            escribirNum.text = numeroGenerado.toString()
            rbSi.isChecked = false
            rbNo.isChecked = false
        }

        comprobar.setOnClickListener {
            var esBisiesto =
                (numeroGenerado % 4 == 0 && numeroGenerado % 100 != 0) ||
                        (numeroGenerado % 400 == 0)

            if (!rbSi.isChecked && !rbNo.isChecked) {
                resultado.text = "Debe escoger una de las opciones"
                resultado.setTextColor(getColor(android.R.color.holo_blue_dark))
                return@setOnClickListener
            }


            if ((rbSi.isChecked && esBisiesto) || (rbNo.isChecked && !esBisiesto)) {
                resultado.text = "Correcto"
                resultado.setTextColor(getColor(android.R.color.holo_green_dark))
            } else {
                resultado.text = "Fallaste"
                resultado.setTextColor(getColor(android.R.color.holo_red_dark))
            }
        }

        amarillo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutPrincipal.setBackgroundColor(Color.YELLOW)
            } else {
                layoutPrincipal.setBackgroundColor(Color.WHITE)
            }
        }
    }
}
