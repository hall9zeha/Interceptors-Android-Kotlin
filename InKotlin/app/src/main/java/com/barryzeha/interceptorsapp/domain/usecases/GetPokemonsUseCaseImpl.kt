package com.barryzeha.interceptorsapp.domain.usecases

import android.util.Log
import com.barryzeha.interceptorsapp.data.model.PokemonResult
import com.barryzeha.interceptorsapp.data.repository.Repository
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class GetPokemonsUseCaseImpl:GetPokemonsUseCase {
    private val repository:Repository = RepositoryImpl()
    override suspend fun getPokemonList(perPage:Int): List<PokemonResult> {
        val response = repository.getPokemons(perPage)
        return if(response.isSuccessful){
            response.body()!!.result
        }else{
            emptyList()
        }
    }
}