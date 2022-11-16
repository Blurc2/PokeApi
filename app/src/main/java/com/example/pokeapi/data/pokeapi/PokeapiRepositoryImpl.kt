package com.example.pokeapi.data.pokeapi

import com.example.pokeapi.domain.pokeapi.PokeapiRepository
import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import com.example.pokeapi.domain.utils.Error
import com.example.pokeapi.domain.utils.Result

class PokeapiRepositoryImpl(private val pokeapiDataSource: PokeapiDataSource): PokeapiRepository {

    override fun getPokemonList(): Result<PokemonListResponse, Error> = pokeapiDataSource.getPokemonList()

    override fun getPokemonInfo(name: String): Result<PokemonInfoResponse, Error> = pokeapiDataSource.getPokemonInfo(name)
}