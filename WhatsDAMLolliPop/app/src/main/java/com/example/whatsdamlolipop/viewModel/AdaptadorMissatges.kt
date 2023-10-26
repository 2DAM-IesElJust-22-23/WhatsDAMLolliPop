package com.example.whatsdamlolipop.viewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsdamlolipop.R
import com.example.whatsdamlolipop.model.Missatge
import com.example.whatsdamlolipop.repository.MissatgeRepository

/**
 * Clase AdaptadorRecycler
 *
 * Esta clase es un adaptador para el RecyclerView que maneja la lista de mensajes.
 * Se encarga de inflar las vistas de los elementos y vincular los datos con los ViewHolders.
 *
 * @param llistaMissatges La lista de mensajes que se mostrará en el RecyclerView.
 */
class AdaptadorMissatges(var llistaMissatges: List<Missatge>) : RecyclerView.Adapter<MissatgeViewHolder>() {
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
     * Modifica el adaptador para acceder a través del repositorio a los mensajes.
     *
     * @param holder El ViewHolder en el que se mostrará el mensaje.
     * @param position La posición del mensaje en la lista.
     */
    override fun onBindViewHolder(holder: MissatgeViewHolder, position: Int) {
        (holder as MissatgeViewHolder).bind(
            MissatgeRepository.getInstance().getLlistaMissatges()[position]
        )

    }

    /**
     * Obtiene el número total de mensajes en el repositorio.
     *
     * @return El número total de mensajes.
     */
    override fun getItemCount(): Int {
        return MissatgeRepository.getInstance().getNumMissatges()
    }
}
