package com.example.apppeliculas.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apppeliculas.Presenter.LoginPresenter
import com.example.apppeliculas.R
import com.example.apppeliculas.View.Contracts.LoginContract

class login : AppCompatActivity(), LoginContract {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnAcceder: Button
    private lateinit var tvRegistrar: TextView
    private var usuarioPresenter = LoginPresenter(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etEmail = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnAcceder = findViewById(R.id.btnAcceder)
        tvRegistrar = findViewById(R.id.tvRegistrar)

        //Listener para el botón de Login
        btnAcceder.setOnClickListener(this::iniciarSesion)

        //Listener para el texto de registro
        tvRegistrar.setOnClickListener(this::redirigirRegistro)
    }

    fun iniciarSesion(v : View)
    {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        usuarioPresenter.acceder(email, password)
    }

    fun redirigirRegistro(v : View)
    {
        val intent = Intent(this, registro::class.java)
        startActivity(intent)
    }

    override fun redirigirInicio() {
        val intent = Intent(this@login, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun mostrarMensaje(mensaje : String) {
        Toast.makeText(this@login, mensaje, Toast.LENGTH_SHORT).show()
    }
}