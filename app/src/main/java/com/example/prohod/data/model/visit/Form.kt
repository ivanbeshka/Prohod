package com.example.prohod.data.model.visit

import com.example.prohod.data.model.Passport
import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val passport: Passport,
    val visitTime: String,
    val visitReason: String,
    val userToVisitId: String,
    val emailToSendReply: String
)
