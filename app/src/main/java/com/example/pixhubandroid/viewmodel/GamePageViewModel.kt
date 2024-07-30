package com.example.pixhubandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.ArtistBean
import com.example.pixhubandroid.model.ArtistResponse
import com.example.pixhubandroid.model.MediaBean
import com.example.pixhubandroid.model.MediaResponse
import com.example.pixhubandroid.model.PixhubAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamePageViewModel : ViewModel() {

    var gameList = mutableStateListOf<MediaBean>()
    var errorMessage = mutableStateOf("")


    fun loadRecentGames() {

        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val mediaResponse = PixhubAPI.getRecentGames()
                launch(Dispatchers.Main) {
                    if (mediaResponse != null) {
                        mediaResponse.results?.let { gameList.addAll(it) }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    errorMessage.value = "Erreur : ${e.message}"
                }
            }
        }
    }

}