package com.example.apppeliculas.Presenter

import com.example.apppeliculas.Model.LoginModel
import com.example.apppeliculas.View.Contracts.LoginContract

class LoginPresenter (val vista : LoginContract) {
    val modelo = LoginModel()

    fun acceder(email : String, password : String)
    {
        modelo.inicializarApiService()
        modelo.acceder(email, password) { exito, mensaje ->
            if (exito) {
                //Registro exitoso
                vista.mostrarMensaje(mensaje)
                vista.redirigirInicio()
            } else {
                //Registro fallido
                vista.mostrarMensaje(mensaje)
            }
        }
    }

}