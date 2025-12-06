package com.example.apppeliculas.View.Contracts

import com.example.apppeliculas.Model.clsPeliculas

interface PeliculasContract {
    fun mostrarPeliculas(peliculas : List<clsPeliculas>)
    fun mostrarMensajes(mensaje : String)
}