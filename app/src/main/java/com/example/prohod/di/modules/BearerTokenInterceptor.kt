package com.example.prohod.di.modules

import com.example.prohod.utils.AuthSharedPref
import com.example.prohod.utils.INeedTokenUpdate
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject

class BearerTokenInterceptor @Inject constructor(
//    private val authSharedPref: AuthSharedPref,
//    private val needTokenUpdate: dagger.Lazy<INeedTokenUpdate>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
//        authSharedPref.token?.let {
//            request.addHeader("Authorization", "Bearer $it")
//        }
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiNzVmNWI1YWUtODU0Ny00MDRiLTlmZTQtYTBhODgxYzJlMTdmIiwiZXhwIjoxNzA3MjE4NDEwLCJpc3MiOiJwcm9ob2QtYmFja2VuZCIsImF1ZCI6InByb2hvZC1mcm9udGVuZCJ9.2NqDwt0NPG7c3rpVfiJWFZkq-llqsrlzOIFUoo9yerk")
        val buildRequest = request.build()
        val response = chain.proceed(buildRequest)

//        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED && authSharedPref.token != null) {
//            needTokenUpdate.get().updateToken()
//        }

        return response
    }
}