package com.example.whatsdamlolipop

/**
 * Data class Missatge
 *
 * Esta data class representa un mensaje en la conversación.
 *
 * @property nom El nombre del remitente del mensaje.
 * @property text El texto del mensaje.
 */
data class Missatge(var nom: String, var text: String)

// Variable para almacenar la lista de mensajes
var llistaMissatges = mutableListOf<Missatge>()
