package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNum1 = findViewById<EditText>(R.id.num1)
        val etNum2 = findViewById<EditText>(R.id.num2)

        val btnSuma = findViewById<Button>(R.id.suma)
        val btnResta = findViewById<Button>(R.id.resta)
        val btnMulti = findViewById<Button>(R.id.multiplicacion)
        val btnDiv = findViewById<Button>(R.id.division)

        val tvResultado = findViewById<TextView>(R.id.operacion)

        btnSuma.setOnClickListener {
            val n1 = etNum1.text.toString().toInt()
            val n2 = etNum2.text.toString().toInt()
            tvResultado.text = (n1 + n2).toString()
        }

        btnResta.setOnClickListener {
            val n1 = etNum1.text.toString().toInt()
            val n2 = etNum2.text.toString().toInt()
            tvResultado.text = (n1 - n2).toString()
        }

        btnMulti.setOnClickListener {
            val n1 = etNum1.text.toString().toInt()
            val n2 = etNum2.text.toString().toInt()
            tvResultado.text = (n1 * n2).toString()
        }

        btnDiv.setOnClickListener {
            val n1 = etNum1.text.toString().toInt()
            val n2 = etNum2.text.toString().toInt()
            tvResultado.text = (n1.toDouble() / n2).toString()
        }
    }
}
