package com.example.jugandoalosdados

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var opc1 = arrayOf("Par", "Impar")
    var opc2 = arrayOf("Más", "Menos")
    lateinit var adaptador: ArrayAdapter<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var saldo = findViewById<TextView>(R.id.saldo)
        var parImpar = findViewById<MaterialButton>(R.id.parImpar)
        var masMenos = findViewById<MaterialButton>(R.id.masMenos)
        var hagaApuesta = findViewById<TextInputEditText>(R.id.apuesta)
        var lanzarDados = findViewById<Button>(R.id.lanzarDados)
        var spinner = findViewById<Spinner>(R.id.spinner)
        var xddado1 = findViewById<TextView>(R.id.dado1)
        var xddado2 = findViewById<TextView>(R.id.dado2)
        var imagen = findViewById<ImageView>(R.id.imagen)
        imagen.setImageResource(0)


        var saldoTotal = 100
        saldo.text = saldoTotal.toString()


        parImpar.setOnClickListener {
            if (parImpar.isChecked) {
                adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc1)
                spinner.adapter = adaptador
            }
        }
        masMenos.setOnClickListener {
            if (masMenos.isChecked) {
                adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc2)
                spinner.adapter = adaptador
            }
        }
//--------------------------------------------------------------------------------------------------
        lanzarDados.setOnClickListener {

            Glide.with(this)
                .load(R.drawable.dado_imagen_animada_0092)
                .into(imagen)
            lifecycleScope.launch {

                tiempo(3)


                var apuesta = hagaApuesta.text.toString().toIntOrNull() ?: 0

                if (saldoTotal > 0 && apuesta in 1..saldoTotal) {
                    var dado1 = Random.nextInt(1, 7)
                    var dado2 = Random.nextInt(1, 7)
                    var dadoTotal = dado1 + dado2



                    if (parImpar.isChecked) {
                        if (spinner.selectedItem == "Par") {
                            if (dadoTotal % 2 == 0) {
                                saldoTotal += apuesta
                                imagen.setImageResource(R.drawable.ganar_dados)
                            } else {
                                saldoTotal -= apuesta
                                imagen.setImageResource(R.drawable.perder_dados)

                            }

                            saldo.text = saldoTotal.toString()
                        }
                    }
                    if (parImpar.isChecked) {
                        if (spinner.selectedItem == "Impar") {
                            if (dadoTotal % 2 == 0) {
                                saldoTotal -= apuesta
                                imagen.setImageResource(R.drawable.perder_dados)

                            } else {
                                saldoTotal += apuesta
                                imagen.setImageResource(R.drawable.ganar_dados)

                            }

                            saldo.text = saldoTotal.toString()
                        }
                    }


                    //---------------------------------------------------

                    if (masMenos.isChecked) {
                        if (spinner.selectedItem == "Más") {
                            if (dadoTotal >= 7) {
                                saldoTotal += apuesta
                                imagen.setImageResource(R.drawable.ganar_dados)

                            } else {
                                saldoTotal -= apuesta
                                imagen.setImageResource(R.drawable.perder_dados)

                            }

                            saldo.text = saldoTotal.toString()
                        }
                    }
                    if (masMenos.isChecked) {
                        if (spinner.selectedItem == "Menos") {
                            if (dadoTotal < 7) {
                                saldoTotal += apuesta
                                imagen.setImageResource(R.drawable.ganar_dados)

                            } else {
                                saldoTotal -= apuesta
                                imagen.setImageResource(R.drawable.perder_dados)

                            }

                            saldo.text = saldoTotal.toString()
                        }
                    }

                    xddado1.text = dado1.toString()
                    xddado2.text = dado2.toString()

                }else {
                    imagen.setImageResource(R.drawable.bancarrota)
                    saldo.text = "0"
                }

            }

        }

    }

    suspend fun tiempo(x: Long): Int {
        delay(1000 * x)
        return 13
    }
}
