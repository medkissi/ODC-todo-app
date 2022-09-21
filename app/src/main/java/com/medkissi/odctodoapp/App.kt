package com.medkissi.odctodoapp

import android.app.Application

class App : Application() {
    init {
        app = this
    }

    companion object {
        private lateinit var app: Application
        fun getAppContext() = app.applicationContext
    }
}