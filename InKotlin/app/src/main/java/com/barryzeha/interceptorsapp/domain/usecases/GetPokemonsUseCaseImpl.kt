package com.barryzeha.interceptorsapp.domain.usecases

import android.util.Log
import com.barryzeha.interceptorsapp.data.repository.Repository
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import com.barryzeha.interceptorsapp.domain.model.toDomain


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class GetPokemonsUseCaseImpl:GetPokemonsUseCase {
    private val repository:Repository = RepositoryImpl()
    override suspend fun fetchPokemonData(perPage:Int): PokemonResponse {
        return try {
            val response = repository.getPokemons(perPage)
            if (response.isSuccessful) {
                Log.e("INTERCEPTOR", response.message())
                PokemonResponse.ResponseSuccess(response.body()!!.toDomain())

            } else {
                PokemonResponse.ResponseError(response.message())
            }
        }catch(e:Exception){
            PokemonResponse.ResponseError(e.message.toString())
        }

    }
}