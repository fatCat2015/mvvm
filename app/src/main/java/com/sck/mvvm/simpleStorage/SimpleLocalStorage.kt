package com.sck.mvvm.simpleStorage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.sck.mvvm.appName
import com.sck.mvvm.application
import java.io.*
import java.lang.IllegalArgumentException



interface SimpleLocalStorage {

    fun <T> get(key:String,defaultValue:T):T
    fun <T> getObject(key:String,type:Class<T>):T?

    fun put(key:String,value:Any){
        this.put(mapOf(key to value))
    }
    fun put(valuePairs:Map<String,Any>)
}



class SharedPreferencesStorage(application: Application,name:String):SimpleLocalStorage {


    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    override fun <T> get(key: String, defaultValue: T): T {
        return when(defaultValue){
            is Int ->{
                sharedPreferences.getInt(key,defaultValue) as T
            }
            is Long ->{
                sharedPreferences.getLong(key,defaultValue) as T
            }
            is Float ->{
                sharedPreferences.getFloat(key,defaultValue) as T
            }
            is Boolean ->{
                sharedPreferences.getBoolean(key,defaultValue) as T
            }
            is String ->{
                sharedPreferences.getString(key,defaultValue) as T
            }
            else -> throw IllegalArgumentException("illegal defaultValue type")
        }
    }

    override fun <T> getObject(key: String, type: Class<T>): T? {
        if(type is Serializable){
            val base64Str=get(key,"")
            val byteArray= Base64.decode(base64Str, Base64.DEFAULT)
            return ObjectInputStream(ByteArrayInputStream(byteArray)).use {
                it.readObject()
            } as? T?
        }
        return null
    }


    override fun put(valuePairs: Map<String, Any>) {
        val editor=sharedPreferences.edit()
        valuePairs.forEach{
            putValue(it.key,it.value,editor)
        }
        editor.apply()
    }

    private fun putValue(key:String, value:Any, editor: SharedPreferences.Editor){
        when(value){
            is Int ->{
                editor.putInt(key,value)
            }
            is Long ->{
                editor.putLong(key,value)
            }
            is Float ->{
                editor.putFloat(key,value)
            }
            is Boolean ->{
                editor.putBoolean(key,value)
            }
            is String ->{
                editor.putString(key,value)
            }
            is Serializable ->{
                ByteArrayOutputStream().also { byteArrayOutputStream ->
                    ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream ->
                        objectOutputStream.writeObject(value)
                        objectOutputStream.flush()
                        put(key, Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT))
                    }
                }
            }
        }
    }



}