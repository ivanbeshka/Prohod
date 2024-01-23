package com.example.prohod.data.model.create_user

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateResponse(
    val credentials: Credentials
)
