package com.example.whatsdamlolipop.repository

import androidx.lifecycle.LiveData
import com.example.whatsdamlolipop.model.CommunicationManager
import com.example.whatsdamlolipop.model.Missatge

class MessagesRepository private constructor() {

    companion object {
        private var instance: MessagesRepository? = null

        fun getInstance(): MessagesRepository {
            if (instance == null) {
                instance = MessagesRepository()
            }
            return instance!!
        }
    }

    private val messages = Missatges()
    private val communicationManager = CommunicationManager()

    var username: String = ""
    var server: String = ""

    fun getMessages(): LiveData<ArrayList<Missatge>> {
        return messages.missatges
    }

    fun getMessage(position: Int): Missatge? {
        return messages.getMessageAt(position)
    }

    fun getNumMessages(): Int {
        return messages.size()
    }

    fun addMessage(username: String, text: String) {
        messages.add(username, text)
    }

    suspend fun sendMessageToServer(message: Missatge) {
        communicationManager.sendMessage(message)
    }

    suspend fun loginToServer(user: String, password: String): String {
        return communicationManager.login(user, password)
    }
}
