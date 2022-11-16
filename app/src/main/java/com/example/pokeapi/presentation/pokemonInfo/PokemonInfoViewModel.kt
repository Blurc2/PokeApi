package com.example.pokeapi.presentation.pokemonInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.domain.pokeapi.PokeapiUseCase
import com.example.pokeapi.domain.pokeapi.model.PokemonInfoResponse
import com.example.pokeapi.domain.utils.Failure
import com.example.pokeapi.domain.utils.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PokemonInfoViewModel(private val pokeapiUseCase: PokeapiUseCase): ViewModel() {

    private val _onPokemonInfoFetch = MutableLiveData<PokemonInfoResponse>()
    val onPokemonInfoFetch: LiveData<PokemonInfoResponse> get() = _onPokemonInfoFetch

    private val _onDisplayLoader = MutableLiveData<Boolean>()
    val onDisplayLoader: LiveData<Boolean> get() = _onDisplayLoader

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> get() = _onError

    fun getPokemonInfo(name: String) = viewModelScope.launch {
        pokeapiUseCase.getPokemonInfo(name)
            .onStart { showLoader() }
            .onCompletion { hideLoader() }
            .collect { result ->
                when(result) {
                    is Success -> {
                        result.value?.let { _onPokemonInfoFetch.postValue(it) }
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