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


class CalendarViewModel : ViewModel() {

    var list = mutableStateListOf<MediaBean>()
    var artistsList = mutableStateListOf<ArtistBean>()
    var errorMessage = mutableStateOf("")
    private val gson = Gson()


    fun loadData() {

        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val mediaResponse = PixhubAPI.getUpcomingMovies()
                //val medias = gson.fromJson(mediaResponse?.results, MediaBean::class.java).toList
                launch(Dispatchers.Main) {
                    if (mediaResponse != null) {
                        mediaResponse.results?.let { list.addAll(it) }
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

    fun loadMovieDetails(mediaId: Int) {
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Appel à getMovieDetails avec mediaId
                val mediaBean = PixhubAPI.getMovieDetails(mediaId)

                launch(Dispatchers.Main) {
                    if (mediaBean != null) {
                        // Assurez-vous que vous gérez le MediaBean reçu correctement
                        list.clear() // Remplacez la liste actuelle si nécessaire
                        list.add(mediaBean)
                    } else {
                        // Gérer le cas où mediaBean est null
                        errorMessage.value = "Détails du film non trouvés"
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




    fun loadMovieArtists(mediaId: Int) {

        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val artistResponse = PixhubAPI.getMovieArtists(mediaId)
                val artistJson = Gson().toJson(artistResponse) // Convertir en JSON
                Log.d("CalendarViewModel", "Artist Response JSON: $artistJson")  // Log the JSON

                if (artistResponse != null && artistResponse.cast != null) {
                    Log.d("CalendarViewModel", "Number of artists: ${artistResponse.cast!!.size}")
                } else {
                    Log.d("CalendarViewModel", "Artist Response or cast is null")
                }
                launch(Dispatchers.Main) {
                    if (artistResponse != null) {
                        artistResponse.cast?.let { artistsList.addAll(it) }
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

