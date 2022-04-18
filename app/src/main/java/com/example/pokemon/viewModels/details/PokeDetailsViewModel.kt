package com.example.pokemon.viewModels.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.util.extractLastUrlParam
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

        val detailedAbilities = mutableListOf<AbilityDetailed>()
        result.abilities?.forEach {
            val id = it.ability?.url?.extractLastUrlParam()
            id?.let {
                val detailedAbility = pokemonRepository.getAbility(id)
                detailedAbilities.add(detailedAbility)
            }
        }

        _stateFlow.value = _stateFlow.value.copy(
            loading = false,
            pokemon = result,
            detailedAbilities = detailedAbilities
        )
    }
}