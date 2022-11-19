package com.example.vasilyuk

data class Contact(
    var id: Long? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val phoneNumber: String
)