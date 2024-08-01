package com.android.abhay.jetpack.module

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return synchronized(this) {
            val originalRequest = chain.request()
            val requestBuilder =
                originalRequest.newBuilder().apply {
                    header("Content-Type", "application/json")
                        .header("access-token", "")
                }
            chain.proceed(requestBuilder.build())
        }
    }

}