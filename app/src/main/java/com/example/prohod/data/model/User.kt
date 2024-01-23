package com.example.prohod.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val surname: String,
    val userEmail: String,
    val role: String
)
