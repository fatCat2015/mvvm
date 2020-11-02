package com.sck.mvvm

import android.app.Application
import com.sck.mvvm.simpleStorage.SharedPreferencesStorage
import com.sck.mvvm.simpleStorage.SimpleLocalStorage


val application:Application = App.app

val appName= application.getString(R.string.app_name)

val simpleLocalStorage: SimpleLocalStorage = SharedPreferencesStorage(application, appName)