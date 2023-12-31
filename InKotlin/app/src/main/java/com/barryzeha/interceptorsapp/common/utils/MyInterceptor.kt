package com.barryzeha.interceptorsapp.common.utils

import android.util.Log
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
    private var tryCount =0
    override fun intercept(chain: Interceptor.Chain): Response {

        val request:Request = chain.request()
        val response = chain.proceed(request)
        return when(response.code){
            400->{setResponse(response,chain,400,"Bad request")}
            401->{setResponse(response,chain,401,"No autorizado")}
            403->{setResponse(response,chain,403,"No posee los permisos necesarios")}
            404->{setResponse(response,chain,404,"No hay conexión o la dirección no es correcta")}
            200->{setResponse(response,chain,200,"Respuesta de la API completada correctamente")}
            else->{
                //Volvemos a llamar a la api  cinco veces más si hay algún error desconocido
                if(!response.isSuccessful && tryCount<5){
                    Thread.sleep(2000)
                    tryCount++
                    response.close()
                    chain.call().clone().execute()

                }
                //Despúes de cumplir las cinco llamadas y no obtener un respuesta satisfactoria
                //posteamos el mensaje recibido
                setResponse(response,chain,429,response.message)

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