package com.example.prohod.data.model.visit_requests

import com.example.prohod.data.model.Passport
import com.example.prohod.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val id: String,
    val passport: Passport,
    val visitTime: String,
    val visitReason: String,
    val userToVisit: User,
    val emailToSendReply: String
)
