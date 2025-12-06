package com.example.apppeliculas.Presenter

import com.example.apppeliculas.Model.RegisterModel
import com.example.apppeliculas.View.Contracts.RegisterContract

class RegisterPresenter (val vista : RegisterContract) {
    val modelo = RegisterModel()

    fun registrar(nombreUsuario : String, email: String, password : String)
    {
        modelo.inicializarApiService()
        modelo.registrarUsuario(nombreUsuario, email, password) { exito, mensaje ->
            if (exito) {
                vista.mostrarMensaje(mensaje)
                vista.volverLogin()
            } else {
                vista.mostrarMensaje(mensaje)
            }
        }
    }
}