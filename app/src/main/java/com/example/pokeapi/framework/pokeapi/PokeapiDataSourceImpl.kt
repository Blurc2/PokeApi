package com.example.pokeapi.framework.pokeapi

import com.example.pokeapi.data.pokeapi.PokeapiDataSource
import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import com.example.pokeapi.domain.utils.Error
import com.example.pokeapi.domain.utils.Failure
import com.example.pokeapi.domain.utils.Result
import com.example.pokeapi.domain.utils.Success
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

class PokeapiDataSourceImpl(private val pokeapiService: PokeapiService) : PokeapiDataSource {

    private val pokemonListQuery = mapOf("limit" to "10000", "offset" to "0")

    override fun getPokemonList(): Result<PokemonListResponse, Error> {
        return handleRequest {
            val list = pokeapiService.getPokemonList(pokemonListQuery).execute().body()

            list?.let { Success(it) } ?: Failure(Error.Unknown())
        }
    }

    override fun getPokemonInfo(name: String): Result<PokemonInfoResponse, Error> {
        return handleRequest {
            val list = pokeapiService.getPokemonInfo(name).execute().body()

            list?.let { Success(it) } ?: Failure(Error.Unknown())
        }
    }

    private fun <T> handleRequest(block: () -> Result<T, Error>): Result<T, Error> {
        return try {
            block()
        } catch (e: ConnectException) {
            Failure(Error.NetworkError())
        } catch (e: HttpException) {
            Failure(Error.HTTPError(e.code(), e.message(), e))
        } catch (e: RuntimeException) {
            Failure(Error.Unknown())
        } catch (e: IOException) {
            Failure(Error.Unknown())
        }
    }
}