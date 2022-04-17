package com.example.pokemon.viewModels.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.PokemonRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokeDetailsViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(PokeDetailsViewState())
    val pokemonDetails: StateFlow<PokeDetailsViewState> = _stateFlow

    private val pokemonRepository: PokemonRepository = PokemonRepository()
    private var coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            _stateFlow.value = _stateFlow.value.copy(
                loading = false
            )
        }

    fun getPokemon(id: String) {
        _stateFlow.value = _stateFlow.value.copy(
            loading = true
        )
        viewModelScope.launch(coroutineExceptionHandler) {
            getSinglePokemon(id)
        }
    }

    private suspend fun getSinglePokemon(id: String) {
        val result = pokemonRepository.getPokemonDetails(id)
        _stateFlow.value = _stateFlow.value.copy(
            loading = false,
            pokemon = result
        )
    }
}