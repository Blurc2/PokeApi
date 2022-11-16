package com.example.pokeapi

import android.app.Application
import com.example.pokeapi.framework.ServiceLocator

private const val BASE_URL = "https://pokeapi.co/api/v2/"

class PokeapiApplication: Application() {

    companion object {
        val serviceLocator by lazy {
            ServiceLocator(BASE_URL)
        }
    }
}