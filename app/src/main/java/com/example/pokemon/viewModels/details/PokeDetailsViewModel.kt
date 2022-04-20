package com.example.pokemon.viewModels.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.util.UriUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokeDetailsViewModel(
    private val pokemonRepository: PokemonRepository,
    private val uriUtils: UriUtils
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(PokeDetailsViewState())
    val pokemonDetails: StateFlow<PokeDetailsViewState> = _stateFlow

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

    fun hasData(): Boolean {
        return with(_stateFlow.value) {
            detailedAbilities.isNotEmpty() &&
                    pokemon != null &&
                    species != null
        }
    }

    private suspend fun getSinglePokemon(id: String) {
        val result = pokemonRepository.getPokemonDetails(id)

        val detailedAbilities = mutableListOf<AbilityDetailed>()
        result.abilities?.forEach {
            val abilityId = uriUtils.extractLastUrlParam(it.ability?.url)
            abilityId?.let {
                val detailedAbility = pokemonRepository.getAbility(abilityId)
                detailedAbilities.add(detailedAbility)
            }
        }
        val species = pokemonRepository.getSpecies(result.id.toString())

        _stateFlow.value = _stateFlow.value.copy(
            loading = false,
            pokemon = result,
            detailedAbilities = detailedAbilities,
            species = species
        )
    }
}