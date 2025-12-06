package com.example.apppeliculas.View

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppeliculas.Model.clsPeliculas
import com.example.apppeliculas.R
import com.example.apppeliculas.View.VistaDetalle

class peliculaAdaptador(val contexto: Context, val listapeliculas: List<clsPeliculas>) : RecyclerView.Adapter<peliculaAdaptador.PeliculaViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder
    {
        //Se hará el vinculo con el layout pelicula_layout.xml
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pelicula_layout,
            parent,
            false
        )

        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int)
    {
        //Se van a extraer los datos de las listas y los colocamos en cada control de layout
        val pelicula = listapeliculas[position]
        holder.txtNombre.text = pelicula.nombre
        holder.txtDescripcion.text = pelicula.descripcion

        //Cargar la imagen usando Glide
        Glide.with(contexto)
            .load("https://grupoctic.com/giovanni/peliculas/img/" + pelicula.imagenPeli) //Usar la URL de la imagen
            .into(holder.imgPelicula)
        holder.imgPelicula.setOnClickListener {
            verDetalle(pelicula)
        }
    }

    fun verDetalle(pelicula : clsPeliculas)
    {
        //Se llama al activity detalle
        val intent = Intent(contexto, VistaDetalle::class.java).apply {
            putExtra("pelicula_id", pelicula.idPelicula)
            putExtra("pelicula_nombre", pelicula.nombre)
            putExtra("pelicula_descripcion", pelicula.descripcion)
            putExtra("pelicula_sinopsis", pelicula.sinopsis)
            putExtra("pelicula_imagen", pelicula.imagenPeli) //Puede ser opcional si usas Glide
            putExtra("pelicula_video", pelicula.videoPeli)
        }
        contexto.startActivity(intent)
    }

    override fun getItemCount(): Int
    {
        //Se retorna el tamaño de la lista
        return listapeliculas.size;
    }

    class PeliculaViewHolder(control: View) : RecyclerView.ViewHolder(control)
    {
        //Aquí especificamos los controles del layout
        val imgPelicula : ImageView = itemView.findViewById(R.id.imgFoto)
        val txtNombre : TextView = itemView.findViewById(R.id.txtNombre)
        val txtDescripcion : TextView = itemView.findViewById(R.id.txtDescripcion)

    }
}