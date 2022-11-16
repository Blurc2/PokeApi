package com.example.pokeapi.presentation.pokeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pokeapi.databinding.PokemonListItemBinding
import com.example.pokeapi.domain.pokeapi.model.PokemonListResult
import com.example.pokeapi.presentation.pokeapi.adapter.viewholder.PokeapiViewHolder
import java.util.zip.Inflater

class PokeapiAdapter(private val onClickListener: (String) -> Unit) :
    ListAdapter<PokemonListResult, PokeapiViewHolder>(PokemonListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeapiViewHolder {
        return PokeapiViewHolder(
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            ::onPokemonClicked
        )
    }

    override fun onBindViewHolder(holder: PokeapiViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private fun onPokemonClicked(pos: Int) {
        onClickListener(currentList[pos].name)
    }

    private class PokemonListDiffCallBack : DiffUtil.ItemCallback<PokemonListResult>() {
        override fun areItemsTheSame(oldItem: PokemonListResult, newItem: PokemonListResult): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: PokemonListResult, newItem: PokemonListResult): Boolean = oldItem == newItem
    }
}