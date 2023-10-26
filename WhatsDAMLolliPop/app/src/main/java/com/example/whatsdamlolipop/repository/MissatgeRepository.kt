package com.example.whatsdamlolipop.repository

import com.example.whatsdamlolipop.model.Missatge
import com.example.whatsdamlolipop.model.Missatges

/**
 * Clase que se encarga de gestionar los mensajes en el repositorio.
 */
class MissatgeRepository private constructor() {

    /**
     * Obtiene una instancia única de MissatgeRepository.
     *
     * @return La instancia única de MissatgeRepository.
     */
    companion object {
        private var INSTANCE: MissatgeRepository? = null

        fun getInstance(): MissatgeRepository {
            if (INSTANCE == null){
                INSTANCE = MissatgeRepository()
            }
            return  INSTANCE!!
        }
    }

    private val missatges = Missatges

    /**
     * Obtiene la lista de mensajes del repositorio.
     *
     * @return La lista de mensajes.
     */
    fun getLlistaMissatges() = missatges.getMissatges()

    /**
     * Obtiene el número total de mensajes en el repositorio.
     *
     * @return El número total de mensajes.
     */
    fun getNumMissatges() = missatges.getNumMissatges()

    /**
     * Agrega un nuevo mensaje al repositorio.
     *
     * @param nouMissatge El nuevo mensaje a agregar.
     * @return `true` si el mensaje se agregó correctamente, `false` en caso contrario.
     */
    fun addMissatge(nouMissatge: Missatge) = missatges.addMissatge(nouMissatge)
}