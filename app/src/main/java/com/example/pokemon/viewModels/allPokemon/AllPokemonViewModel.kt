package com.example.pokemon.viewModels.allPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.util.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllPokemonViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(AllPokemonViewState())
    val allPokemonState: StateFlow<AllPokemonViewState> = _stateFlow

    private val pokemonRepository: PokemonRepository = PokemonRepository()
    private var coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            _stateFlow.value = _stateFlow.value.copy(
                loading = false
            )
        }

    fun loadMorePokemon() {
        _stateFlow.value = _stateFlow.value.copy(
            loading = true
        )
        viewModelScope.launch(coroutineExceptionHandler) {
            loadDetailedPage()
        }
    }

    private suspend fun loadDetailedPage() {
        val result = pokemonRepository.getAllPokemon(Constants.QUERY_PAGE_SIZE, 0)
        val detailedList = mutableListOf<PokemonResponse>()

        result.results?.forEach { pokemon ->
            pokemon.name?.let {
                val details = pokemonRepository.getPokemonDetails(it)
                detailedList.add(details)
            }
        }
        detailedList.sortBy { x -> x.id }
        _stateFlow.value = _stateFlow.value.copy(
            loading = false,
            allPokemon = result,
            pokemonList = detailedList
        )
    }

}