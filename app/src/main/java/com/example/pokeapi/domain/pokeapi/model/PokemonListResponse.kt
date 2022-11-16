package com.example.pokeapi.domain.pokeapi.model

/**
 * "count": 1154,
"next": null,
"previous": null,
"results": [
{
"name": "bulbasaur",
"url": "https://pokeapi.co/api/v2/pokemon/1/"
},
 */
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListResult>
)

data class PokemonListResult(
    val name: String,
    val url: String
)