package mx.edu.potros.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var tvResultado: TextView
    lateinit var tvNumeros: TextView

    var numeroAnterior: Double = 0.0
    var operacionPendiente: String = ""
    var numeroNuevo: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los botones numéricos y operacionales
        val btnSumar: Button = findViewById(R.id.btnSumar)
        val btnRestar: Button = findViewById(R.id.btnRestar)
        val btnMultiplicar: Button = findViewById(R.id.btnMultilicar)
        val btnDividir: Button = findViewById(R.id.btnDivision)
        val btnIgual: Button = findViewById(R.id.btnResult)
        val btnBorrar: Button = findViewById(R.id.btnBorrar)

        // Referencia al TextView donde se mostrará el resultado
        tvResultado = findViewById(R.id.tvResult)
        tvNumeros = findViewById(R.id.tvNumeros)

        // Asignar OnClickListener a cada botón numérico
        val listenerNumeros = View.OnClickListener { view ->
            val numeroSeleccionado = (view as Button).text.toString()
            tvResultado.append(numeroSeleccionado)
            tvNumeros.append(numeroSeleccionado)
        }

        findViewById<Button>(R.id.btnUno).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnDos).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnTres).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnCuatro).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnCinco).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnSeis).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnSiete).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnOcho).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnNueve).setOnClickListener(listenerNumeros)
        findViewById<Button>(R.id.btnCero).setOnClickListener(listenerNumeros)

        // Asignar OnClickListener a los botones de operaciones
        btnSumar.setOnClickListener {
            prepararOperacion("+")
        }

        btnRestar.setOnClickListener {
            prepararOperacion("-")
        }

        btnMultiplicar.setOnClickListener {
            prepararOperacion("*")
        }

        btnDividir.setOnClickListener {
            prepararOperacion("/")
        }

        // Realizar la operación cuando se presiona el botón de igual
        btnIgual.setOnClickListener {
            if (tvResultado.text.isNotEmpty()) {
                numeroNuevo = tvResultado.text.toString().toDouble()
                realizarOperacion()
            }
        }

        // Borrar el contenido cuando se presiona el botón de borrar
        btnBorrar.setOnClickListener {
            tvResultado.text = ""
            numeroAnterior = 0.0
            operacionPendiente = ""
            numeroNuevo = 0.0
            tvNumeros.text =""
        }
    }

    private fun prepararOperacion(operacion: String) {
        if (tvResultado.text.isNotEmpty()) {

            numeroAnterior = tvResultado.text.toString().toDouble()
            operacionPendiente = operacion
            tvResultado.text = ""
            tvNumeros.append(operacion)
        }
    }

    private fun realizarOperacion() {
        when (operacionPendiente) {
            "+" -> tvResultado.text = (numeroAnterior + numeroNuevo).toString()
            "-" -> tvResultado.text = (numeroAnterior - numeroNuevo).toString()
            "*" -> tvResultado.text = (numeroAnterior * numeroNuevo).toString()
            "/" -> {
                if (numeroNuevo != 0.0) {
                    tvResultado.text = (numeroAnterior / numeroNuevo).toString()
                } else {
                    tvResultado.text = "Error"
                }
            }
        }
        // Limpiar variables después de realizar la operación
        numeroAnterior = 0.0
        operacionPendiente = ""
        numeroNuevo = 0.0
    }
}