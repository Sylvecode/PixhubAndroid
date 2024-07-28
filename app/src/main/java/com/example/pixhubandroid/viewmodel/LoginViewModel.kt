package com.example.pixhubandroid.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.model.PixhubAPI
import com.example.pixhubandroid.model.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val username = mutableStateOf("")
    val password = mutableStateOf("")


    fun login(context: Context, userViewModel: UserViewModel) {
        viewModelScope.launch {
            try {
                val account = PixhubAPI.login(context, username.value, password.value)
                if (account != null) {
                    userViewModel.setAccount(account) // Mettre à jour le UserViewModel
                    SessionManager.saveAccount(context, account)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Gérer les erreurs ici
            }
        }
    }

}