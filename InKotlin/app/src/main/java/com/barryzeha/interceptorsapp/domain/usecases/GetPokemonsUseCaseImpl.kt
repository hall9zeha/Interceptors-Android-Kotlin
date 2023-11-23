package com.barryzeha.interceptorsapp.domain.usecases

import android.util.Log
import com.barryzeha.interceptorsapp.data.repository.Repository
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.domain.model.PokemonData
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import com.barryzeha.interceptorsapp.domain.model.toDomain


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class GetPokemonsUseCaseImpl(private val repository: Repository):GetPokemonsUseCase {
    //private val repository:Repository = RepositoryImpl()
    override suspend fun fetchPokemonData(perPage:Int): PokemonResponse {
       return try {
            val response = repository.getPokemons(perPage)
            if (response.isSuccessful) {
                /*Si usamos tests debemos comentar los informes de Log en las funciones o métodos a testear
                *o nos dará un error o podemos agregar las siguientes dependencias para usar Log en producción y
                *en testing:
                *
                *testImplementation 'org.slf4j:slf4j-api:1.7.32'
                *testImplementation 'org.apache.logging.log4j:log4j-slf4j-impl:2.14.1'
                */

                //Log.e("INTERCEPTOR", response.message())
                PokemonResponse.ResponseSuccess(response.body()!!.toDomain())

            } else {
                PokemonResponse.ResponseError(response.message())
            }
        }catch(e:Exception){
            PokemonResponse.ResponseError(e.message.toString())
        }

    }
}