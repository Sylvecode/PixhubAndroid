package com.example.pixhubandroid.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {

    val username = mutableStateOf("")
}