package com.example.prohod.data.api

import com.example.prohod.data.model.available_to_visit.AvailableToVisitResponse
import com.example.prohod.data.model.create_user.AccountInfo
import com.example.prohod.data.model.create_user.UserCreateRequest
import com.example.prohod.data.model.create_user.UserCreateResponse
import com.example.prohod.data.model.login.LoginRequest
import com.example.prohod.data.model.login.LoginResponse
import com.example.prohod.data.model.visit.VisitRequest
import com.example.prohod.data.model.visit_requests.VisitRequestsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiHelper {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
    suspend fun sendVisitRequest(form: VisitRequest): Response<Void>
    suspend fun createUser(userCreateRequest: UserCreateRequest): Response<UserCreateResponse>
    suspend fun getAvailableToVisitUser(userId: String): Response<AvailableToVisitResponse>
    suspend fun getVisitRequests(): Response<VisitRequestsResponse>
}