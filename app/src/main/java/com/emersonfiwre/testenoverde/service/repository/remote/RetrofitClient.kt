package com.emersonfiwre.testenoverde.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL =
            "https://api.noverde-hmg.net"

        private fun getRetrofitInstance(): Retrofit {
            if (!Companion::retrofit.isInitialized) {
                val httpClient = OkHttpClient.Builder()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}