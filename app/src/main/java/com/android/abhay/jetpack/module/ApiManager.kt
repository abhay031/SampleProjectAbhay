package com.android.abhay.jetpack.module

import com.android.abhay.jetpack.bases.Urls
import com.android.abhay.jetpack.retrofit.RetrofitCalls
import retrofit2.Retrofit

class ApiManager constructor(
    private val retrofit: Retrofit
) {
    val retrofitApis by lazy {
        retrofit.updateBaseUrl(Urls.API_URL).createApi<RetrofitCalls>()
    }

}


inline fun <reified T> Retrofit.createApi(): T = this.create(T::class.java)
fun Retrofit.updateBaseUrl(baseUrl: String): Retrofit = this.newBuilder().baseUrl(baseUrl).build()