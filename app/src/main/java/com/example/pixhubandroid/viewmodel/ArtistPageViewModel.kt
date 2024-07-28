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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtistPageViewModel : ViewModel() {

    var mediaList = mutableStateListOf<MediaBean>()
    var artistsList = mutableStateListOf<ArtistBean>()
    var errorMessage = mutableStateOf("")


    fun loadArtistDetails(artistId: Int) {
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Appel à getMovieDetails avec mediaId
                val artistBean = PixhubAPI.getArtistDetails(artistId)

                launch(Dispatchers.Main) {
                    if (artistBean != null) {
                        // Assurez-vous que vous gérez le MediaBean reçu correctement
                        artistsList.clear() // Remplacez la liste actuelle si nécessaire
                        artistsList.add(artistBean)
                    } else {
                        // Gérer le cas où mediaBean est null
                        errorMessage.value = "Détails de l'artiste non trouvés"
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
    fun loadArtistMovies(artistId: Int?) {
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            // Vérifier si artistId est null
            if (artistId == null) {
                Log.d("CalendarViewModel", "Artist ID is null")
                launch(Dispatchers.Main) {
                    errorMessage.value = "Artist ID cannot be null."
                }
                return@launch
            }

            try {
                // Log the artistId
                Log.d("ArtistPageViewModel", "Requesting movies for artist ID: $artistId")

                val mediaResponse = PixhubAPI.getArtistMovies(artistId)
                val mediaJson = Gson().toJson(mediaResponse) // Convertir en JSON
                Log.d("ArtistPageViewModel", "Media Response JSON: $mediaJson")  // Log the JSON

                if (mediaResponse != null && mediaResponse.results != null) {
                    Log.d("ArtistPageViewModel", "Number of artists: ${mediaResponse.cast!!.size}")
                } else {
                    Log.d("ArtistPageViewModel", "Media Response or result is null")
                }
                launch(Dispatchers.Main) {
                    if (mediaResponse != null) {
                        mediaResponse.cast?.let { mediaList.addAll(it) }
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