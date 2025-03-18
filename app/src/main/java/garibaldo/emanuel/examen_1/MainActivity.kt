package garibaldo.emanuel.examen_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración de los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los elementos de la pantalla
        tvWelcome = findViewById(R.id.tv_welcome)
        etNombre = findViewById(R.id.et_nombre)
        etPeso = findViewById(R.id.et_peso)
        etAltura = findViewById(R.id.et_altura)
        btnCalcular = findViewById(R.id.btn_calcular)

        // Configuración del botón calcular
        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            val pesoStr = etPeso.text.toString()
            val alturaStr = etAltura.text.toString()

            if (nombre.isNotEmpty() && pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
                val peso = pesoStr.toDoubleOrNull()
                val altura = alturaStr.toDoubleOrNull()

                if (peso != null && altura != null && altura > 0) {
                    // Pasar datos a la segunda actividad
                    val intent = Intent(this, Pantalla_Resultado::class.java).apply {
                        putExtra("nombre", nombre)
                        putExtra("peso", peso)
                        putExtra("altura", altura)
                    }
                    startActivity(intent)
                } else {
                    tvWelcome.text = "Por favor ingresa valores válidos para peso y altura."
                }
            } else {
                tvWelcome.text = "Por favor completa todos los campos."
            }
        }
    }

    // Limpiar los campos cuando la actividad regrese a primer plano
    override fun onResume() {
        super.onResume()
        etNombre.text.clear()  // Borra el texto ingresado en el nombre
        etPeso.text.clear()    // Borra el texto ingresado en el peso
        etAltura.text.clear()  // Borra el texto ingresado en la altura
    }
}
