package com.example.apppeliculas.Model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel {
    private lateinit var apiService: ifaceApiService

    fun inicializarApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://grupoctic.com/giovanni/peliculas/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun acceder(email: String, password: String, callback: (Boolean, String) -> Unit) {
        apiService.iniciarSesion(action = "login", email = email, password = password)
            .enqueue(object : Callback<List<clsDatosRespuesta>> {
                override fun onResponse(
                    call: Call<List<clsDatosRespuesta>>,
                    response: Response<List<clsDatosRespuesta>>
                ) {
                    if (response.isSuccessful) {
                        val datos = response.body()
                        if (!datos.isNullOrEmpty() && datos[0].Estado == "Correcto") {
                            callback(true, "Inicio de sesión correcto")
                        } else {
                            callback(false, "No se encontraron datos del usuario.")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        callback(false, "Error en la respuesta del servidor: $errorBody")
                    }
                }

                override fun onFailure(call: Call<List<clsDatosRespuesta>>, t: Throwable) {
                    callback(false, "Error en la conexión: ${t.message}")
                }
            })
    }
}