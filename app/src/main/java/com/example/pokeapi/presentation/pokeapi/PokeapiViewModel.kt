package com.example.pokeapi.presentation.pokeapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.domain.pokeapi.PokeapiUseCase
import com.example.pokeapi.domain.pokeapi.model.PokemonListResponse
import com.example.pokeapi.domain.pokeapi.model.PokemonListResult
import com.example.pokeapi.domain.utils.Failure
import com.example.pokeapi.domain.utils.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PokeapiViewModel(private val pokeapiUseCase: PokeapiUseCase): ViewModel() {

    private val _onPokemonListFetch = MutableLiveData<List<PokemonListResult>>()
    val onPokemonListFetch: LiveData<List<PokemonListResult>> get() = _onPokemonListFetch

    private val _onDisplayLoader = MutableLiveData<Boolean>()
    val onDisplayLoader: LiveData<Boolean> get() = _onDisplayLoader

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> get() = _onError

    fun getPokemonList() = viewModelScope.launch {
        pokeapiUseCase.getPokemonList()
            .onStart { showLoader() }
            .onCompletion { hideLoader() }
            .collect { result ->
                when(result) {
                    is Success -> {
                        _onPokemonListFetch.postValue(result.value.results)
                    }
                    is Failure -> showError(result.reason.message.toString())
                }
            }
    }

    private fun showError(msg: String) {
        _onError.postValue(msg)
    }

    private fun showLoader() {
        _onDisplayLoader.postValue(true)
    }

    private fun hideLoader() {
        _onDisplayLoader.postValue(false)
    }
}