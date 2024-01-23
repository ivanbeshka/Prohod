package com.example.prohod.data.model.visit_requests

import com.example.prohod.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class VisitRequest(
    val id: String,
    val form: Form,
    val whoProcessed: User? = null,
    val status: String,
    val rejectionReason: String? = null
)
