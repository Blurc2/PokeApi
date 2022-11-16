package com.example.pokeapi.domain.pokeapi.model

/**
 * id:35
name:"clefairy"
base_experience:113
height:6
is_default:true
order:56
weight:75
 */
data class PokemonInfoResponse(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int
)
