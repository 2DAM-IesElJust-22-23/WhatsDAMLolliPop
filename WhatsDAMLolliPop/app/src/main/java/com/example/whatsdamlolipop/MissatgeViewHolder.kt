package com.example.whatsdamlolipop
import android.view.View
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