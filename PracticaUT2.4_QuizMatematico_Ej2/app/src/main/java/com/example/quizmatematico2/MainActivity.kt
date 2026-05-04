package com.example.quizmatematico2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var numeroGenerado = 0

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var num = findViewById<TextView>(R.id.num)
        var genNum = findViewById<Button>(R.id.genNum)
        var div2 = findViewById<CheckBox>(R.id.div2)
        var div3 = findViewById<CheckBox>(R.id.div3)
        var div5 = findViewById<CheckBox>(R.id.div5)
        var div10 = findViewById<CheckBox>(R.id.div10)
        var noDiv = findViewById<CheckBox>(R.id.noDiv)
        var comprobar = findViewById<Button>(R.id.comprobar)
        var respuesta = findViewById<TextView>(R.id.respuesta)



        genNum.setOnClickListener {
            numeroGenerado = Random.nextInt(1000, 2000)
            num.text = numeroGenerado.toString()
        }

        comprobar.setOnClickListener {

            if (!div2.isChecked && !div3.isChecked && !div5.isChecked &&
                !div10.isChecked && !noDiv.isChecked) {

                respuesta.text = "Debe escoger al menos una de las opciones"
                return@setOnClickListener
            }

            var esDiv2 = numeroGenerado % 2 == 0
            var esDiv3 = numeroGenerado % 3 == 0
            var esDiv5 = numeroGenerado % 5 == 0
            var esDiv10 = numeroGenerado % 10 == 0

            var correcto = true

            if (div2.isChecked && !esDiv2) correcto = false
            if (div3.isChecked && !esDiv3) correcto = false
            if (div5.isChecked && !esDiv5) correcto = false
            if (div10.isChecked && !esDiv10) correcto = false

            if (noDiv.isChecked &&
                (esDiv2 || esDiv3 || esDiv5 || esDiv10)) {
                correcto = false
            }

            if (correcto) {
                respuesta.text = "Correcto"
            } else {
                respuesta.text = "Error"
            }
        }


    }
}