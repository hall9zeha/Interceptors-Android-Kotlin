package com.barryzeha.interceptorsapp.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.barryzeha.interceptorsapp.domain.model.PokemonData
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCase
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.kotlin.mock


/****
 * Project InterceptorsApp
 * Created by Barry Zea H. on 24/11/2023
 * Copyright (c)  All rights reserved.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var useCase:GetPokemonsUseCase

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        useCase = mockk(relaxed = true)
        viewModel = MainViewModel(useCase)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `if response is success and not empty`() = runTest {
        val pokemonData =PokemonData(
            totalItems = 2,
            pokemons = listOf(
                PokemonEntity("Pikachu","https://www.fakeurlpokemonimage/${1}.png"),
                PokemonEntity("Charmander","https://www.fakeurlpokemonimage/${2}.png")

            )
        )
        //Given
        coEvery { useCase.fetchPokemonData(20) } returns PokemonResponse.ResponseSuccess(pokemonData)

        //When
        viewModel.fetchPokemonData(20)

        //Then
        //esperamos a que se complete la tarea asíncrona
        advanceTimeBy(1000)
        //Verificamos si se llama al método
        coVerify { useCase.fetchPokemonData(20) }

        assertEquals(pokemonData,viewModel.pokemonResponse.value)
    }

}