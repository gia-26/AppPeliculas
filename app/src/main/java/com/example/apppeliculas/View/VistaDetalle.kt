package com.example.apppeliculas.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.apppeliculas.R
import com.example.apppeliculas.View.reproducir

class VistaDetalle : AppCompatActivity() {
    private lateinit var imgFoto : ImageView
    private lateinit var txtNombre : TextView
    private lateinit var txtDescripcion : TextView
    private lateinit var txtSinopsis : TextView
    private lateinit var btnReproducir : Button
    private lateinit var nombrePeli : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgFoto = findViewById(R.id.imgDetalle)
        txtNombre = findViewById(R.id.txtNombreDetalle)
        txtDescripcion = findViewById(R.id.txtDescripcionDetalle)
        txtSinopsis = findViewById(R.id.txtSinopsis)
        btnReproducir = findViewById(R.id.btnReproducir)

        //Obtener los datos de las peliculas del intent
        val peliculaNombre = intent.getStringExtra("pelicula_nombre")
        val peliculaDescripcion = intent.getStringExtra("pelicula_descripcion")
        val peliculaSinopsis = intent.getStringExtra("pelicula_sinopsis")
        val peliculaImagen = intent.getStringExtra("pelicula_imagen")
        nombrePeli = intent.getStringExtra("pelicula_video").toString()

        //Cargar la imagen de la película usando Glide
        Glide.with(this)
            .load("https://grupoctic.com/giovanni/peliculas/img/" + peliculaImagen)
            .into(imgFoto)

        //Asignar los datos a las vistas
        txtNombre.text = peliculaNombre
        txtDescripcion.text = peliculaDescripcion
        txtSinopsis.text = peliculaSinopsis
        btnReproducir.setOnClickListener(this::iraVerPelicula)
    }
    fun iraVerPelicula(v : View)
    {
        val intent = Intent(this, reproducir::class.java).apply {
            putExtra("pelicula_video", nombrePeli) //Puede ser opcional si usas Glide
        }
        this.startActivity(intent)
    }
}