package com.example.apppeliculas.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppeliculas.Model.clsPeliculas
import com.example.apppeliculas.Presenter.PeliculasPresenter
import com.example.apppeliculas.R
import com.example.apppeliculas.View.Contracts.PeliculasContract

class MainActivity : AppCompatActivity(), PeliculasContract {
    private lateinit var rcvLista : RecyclerView
    private val peliculasPresenter = PeliculasPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rcvLista = findViewById(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        peliculasPresenter.recuperarPeliculas()
    }

    override fun mostrarPeliculas(peliculas : List<clsPeliculas>) {
        val adaptador = peliculaAdaptador(this@MainActivity, peliculas)
        rcvLista.adapter = adaptador
    }

    override fun mostrarMensajes(mensaje: String) {
        Toast.makeText(baseContext, mensaje, Toast.LENGTH_SHORT).show()
    }
}