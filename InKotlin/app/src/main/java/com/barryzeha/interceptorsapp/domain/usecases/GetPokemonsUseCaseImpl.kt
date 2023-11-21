package com.barryzeha.interceptorsapp.domain.usecases

import android.util.Log
import com.barryzeha.interceptorsapp.data.model.PokemonResult
import com.barryzeha.interceptorsapp.data.repository.Repository
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity
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
        val response = repository.getPokemons(perPage)
        return if(response.isSuccessful){
            Log.e("INTERCEPTOR",response.message()  )
            response.body()!!.toDomain()
        }else{
            PokemonResponse()
        }
    }
}