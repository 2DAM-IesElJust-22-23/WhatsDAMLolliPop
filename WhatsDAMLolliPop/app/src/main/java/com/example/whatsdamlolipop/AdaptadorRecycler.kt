package com.example.whatsdamlolipop
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


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
