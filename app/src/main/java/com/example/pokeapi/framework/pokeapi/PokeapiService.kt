package com.example.pokeapi.framework.pokeapi

import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

private const val POKEMON_NAME = "POKEMON_NAME"
private const val POKEMON_LIST_PATH = "pokemon"
private const val POKEMON_INFO_PATH = "pokemon/{$POKEMON_NAME}"

interface PokeapiService {

    @GET(POKEMON_LIST_PATH)
    fun getPokemonList(@QueryMap query: Map<String, String>): Call<PokemonListResponse>

    @GET(POKEMON_INFO_PATH)
    fun getPokemonInfo(@Path(POKEMON_NAME) pokemonName: String): Call<PokemonInfoResponse>
}