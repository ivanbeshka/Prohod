package com.example.prohod.data.model.create_user

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val login: String,
    val password: String
)
