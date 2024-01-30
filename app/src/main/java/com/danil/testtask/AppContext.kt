package com.danil.testtask

import android.content.Context

object AppContext {
    lateinit var appContext: Context
    fun provide(context: Context){
        appContext = context
    }
}