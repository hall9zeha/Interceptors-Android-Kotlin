package com.barryzeha.interceptorsapp.data.network

import com.barryzeha.interceptorsapp.common.BASE_URL
import com.barryzeha.interceptorsapp.common.utils.MyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/
 
private var retrofit:Retrofit? = null

fun getClient():Retrofit{
    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    val client = OkHttpClient.Builder().addInterceptor(MyInterceptor()).build()

    if (retrofit == null){
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }
    return retrofit!!
}
fun getApi():ApiService{
    return getClient().create(ApiService::class.java)
}