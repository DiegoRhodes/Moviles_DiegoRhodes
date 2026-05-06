package com.example.tienda.data.api

import com.example.tienda.domain.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response{
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()

        SessionManager.token?.let{
            newRequest.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(newRequest.build())
    }
}