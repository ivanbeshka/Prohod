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
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        apiService.login(loginRequest)

    override suspend fun sendVisitRequest(form: VisitRequest): Response<Void> =
        apiService.sendVisitRequest(form)

    override suspend fun createUser(userCreateRequest: UserCreateRequest): Response<UserCreateResponse> =
        apiService.createUser(userCreateRequest)

    override suspend fun getAvailableToVisitUser(userId: String): Response<AvailableToVisitResponse> =
        apiService.getAvailableToVisitUser(userId)

    override suspend fun getVisitRequests(): Response<VisitRequestsResponse> =
        apiService.getVisitRequests()
}