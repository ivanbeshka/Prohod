package com.example.prohod.di.modules

import com.example.prohod.data.repo.MainRepository
import com.example.prohod.utils.AuthSharedPref
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class JwtAuth @Inject constructor(
    private val authSharedPref: AuthSharedPref,
    private val repository: dagger.Lazy<MainRepository>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val isLogin = repository.get().loginUser()

            if (isLogin) {
                buildRequest(response.request.newBuilder())
            } else {
                null
            }
        }
    }

    private fun buildRequest(requestBuilder: Request.Builder): Request? {
        val token = authSharedPref.token ?: return null

        return requestBuilder
            .header("Authorization", "Bearer $token")
            .build()
    }
}