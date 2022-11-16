package com.example.pokeapi.domain.pokeapi

import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import com.example.pokeapi.domain.utils.Error
import com.example.pokeapi.domain.utils.Result

interface PokeapiRepository {
    fun getPokemonList(): Result<PokemonListResponse, Error>
    fun getPokemonInfo(name: String): Result<PokemonInfoResponse, Error>
}