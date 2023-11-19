package com.barryzeha.interceptorsapp

import android.app.Application
import android.content.Context


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/

class MyApp: Application() {
 companion object{
  private var _context:Context?=null
  val context:Context get() = _context!!
 }
 override fun onCreate() {
  super.onCreate()
  _context = applicationContext
 }
}