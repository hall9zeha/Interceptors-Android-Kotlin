package com.barryzeha.interceptorsapp.common

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.barryzeha.interceptorsapp.MyApp
import com.barryzeha.interceptorsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.net.URL


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/



fun ImageView.loadUrl(url:String) = with(this){
 //Placeholder
 val circularProgressDrawable = CircularProgressDrawable(context)
 circularProgressDrawable.strokeWidth = 5f
 circularProgressDrawable.centerRadius = 30f
 //circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.your_custom_color))
 circularProgressDrawable.start()

 Glide.with(this)
  .load(url)
  .placeholder(circularProgressDrawable)
  .diskCacheStrategy(DiskCacheStrategy.ALL)
  .centerCrop()
  .into(this)
}

fun getNumbersOfLastUrl(url: String): List<Int> {
 val pattern = Regex("""(\d+)\/?$""")
 val matchResult = pattern.find(URL(url).path)
 val numbers = mutableListOf<Int>()

 matchResult?.groupValues?.let {
  for (i in it.size - 1 downTo 1) {
   try {
    numbers.add(it[i].toInt())
   } catch (e: NumberFormatException) {
    Log.e("ID_OF_URL", e.message.toString() )
   }
  }
 }

 return numbers
}