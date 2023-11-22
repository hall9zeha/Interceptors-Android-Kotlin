package com.barryzeha.interceptorsapp.common.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


/****
 * Project InterceptorsApp
 * Created by Barry Zea H. on 22/11/2023
 * Copyright (c)  All rights reserved.
 ***/

class SingleMutableLiveData<T>:MutableLiveData<T?>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        super.observe(owner) { t ->
            if (t != null) {
                observer.onChanged(t)
                postValue(null)
            }
        }
    }
}