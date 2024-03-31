package com.example.pixhubandroid.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.model.PixhubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val username = mutableStateOf("")
    val password = mutableStateOf("")


    fun login() = liveData(Dispatchers.Default) {
        emit(null) // Émettre null pour montrer que la requête est en cours

        try {
            val account = PixhubAPI.login(username.value, password.value)
            emit(account) // Émettre le résultat de la requête
        } catch (e: Exception) {
            e.printStackTrace()
            // Gérer les erreurs ici
        }
    }

}