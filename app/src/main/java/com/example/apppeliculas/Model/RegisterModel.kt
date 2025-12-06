package com.example.apppeliculas.Model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterModel {
    private lateinit var apiService: ifaceApiService

    fun inicializarApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://grupoctic.com/giovanni/peliculas/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun registrarUsuario(nombreUsuario: String, email: String, password: String, callback: (Boolean, String) -> Unit) {
        apiService.registrarUsuario(action = "registrar",nombreusuario=nombreUsuario, email = email, password = password).enqueue(object :
            Callback<List<clsDatosRespuesta>> {
            override fun onResponse(call: Call<List<clsDatosRespuesta>>, response: Response<List<clsDatosRespuesta>>)
            {
                if (response.isSuccessful) {
                    response.body()?.let { datos ->
                        if (datos[0].Estado == "true") {
                            // Si el registro es exitoso, regresar al login
                            callback(true, "Registro exitoso: " + datos[0].Salida)
                        } else {
                            callback(true, "Registro exitoso: " + datos[0].Salida)
                        }
                    } ?: run {
                        callback(true, "Respuesta vacía o en formato incorrecto")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    callback(true, "Error en la respuesta del servidor: $errorBody")
                }
            }
            override fun onFailure(call: Call<List<clsDatosRespuesta>>, t: Throwable) {
                callback(true, "Error en la conexión: ${t.message}")
            }
        })
    }
}