package com.example.pixhubandroid.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.ArtistBean
import com.example.pixhubandroid.model.MediaBean
import com.example.pixhubandroid.model.PixhubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel () {
    val searchName = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    val mediaList = mutableStateListOf<MediaBean>()
    val artistList = mutableStateListOf<ArtistBean>()

    fun loadSearchData() {
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val searchResult = PixhubAPI.search(searchName)

                launch(Dispatchers.Main) {
                    when (searchResult) {
                        is MediaBean -> mediaList.add(searchResult)
                        is ArtistBean -> artistList.add(searchResult)
                        else -> errorMessage.value = "Aucun résultat trouvé"
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
