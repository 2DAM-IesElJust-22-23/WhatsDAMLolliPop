package com.example.whatsdamlolipop.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.whatsdamlolipop.model.Missatge
import com.example.whatsdamlolipop.repository.MissatgeRepository
/**
 * ViewModel utilizado para gestionar la lógica y los datos relacionados con los mensajes en la aplicación.
 *
 * Esta clase extiende AndroidViewModel y se utiliza para mantener y proporcionar acceso a un adaptador
 * de mensajes (AdaptadorMissatges) que se utiliza en la interfaz de usuario.
 *
 * @param application La instancia de la aplicación de Android.
 */
class MissatgesViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * LiveData que contiene un adaptador de mensajes (AdaptadorMissatges) para mostrar mensajes en la interfaz de usuario.
     * El adaptador se inicializa con una lista vacía al principio.
     */
    private val _adaptador = MutableLiveData<AdaptadorMissatges>().apply {
        value = AdaptadorMissatges(llistaMissatges = emptyList())
    }

    /**
     * El objeto MutableLiveData que contiene una instancia de AdaptadorMissatges.
     */
    val adaptador: MutableLiveData<AdaptadorMissatges> = _adaptador

    /**
     * Agrega un nuevo Missatge al MissatgeRepository y notifica al adaptador del cambio.
     *
     * @param nouMissatge El nuevo Missatge que se va a agregar.
     */
    fun addMissatge(nouMissatge: Missatge) {
        if (MissatgeRepository.getInstance().addMissatge(nouMissatge)) {
            // Notificar al adaptador que los datos han cambiado
            adaptador.value?.notifyItemInserted(MissatgeRepository.getInstance().getNumMissatges() - 1)
        }
    }
}