package com.example.pixhubandroid.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.model.ArtistBean
import com.example.pixhubandroid.model.MediaBean
import com.example.pixhubandroid.model.PixhubAPI
import com.example.pixhubandroid.model.PixhubAPI.search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel () {
    val query = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    val mediaList = mutableStateListOf<MediaBean>()
    val artistList = mutableStateListOf<ArtistBean>()

    fun loadSearchData() {
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val searchResult = search(query) // Appelle la méthode search avec searchName

                launch(Dispatchers.Main) {
                    if (searchResult is MediaBean) {
                        mediaList.add(searchResult)
                    } else if (searchResult is ArtistBean) {
                        artistList.add(searchResult)
                    } else {
                        errorMessage.value = "Aucun résultat trouvé"
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
