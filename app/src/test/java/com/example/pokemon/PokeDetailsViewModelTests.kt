package com.example.pokemon

import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.models.entity.*
import com.example.pokemon.util.UriUtils
import com.example.pokemon.viewModels.details.PokeDetailsViewModel
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
class PokeDetailsViewModelTests {
    @MockK
    private lateinit var mockRepo: PokemonRepository

    @MockK
    private lateinit var mockUriUtil: UriUtils

    private val dispatcher = UnconfinedTestDispatcher()
    private val testPokemon = "ditto"
    private val testAbility = "battle-armor"
    private val testId = 1

    private lateinit var viewModel: PokeDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        mockRepo = spyk(PokemonRepository())
        mockUriUtil = spyk(UriUtils())
        viewModel = PokeDetailsViewModel(mockRepo, mockUriUtil)
    }

    @Test
    fun hasDataIsFalse() = runBlocking {
        //Given empty response is returned
        coEvery {
            mockRepo.getPokemonDetails(any())
        } returns PokemonResponse()

        //When
        viewModel.getPokemon(testPokemon)

        //Then
        assertEquals(false, viewModel.hasData())
    }

    @Test
    fun hasDataIsTrue() = runBlocking {
        //Given data is returned
        coEvery {
            mockRepo.getPokemonDetails(testPokemon)
        } returns PokemonResponse(
            id = testId,
            abilities = listOf(
                Ability(
                    NameAndUrl(
                        name = "test",
                        url = "testUrl"
                    )
                )
            )
        )

        coEvery {
            mockRepo.getAbility(any())
        } returns AbilityDetailed()

        coEvery {
            mockRepo.getSpecies(any())
        } returns Species()

        coEvery {
            mockUriUtil.extractLastUrlParam(any())
        } returns testAbility

        //When
        viewModel.getPokemon(testPokemon)

        //Then
        assertEquals(true, viewModel.hasData())
    }
}