package garibaldo.emanuel.examen_1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Pantalla_Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_resultado)

        // Configurar los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referenciar los elementos de la interfaz
        val tvResultIMC: TextView = findViewById(R.id.tv_result_imc)
        val tvRecommendation: TextView = findViewById(R.id.tv_recommendation)
        val imgGIF: ImageView = findViewById(R.id.img_gif)
        val btnRegresar: Button = findViewById(R.id.btn_regresar)

        // Obtener los datos enviados desde MainActivity
        val nombre = intent.getStringExtra("nombre")
        val peso = intent.getDoubleExtra("peso", 0.0)
        val altura = intent.getDoubleExtra("altura", 0.0)

        // Calcular el IMC
        val imc = if (altura > 0) peso / (altura * altura) else 0.0

        // Mostrar el resultado del IMC
        tvResultIMC.text = "IMC: %.2f".format(imc)

        // Determinar recomendaciones y cargar el GIF correspondiente según el rango del IMC
        when {
            imc < 18.5 -> {
                tvRecommendation.text = "Hola $nombre, tu IMC indica bajo peso. Es recomendable consultar a un profesional para una dieta equilibrada."
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_bajo_peso)
                    .into(imgGIF)
            }
            imc in 18.5..24.9 -> {
                tvRecommendation.text = "Hola $nombre, ¡estás en el rango de peso normal! Sigue manteniendo un estilo de vida saludable."
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_peso_normal)
                    .into(imgGIF)
            }
            imc in 25.0..29.9 -> {
                tvRecommendation.text = "Hola $nombre, tu IMC indica sobrepeso. Considera aumentar la actividad física y mantener una dieta balanceada."
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_sobrepeso)
                    .into(imgGIF)
            }
            else -> {
                tvRecommendation.text = "Hola $nombre, tu IMC indica obesidad. Es importante buscar orientación médica para mejorar tu salud."
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_obesidad)
                    .into(imgGIF)
            }
        }
        // Configurar el botón para regresar a MainActivity
        btnRegresar.setOnClickListener {
            finish() // Finaliza la actividad actual y regresa a la anterior
        }
    }
}