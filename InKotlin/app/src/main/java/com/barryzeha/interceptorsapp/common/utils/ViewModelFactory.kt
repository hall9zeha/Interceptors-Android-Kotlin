package com.barryzeha.interceptorsapp.common.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCase
import com.barryzeha.interceptorsapp.ui.viewModel.MainViewModel


/****
 * Project InterceptorsApp
 * Created by Barry Zea H. on 24/11/2023
 * Copyright (c)  All rights reserved.
 ***/

//Para testear el viewmodel necesitamos inyectar una instancia del caso de uso
//para hacerlo sin librerías de inyección de dependencias usamos un ViewModelFactory

//View model factory genérico para ser usado en cualquier clase ViewModel
class ViewModelFactory<T:ViewModel>(  private val creator: () -> T):ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
          if(modelClass.isAssignableFrom(creator().javaClass)){
              @Suppress("UNCHECKED_CAST")
              return creator() as T
          }
          throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}