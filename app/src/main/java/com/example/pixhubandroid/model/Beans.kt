package com.example.pixhubandroid.model

import java.net.ProtocolFamily


fun main () {

}


data class AccountBean(
    var id: Long? = null,
    var username: String = "",
    var familyName: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = ""
)