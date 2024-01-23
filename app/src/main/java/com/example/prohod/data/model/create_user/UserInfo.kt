package com.example.prohod.data.model.create_user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val name: String,
    val surname: String,
    val userEmail: String
)
