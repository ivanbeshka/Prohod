package com.example.prohod.data.model.available_to_visit

import com.example.prohod.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class AvailableToVisitResponse(
    val users: List<User>
)
