package com.example.pixhubandroid.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.model.PixhubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PixhubViewModel : ViewModel() {

    val usernameText = mutableStateOf("")
    val familyNameText = mutableStateOf("")
    val nameText = mutableStateOf("")
    val emailText = mutableStateOf("")
    val passwordText = mutableStateOf("")
    val passwordConfirmText = mutableStateOf("")


    // Fonction pour ajouter un compte
    fun addAccount(
        username: String,
        familyName: String,
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ) {
        if (password != passwordConfirm) {
            throw Exception("Les mots de passe doivent être identiques")
        } else {
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    val account = AccountBean(
                        username = username,
                        familyName = familyName,
                        name = name,
                        email = email,
                        password = password
                    )
                    PixhubAPI.addAccount(account)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }
}