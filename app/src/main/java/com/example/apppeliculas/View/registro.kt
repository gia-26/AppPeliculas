package com.example.apppeliculas.View

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apppeliculas.Presenter.LoginPresenter
import com.example.apppeliculas.Presenter.RegisterPresenter
import com.example.apppeliculas.R
import com.example.apppeliculas.View.Contracts.RegisterContract

class registro : AppCompatActivity(), RegisterContract {
    private lateinit var etNombreUsuario: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPasswordRegistro: EditText
    private lateinit var btnRegistrar: Button

    private val registerPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etEmail = findViewById(R.id.etEmail)
        etPasswordRegistro = findViewById(R.id.etPasswordRegistro)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener(this::registrar)
    }

    fun registrar(v : View)
    {
        val nombreUsuario = etNombreUsuario.text.toString()
        val email = etEmail.text.toString()
        val password = etPasswordRegistro.text.toString()
        registerPresenter.registrar(nombreUsuario, email, password)
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this@registro,mensaje,Toast.LENGTH_SHORT).show()
    }

    override fun volverLogin() {
        finish()
    }
}