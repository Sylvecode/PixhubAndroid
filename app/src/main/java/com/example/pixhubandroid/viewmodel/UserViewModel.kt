package com.example.pixhubandroid.viewmodel



import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pixhubandroid.model.AccountBean

class UserViewModel : ViewModel() {

    // Déclarer un MutableState pour stocker l'objet AccountBean
    val user = mutableStateOf(AccountBean())


}