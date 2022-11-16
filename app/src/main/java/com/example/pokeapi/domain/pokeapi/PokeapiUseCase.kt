package com.example.pokeapi.domain.pokeapi

import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import com.example.pokeapi.domain.utils.Result
import com.example.pokeapi.domain.utils.Error
import com.example.pokeapi.domain.utils.Error.Unknown
import com.example.pokeapi.domain.utils.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.conflate

class PokeapiUseCase(private val pokeapiRepository: PokeapiRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    fun getPokemonList(): Flow<Result<PokemonListResponse, Error>> {
        return flow {
            emit(pokeapiRepository.getPokemonList())
        }
            .catch { emit(Failure(Unknown(message = it.message.toString()))) }
            .flowOn(dispatcher)
            .conflate()
    }

    fun getPokemonInfo(name: String): Flow<Result<PokemonInfoResponse, Error>> {
        return flow {
            emit(pokeapiRepository.getPokemonInfo(name))
        }
            .catch { emit(Failure(Unknown(message = it.message.toString()))) }
            .flowOn(dispatcher)
            .conflate()
    }
}