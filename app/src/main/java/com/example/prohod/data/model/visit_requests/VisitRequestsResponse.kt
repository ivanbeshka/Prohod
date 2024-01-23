package com.example.prohod.data.model.visit_requests

import kotlinx.serialization.Serializable

@Serializable
data class VisitRequestsResponse(
    val visitRequests: ArrayList<VisitRequest> = arrayListOf()
)
