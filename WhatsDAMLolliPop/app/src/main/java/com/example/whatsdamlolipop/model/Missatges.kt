package com.example.whatsdamlolipop.model

import androidx.lifecycle.MutableLiveData

class Missatges {
    private val _missatges = MutableLiveData<ArrayList<Missatge>>().apply {
        value = ArrayList()
    }

    val missatges: MutableLiveData<ArrayList<Missatge>> = _missatges

    fun getMessageAt(position: Int): Missatge? {
        return _missatges.value?.get(position)
    }

    fun add(username: String, text: String) {
        _missatges.value?.add(Missatge(username, text))
        _missatges.postValue(_missatges.value)
    }

    fun sendMessage(message: Missatge) {
        // Implementa la lógica para enviar el mensaje al servidor si es necesario.
    }

    fun size(): Int {
        return _missatges.value?.size ?: 0
    }
}
