package com.barryzeha.interceptorsapp.domain.model


/****
 * Project InterceptorsApp
 * Created by Barry Zea H. on 22/11/2023
 * Copyright (c)  All rights reserved.
 ***/

sealed class PokemonResponse{
    class ResponseSuccess(val pokemonResponse:PokemonData):PokemonResponse()
    class ResponseError(val errorMsg:String):PokemonResponse()
}
