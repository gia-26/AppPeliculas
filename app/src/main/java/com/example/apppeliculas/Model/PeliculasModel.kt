package com.example.apppeliculas.Model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeliculasModel {
    private lateinit var apiService: ifaceApiService

    fun inicializarApiService() {
        //Configuracion de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://grupoctic.com/giovanni/peliculas/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerPeliculas(callback: (Boolean, String, List<clsPeliculas>) -> Unit)
    {
        apiService.obtenerPeliculas().enqueue(object: Callback<List<clsPeliculas>>
        {
            override fun onResponse(call: Call<List<clsPeliculas>>, response: Response<List<clsPeliculas>>)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let { peliculas ->
                        callback(true, "Películas recuperadas correctamente", peliculas)
                    }
                }
                else
                {
                    callback(true, "Error: ${response.message()}", emptyList())
                }
            }

            override fun onFailure(call: Call<List<clsPeliculas>?>, t: Throwable) {
                callback(true, "Error: ${t.message}", emptyList())
            }
        })
    }
}