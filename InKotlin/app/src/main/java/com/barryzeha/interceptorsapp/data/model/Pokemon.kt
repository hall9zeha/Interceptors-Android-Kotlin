package com.barryzeha.interceptorsapp.data.model

import com.google.gson.annotations.SerializedName


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

data class Pokemon( @SerializedName("count") val  count:Int=0,
                   @SerializedName("next") val next:String="",
                   @SerializedName("previous")val previous:String="",
                   @SerializedName("results")val result:List<PokemonResult> = arrayListOf()
)
data class PokemonResult(@SerializedName("name")val name:String="",
                         @SerializedName("url")val url:String="")