package com.example.whatsdamlolipop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Clase MissatgeViewHolder
 *
 * Esta clase representa un ViewHolder para un elemento individual en el RecyclerView.
 * Se utiliza para mostrar mensajes en una conversación, con hora y texto del mensaje.
 *
 * @param itemView La vista del elemento individual en el RecyclerView.
 */
class MissatgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Propiedades para las vistas dentro del elemento del RecyclerView
    private val hora: TextView = itemView.findViewById(R.id.msg_me_timestamp)
    private val missatge_text: TextView = itemView.findViewById(R.id.msg_text)

    /**
     * Vincula (bind) los datos del mensaje a las vistas en el ViewHolder.
     *
     * @param missatge El objeto Missatge que se va a mostrar en esta vista.
     */
    fun bind(missatge: Missatge) {
        missatge_text.text = missatge.text

        // Formatea la hora actual en el formato "HH:mm" y la muestra en la vista de hora.
        val dateFormat = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        val horaFormat = dateFormat.format(horaActual)
        hora.text = horaFormat
    }
}

/**
 * Clase AdaptadorRecycler
 *
 * Esta clase es un adaptador para el RecyclerView que maneja la lista de mensajes.
 * Se encarga de inflar las vistas de los elementos y vincular los datos con los ViewHolders.
 *
 * @param llistaMissatges La lista de mensajes que se mostrará en el RecyclerView.
 */
class AdaptadorRecycler(var llistaMissatges: List<Missatge>) : RecyclerView.Adapter<MissatgeViewHolder>() {
    /**
     * Crea un nuevo ViewHolder cuando se necesita.
     *
     * @param parent El ViewGroup en el que se creará la vista.
     * @param viewType El tipo de vista (puede ser ignorado en este caso).
     * @return Un nuevo MissatgeViewHolder que representa un elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissatgeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.my_msg_viewholder, parent, false)
        return MissatgeViewHolder(itemView)
    }

    /**
     * Vincula (bind) los datos del mensaje en una posición específica con un ViewHolder.
     *
     * @param holder El MissatgeViewHolder que se utilizará para mostrar los datos.
     * @param position La posición del mensaje en la lista.
     */
    override fun onBindViewHolder(holder: MissatgeViewHolder, position: Int) {
        val missatge = llistaMissatges[position]
        holder.bind(missatge)
    }

    /**
     * Obtiene el número total de elementos en la lista.
     *
     * @return El número de elementos en la lista de mensajes.
     */
    override fun getItemCount(): Int {
        return llistaMissatges.size
    }
}
