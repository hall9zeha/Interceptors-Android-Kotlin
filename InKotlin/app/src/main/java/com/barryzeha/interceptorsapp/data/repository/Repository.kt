package com.barryzeha.interceptorsapp.data.repository

import com.barryzeha.interceptorsapp.data.model.Pokemon
import retrofit2.Response


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

interface Repository {
 suspend fun getPokemons(perPage:Int):Response<Pokemon>
}