package com.example.whatsdamlolipop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsdamlolipop.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LoginViewModel : ViewModel() {

    private val repository = MessagesRepository.getInstance()

    val loginStatus = MutableLiveData<JSONObject>()

    fun login(username: String, server: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.loginToServer(username, server)
            }

            loginStatus.postValue(JSONObject(result))
        }
    }
}
