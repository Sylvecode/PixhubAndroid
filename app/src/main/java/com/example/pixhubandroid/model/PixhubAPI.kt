package com.example.pixhubandroid.model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody


fun main () {
}

object PixhubAPI {

    private val gson = Gson()
    private val client = OkHttpClient()
    val searchName = mutableStateOf("")



    suspend fun login(context: Context, username: String, password: String): AccountBean? {
        return withContext(Dispatchers.IO) {
            val params = mapOf(
                "username" to username,
                "password" to password
            )
            val response = sendPost("http://10.0.2.2:8080/login", params)
            val account =  gson.fromJson(response, AccountBean::class.java)
            if (account != null) {
                // Stocker l'utilisateur dans SharedPreferences
                SessionManager.saveAccount(context, account)
            }
            account
        }
    }


    // Méthode pour ajouter un compte en utilisant sendPost
    fun addAccount(context: Context, account: AccountBean): AccountBean {
        val response = sendPost("http://10.0.2.2:8080/addAccount", account)
        val accountJson =  gson.fromJson(response, AccountBean::class.java)
        if (accountJson != null) {
            // Stocker l'utilisateur dans SharedPreferences
            SessionManager.saveAccount(context, account)
        }
        return accountJson
    }

    fun updateAccount(context: Context, account: AccountBean): AccountBean {
        val response = sendPatch("http://10.0.2.2:8080/updateAccount", account)
        val accountJson =  gson.fromJson(response, AccountBean::class.java)
        if (accountJson != null) {
            // Stocker l'utilisateur dans SharedPreferences
            SessionManager.logout(context)
            SessionManager.saveAccount(context, account)
        }
        return accountJson
    }

    fun deleteAccount(context: Context, account: AccountBean) {
        val response = sendDelete("http://10.0.2.2:8080/deleteAccount", account)
        // Stocker l'utilisateur dans SharedPreferences
        SessionManager.logout(context)
    }


    fun getUpcomingMovies(): MediaResponse? {
        val response = sendGet("http://10.0.2.2:8080/upcomingMovies")
        return gson.fromJson(response, MediaResponse::class.java)
    }

    fun getMovieDetails(mediaId: Int): MediaBean? {
        val url = "http://10.0.2.2:8080/getMovieDetails?movieId=$mediaId"
        val response = sendGet(url)

        return gson.fromJson(response, MediaBean::class.java)
    }


    fun getArtistDetails(artistId: Int): ArtistBean? {
        val url = "http://10.0.2.2:8080/getArtistDetails?artistId=$artistId"
        val response = sendGet(url)

        return gson.fromJson(response, ArtistBean::class.java)
    }

    fun getMovieArtists(mediaId: Int): ArtistResponse? {
        val url = "http://10.0.2.2:8080/getMovieArtists?movieId=$mediaId"
        val response = sendGet(url)

        return gson.fromJson(response, ArtistResponse::class.java)
    }

    fun getArtistMovies(artistId: Int): MediaResponse? {
        println("Received request for artistId: $artistId")
        val url = "http://10.0.2.2:8080/getArtistMovies?artistId=$artistId"
        val response = sendGet(url)

        return gson.fromJson(response, MediaResponse::class.java)
    }


    fun getRecentGames(): MediaResponse? {
        val url = "http://10.0.2.2:8080/getRecentGames"
        val response = sendGet(url)
        return gson.fromJson(response, MediaResponse::class.java)
    }

    fun search(query: MutableState<String>): Any? {
        val url = "http://10.0.2.2:8080/search?query=$query"
        val response = sendGet(url)

        // Vous devrez connaître la structure de votre réponse pour déterminer comment différencier MediaBean de ArtistBean
        val jsonResponse = JsonParser.parseString(response).asJsonObject

        return when {
            jsonResponse.has("movies") -> gson.fromJson(jsonResponse.get("movies"), MediaBean::class.java)
            jsonResponse.has("persons") -> gson.fromJson(jsonResponse.get("persons"), ArtistBean::class.java)
            else -> null
        }
    }

    private fun sendGet(url: String): String {
        println("url : $url")
        // Création de la requête GET
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        // Exécution de la requête
        return client.newCall(request).execute().use { // it: Response
            // 'use' permet de fermer la réponse qu'il y ait ou non une exception
            // Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect : ${it.code}")
            }
            // Résultat de la requête
            it.body.string()
        }
    }



    private fun sendPost(url: String, requestBody: Any): String {
        println("url : $url")
        val jsonBody = gson.toJson(requestBody)
        // Création du corps de la requête
        val body = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())
        // Création de la requête POST
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        // Exécution de la requête
        return client.newCall(request).execute().use { // it: Response
            // 'use' permet de fermer la réponse qu'il y ait ou non une exception
            // Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect : ${it.code}")
            }
            // Résultat de la requête
            it.body.string()
        }
    }

    private fun sendPatch(url: String, requestBody: Any): String {
        println("url : $url")
        val jsonBody = gson.toJson(requestBody)
        // Création du corps de la requête
        val body = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())
        // Création de la requête POST
        val request = Request.Builder()
            .url(url)
            .patch(body)
            .build()

        // Exécution de la requête
        return client.newCall(request).execute().use { // it: Response
            // 'use' permet de fermer la réponse qu'il y ait ou non une exception
            // Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect : ${it.code}")
            }
            // Résultat de la requête
            it.body.string()
        }
    }


    private fun sendDelete(url: String, requestBody: Any): String {
        println("url : $url")
        val jsonBody = gson.toJson(requestBody)
        // Création du corps de la requête
        val body = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())
        // Création de la requête DELETE
        val request = Request.Builder()
            .url(url)
            .delete(body)
            .build()

        // Exécution de la requête
        return client.newCall(request).execute().use { // it: Response
            // 'use' permet de fermer la réponse qu'il y ait ou non une exception
            // Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect : ${it.code}")
            }
            // Résultat de la requête
            it.body.string()
        }
    }

}
