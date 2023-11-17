package com.barryzeha.interceptorsapp.data.network

import com.barryzeha.interceptorsapp.data.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(@Query("offset")perPage:Int): Response<Pokemon>

}