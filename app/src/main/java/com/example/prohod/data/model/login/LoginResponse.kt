package com.example.prohod.data.model.login

import com.example.prohod.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val user: User,
    val jwtToken: JwtToken
)
