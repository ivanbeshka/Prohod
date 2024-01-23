package com.example.prohod.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Passport(
    val fullName: String,
    val series: String,
    val number: String,
    val whoIssued: String,
    val issueDate: String
)
