package com.example.prohod.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class JwtToken(
    val value: String
)
