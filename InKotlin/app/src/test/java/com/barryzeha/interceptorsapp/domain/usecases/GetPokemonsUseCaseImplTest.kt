package com.barryzeha.interceptorsapp.domain.usecases

import android.util.Log
import com.barryzeha.interceptorsapp.data.model.Pokemon
import com.barryzeha.interceptorsapp.data.model.PokemonResult
import com.barryzeha.interceptorsapp.data.repository.Repository
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.domain.model.PokemonData
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import retrofit2.Response


/****
 * Project InterceptorsApp
 * Created by Barry Zea H. on 23/11/2023
 * Copyright (c)  All rights reserved.
 */

class GetPokemonsUseCaseImplTest{
    private lateinit var repository:Repository
    private lateinit var useCase:GetPokemonsUseCase

    @Before
    fun setUp(){
        repository = mockk(relaxed = true)
        useCase = GetPokemonsUseCaseImpl(repository)
    }

    @Test
    fun `verify if fetch api is successful`() = runBlocking{
        //Given
        val response = Response.success(200,pokemonData())

        coEvery { repository.getPokemons(20) } returns mockk{
            every {isSuccessful} returns true
            every {body()} returns response.body()

        }
        //When
        val result = useCase.fetchPokemonData(20)
        //Then
        assert(result is PokemonResponse.ResponseSuccess)
        assertEquals((result as PokemonResponse.ResponseSuccess).pokemonResponse.totalItems,response.body()!!.toDomain().totalItems)


    }
    @Test
    fun `verify if fetch api is successful second way`() = runBlocking{
        val response = Response.success(200,pokemonData())
        //Given
        coEvery { repository.getPokemons(20) }returns response
        //When
        val result = useCase.fetchPokemonData(20)
        //Then
        assertEquals("Se esperaba ResponseSuccess pero se obtuvo $result", PokemonResponse.ResponseSuccess::class, result::class)
    }

    @Test
    fun `verify if fetch is unsuccessful`() = runBlocking {
        val response = Response.error<Pokemon>(404,"Not Found".toResponseBody("application/json".toMediaTypeOrNull())
        )
        //Given
        coEvery { repository.getPokemons(20) } returns response
        //When
        val result = useCase.fetchPokemonData(20)
        //Then
        assert(result is PokemonResponse.ResponseError)
    }
    companion object{
        fun Pokemon.toDomain()=PokemonData(
            totalItems = 2,
            pokemons = listOf(
                PokemonEntity("Pikachu","https://www.fakeurlpokemonimage/${1}.png"),
                PokemonEntity("Charmander","https://www.fakeurlpokemonimage/${2}.png")

            )
        )
        fun pokemonData() = Pokemon(
            count = 2,
            next = "",
            previous = "",
            result = listOf(
                PokemonResult("Pikachu","https://fakeurlpokemon/1/"),
                PokemonResult("Charmander","https://fakeurlpokemon/2/"),

                )

        )
    }
}