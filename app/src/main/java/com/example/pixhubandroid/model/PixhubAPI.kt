package com.example.pixhubandroid.model

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


fun main () {
}

object PixhubAPI {

    private val gson = Gson()
    private val client = OkHttpClient()



    // Méthode pour ajouter un compte en utilisant sendPost
    fun addAccount(account: AccountBean): AccountBean {
        val response = sendPost("http://10.0.2.2:8080/addAccount", account)
        return gson.fromJson(response, AccountBean::class.java)
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
            it.body!!.string()
        }
    }

    fun login(username: String, password: String): AccountBean? {
        val params = mapOf(
            "username" to username,
            "password" to password
        )
        val response = sendPost("http://10.0.2.2:8080/login", params)
        return gson.fromJson(response, AccountBean::class.java)
    }

}
