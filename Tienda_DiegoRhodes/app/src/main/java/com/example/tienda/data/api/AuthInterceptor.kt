package com.example.tienda.data.api

import android.util.Log
import com.example.tienda.domain.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = SessionManager.token

        Log.d("INTERCEPTOR", "TOKEN EN INTERCEPTOR = $token")

        val request = chain.request().newBuilder()

        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
            Log.d("INTERCEPTOR", "HEADER AÑADIDO")
        } else {
            Log.d("INTERCEPTOR", "TOKEN NULL")
        }

        val finalRequest = request.build()

        Log.d("INTERCEPTOR", "HEADERS = ${finalRequest.headers}")

        return chain.proceed(finalRequest)
    }
}