package com.example.pokemon

import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.models.entity.NameAndUrl
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.util.UriUtils
import com.example.pokemon.viewModels.allPokemon.AllPokemonViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AllPokemonViewModelTest {
    @MockK
    private lateinit var mockRepo: PokemonRepository

    @MockK
    private lateinit var mockUriUtil: UriUtils

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: AllPokemonViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        mockRepo = spyk(PokemonRepository())
        mockUriUtil = spyk(UriUtils())
        viewModel = AllPokemonViewModel(mockRepo, mockUriUtil)
    }

    @Test
    fun hasDataIsFalse() = runBlocking {
        //Given no pokemon names are returned
        coEvery {
            mockRepo.getAllPokemon(any(), any())
        } returns AllPokemonResponse()

        //When
        viewModel.loadNextPokemon()

        //Then
        assertEquals(false, viewModel.hasData())
    }

    @Test
    fun hasDataIsTrue() = runBlocking {
        //Given data is returned
        coEvery {
            mockRepo.getAllPokemon(any(), any())
        } returns AllPokemonResponse(results = listOf(NameAndUrl("testName", "testUrl")))

        coEvery {
            mockRepo.getPokemonDetails(any())
        } returns PokemonResponse()

        coEvery {
            mockUriUtil.extractQueryParameter(any(), any())
        } returns "test"

        //When
        viewModel.loadNextPokemon()

        //Then
        assertEquals(true, viewModel.hasData())
    }
}