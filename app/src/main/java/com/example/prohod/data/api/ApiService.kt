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

interface ApiService {
    @POST("accounts/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("visit-requests/apply")
    suspend fun sendVisitRequest(@Body form: VisitRequest): Response<Void>

    @POST("accounts/users/create")
    suspend fun createUser(@Body userCreateRequest: UserCreateRequest): Response<UserCreateResponse>

    @GET("users/available-to-visit/{id}")
    suspend fun getAvailableToVisitUser(@Path("id") userId: String): Response<AvailableToVisitResponse>

    @GET("visit-requests?offset=0&limit=100")
    suspend fun getVisitRequests(): Response<VisitRequestsResponse>
}