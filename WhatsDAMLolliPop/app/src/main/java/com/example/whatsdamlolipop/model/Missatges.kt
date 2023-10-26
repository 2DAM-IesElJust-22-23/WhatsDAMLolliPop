package com.example.whatsdamlolipop.model;

/**
 * Objeto que representa una lista de mensajes.
 */
object Missatges {
         var llistaMissatges = mutableListOf<Missatge>()

        /**
         * Obtiene la lista de mensajes.
         *
         * @return La lista de mensajes.
         */
        fun getMissatges(): List<Missatge> = llistaMissatges

        /**
         * Obtiene el número total de mensajes.
         *
         * @return El número total de mensajes.
         */
        fun getNumMissatges() = llistaMissatges.size

        /**
         * Agrega un nuevo mensaje a la lista de mensajes.
         *
         * @param missatgeM El nuevo mensaje a agregar.
         * @return `true` si el mensaje se agregó correctamente, `false` en caso contrario.
         */
        fun addMissatge(missatgeM: Missatge) = llistaMissatges.add(missatgeM)
}
