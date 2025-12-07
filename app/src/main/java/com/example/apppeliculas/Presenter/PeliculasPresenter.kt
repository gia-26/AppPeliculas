package com.example.apppeliculas.Presenter

import com.example.apppeliculas.Model.PeliculasModel
import com.example.apppeliculas.View.Contracts.PeliculasContract

class PeliculasPresenter (val vista : PeliculasContract) {
    val modelo = PeliculasModel()
    fun recuperarPeliculas()
    {
        modelo.inicializarApiService()
        modelo.obtenerPeliculas { exito, mensaje, peliculas ->
            if (exito) {
                vista.mostrarMensajes(mensaje)
                vista.mostrarPeliculas(peliculas)
            } else {
                vista.mostrarMensajes(mensaje)
            }
        }
    }

}