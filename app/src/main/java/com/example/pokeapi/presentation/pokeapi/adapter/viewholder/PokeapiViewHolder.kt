package com.example.pokeapi.presentation.pokeapi.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.databinding.PokemonListItemBinding
import com.example.pokeapi.domain.pokeapi.model.PokemonListResult

class PokeapiViewHolder(private val binding: PokemonListItemBinding, private val onClickListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            onClickListener(adapterPosition)
        }
    }

    fun bind(data: PokemonListResult) {
        binding.tvName.text = data.name
    }
}