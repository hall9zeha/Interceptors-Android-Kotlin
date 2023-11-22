package com.barryzeha.interceptorsapp.common.utils

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 21/11/23.
 * Copyright (c)  All rights reserved.
 **/
 
class MyInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var tryCount =0
        val request:Request = chain.request()
        val response = chain.proceed(request)
        return when(response.code){
            400->{setResponse(response,chain,400,"Bad request")}
            401->{setResponse(response,chain,401,"No autorizado")}
            403->{setResponse(response,chain,403,"No posee los permisos necesarios")}
            404->{setResponse(response,chain,404,"No hay conexión o la dirección no es correcta")}
            200->{setResponse(response,chain,200,"Respuesta de la API completada correctamente")}
            else->{
                if(!response.isSuccessful && tryCount<5){
                    Thread.sleep(2000)
                    tryCount++
                    response.close()
                    chain.call().clone().execute()
                }
               setResponse(response,chain,401,"Demasiadas peticiones al servidor")
            }
        }

    }
    private fun setResponse(response: Response,chain: Interceptor.Chain,code:Int, msg:String):Response{
        return response.newBuilder()
            .code(code)
            .body(response.body)
            .protocol(Protocol.HTTP_2)
            .message(msg)
            .request(chain.request())
            .build()
    }
}