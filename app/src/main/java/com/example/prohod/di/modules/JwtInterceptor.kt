package com.example.prohod.di.modules

import com.example.prohod.utils.AuthSharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class JwtInterceptor @Inject constructor(
    private val authSharedPref: AuthSharedPref
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        authSharedPref.token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        val buildRequest = request.build()
        return chain.proceed(buildRequest)
    }
}