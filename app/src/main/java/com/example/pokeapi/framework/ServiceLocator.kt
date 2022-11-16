package com.example.pokeapi.framework

import com.example.pokeapi.framework.pokeapi.PokeapiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

class ServiceLocator(private val baseUrl: String) {

    private val apiClient by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun providePokeapiService(): PokeapiService = apiClient.create(PokeapiService::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}