package com.barryzeha.interceptorsapp.data.repository

import com.barryzeha.interceptorsapp.data.model.Pokemon
import com.barryzeha.interceptorsapp.data.network.getApi
import com.barryzeha.interceptorsapp.data.network.getClient
import retrofit2.Response


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class RepositoryImpl:Repository {
 private val api = getApi()
 override suspend fun getPokemons(perPage: Int): Response<Pokemon> {
   return api.getPokemons(perPage)
 }
}