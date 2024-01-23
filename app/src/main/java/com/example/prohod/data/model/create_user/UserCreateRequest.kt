package com.example.prohod.data.model.create_user

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateRequest(
    val accountInfo: AccountInfo
)
