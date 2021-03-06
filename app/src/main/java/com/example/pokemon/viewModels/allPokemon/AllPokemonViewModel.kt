package com.example.pokemon.viewModels.allPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.PaginationResult
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.util.Constants
import com.example.pokemon.util.UriUtils
import com.example.pokemon.util.safeLet
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllPokemonViewModel(
    private val pokemonRepository: PokemonRepository,
    private val uriUtils: UriUtils
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(AllPokemonViewState())
    val allPokemonState: StateFlow<AllPokemonViewState> = _stateFlow

    private var pagination: PaginationResult = PaginationResult()
    private var coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            _stateFlow.value = _stateFlow.value.copy(
                loading = false
            )
        }

    fun loadNextPokemon() {
        viewModelScope.launch(coroutineExceptionHandler) {
            safeLet(
                pagination.nextLimit,
                pagination.nextOffset
            ) { nextLimit, nextOffset ->
                loadDetailedPage(nextLimit, nextOffset)
            }
        }
    }

    fun loadPreviousPokemon() {
        viewModelScope.launch(coroutineExceptionHandler) {
            safeLet(
                pagination.previousLimit,
                pagination.previousOffSet
            ) { previousLimit, previousOffset ->
                loadDetailedPage(previousLimit, previousOffset)
            }
        }
    }

    private suspend fun loadDetailedPage(limit: Int = Constants.QUERY_PAGE_SIZE, offset: Int = 0) {
        _stateFlow.value = _stateFlow.value.copy(
            loading = true
        )

        val result = pokemonRepository.getAllPokemon(limit, offset)
        updatePaginationResult(result)
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
            pokemonList = detailedList,
            hasNext = result.next != null,
            hasPrevious = result.previous != null
        )
    }

    fun hasData(): Boolean {
        return _stateFlow.value.pokemonList.isNotEmpty()
    }

    private fun updatePaginationResult(response: AllPokemonResponse) {
        pagination.nextLimit = if (response.next == null) null else
            uriUtils.extractQueryParameter(response.next, Limit)?.toInt()
        pagination.nextOffset = if (response.next == null) null else
            uriUtils.extractQueryParameter(response.next, Offset)?.toInt()
        pagination.previousLimit = if (response.previous == null) null else
            uriUtils.extractQueryParameter(response.previous, Limit)?.toInt()
        pagination.previousOffSet = if (response.previous == null) null else
            uriUtils.extractQueryParameter(response.previous, Offset)?.toInt()
    }

    companion object {
        const val Limit = "limit"
        const val Offset = "offset"
    }
}